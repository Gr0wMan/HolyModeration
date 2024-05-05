package com.holymoderation.addon.events;

import com.holymoderation.addon.HolyModeration;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

public class FreezerEvent {

    @Subscribe
    public void Freeze(MessageSendEvent event) {
        boolean defFrz = false;
        boolean advFrz = false;
        String player;
        String message = event.getMessage();

        if (message.startsWith("/freezing"))
            defFrz = true;
        else if (message.startsWith("/frz"))
            advFrz = true;

        if (defFrz || advFrz) {
            player = event.getMessage().split(" ")[1];
            if (advFrz)
                HolyModeration.SendMessage("freezing " + player);
            HolyModeration.SendMessage("/checkmute " + player);
            HolyModeration.SendMessage("/prova");
            HolyModeration.SendMessage("/afk");
            if (HolyModeration.IsDupeIpEnabled())
                HolyModeration.SendMessage("/dupeip " + player);
            for (String text : HolyModeration.GetTexts()){
                if (HolyModeration.GetTexts() != null)
                    HolyModeration.SendMessage("/msg " + player + " " + text);
                else
                    HolyModeration.ClientMessage("You don't have any texts, so.. add them!");
            }
        }
    }
}
