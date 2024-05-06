package com.holymoderation.addon.events;

import com.holymoderation.addon.HolyModeration;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

public class HMSettings {

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        if (message.matches("/hmtextlist")) {
            event.setCancelled(true);
            HolyModeration.ClientMessage("list of your texts:");
            if (FreezerEvent.GetSplitTexts() == null)
                HolyModeration.ClientMessage("you don't have any texts");
            else
                for (int i = 0; i < FreezerEvent.GetSplitTexts().length; i++)
                {
                    HolyModeration.ClientMessage((i+1) + ". " + FreezerEvent.GetSplitTexts()[i]);
                }
        }

        else if (message.matches("/hmdupeip")) {
            event.setCancelled(true);
            FreezerEvent.SetDupeIp(!FreezerEvent.GetDupeIp());
            if (FreezerEvent.GetDupeIp())
                HolyModeration.ClientMessage("auto dupeip ON");
            else
                HolyModeration.ClientMessage("auto dupeip OFF");
        }

        else if (message.matches("/hmgetvk")) {
            event.setCancelled(true);
            HolyModeration.ClientMessage("your vk: " + FreezerEvent.GetVkUrl());
        }

        else if (message.startsWith("/hmsetvk")) {
            event.setCancelled(true);
            String value = message.split(" ")[1];
            FreezerEvent.SetVkUrl(value);
            HolyModeration.ClientMessage("Now your vk is: " + FreezerEvent.GetVkUrl());
        }

        else if (message.startsWith("/hmtextadd")) {
            event.setCancelled(true);
            String value = message.split(" ", 2)[1];
            FreezerEvent.AddText(value);
            HolyModeration.ClientMessage("You have added new text!");
        }
        else if (message.startsWith("/hmtextremove")) {
            event.setCancelled(true);
            String value = message.split(" ")[1];
            FreezerEvent.RemoveText(value);
            HolyModeration.ClientMessage("You have removed text number " + message.split(" ", 0)[1] + "!");
        }
    }
}
