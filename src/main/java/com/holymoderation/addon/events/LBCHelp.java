package com.holymoderation.addon.events;

import com.holymoderation.addon.HolyModeration;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

public class LBCHelp {
    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        if (message.matches("/lbchelp") || message.matches("/lbc")) {
            HolyModeration.ClientMessage("LBC help:");
            HolyModeration.ClientMessage("/lbchelp or /lbc will show this texts");
            HolyModeration.ClientMessage("/savefcg - saves your cfg");
            HolyModeration.ClientMessage("/lbctextlist - shows your texts");
            HolyModeration.ClientMessage("/lbctextadd text - adds new text");
            HolyModeration.ClientMessage("/lbctextadd number - removes text by his number in list");
            HolyModeration.ClientMessage("/lbcsetvk your vk - sets your vk for bans");
            HolyModeration.ClientMessage("/lbcdupeip - turn on/off auto dupeip when you freeze the player");
        }
    }
}
