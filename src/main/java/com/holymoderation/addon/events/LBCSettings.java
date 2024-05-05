package com.holymoderation.addon.events;

import com.holymoderation.addon.HolyModeration;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

public class LBCSettings {

    @Subscribe
    public void Settings(MessageSendEvent event) {
        String message = event.getMessage();
        if (message.matches("/lbctextlist")) {
            HolyModeration.ClientMessage("list of your texts:");
            if (HolyModeration.GetTexts() == null)
                HolyModeration.ClientMessage("you don't have any texts");
            for (int i = 0; i < HolyModeration.GetTexts().length; i++)
            {
                HolyModeration.ClientMessage((i+1) + ". " + HolyModeration.GetTexts()[i]);
            }
        }

        if (message.matches("/lbcgetvk")) {
            HolyModeration.ClientMessage("your vk: " + HolyModeration.GetVkUrl());
        }

        if (message.matches("/lbctextadd")) {
            HolyModeration.AddText(message.split(" ", 0)[1]);
            HolyModeration.ClientMessage("You have added new text!");
        }
        if (message.matches("/lbctextremove")) {
            HolyModeration.RemoveText(message.split(" ", 0)[1]);
            HolyModeration.ClientMessage("You have removed text number " + message.split(" ", 0)[1] + "!");
        }

        if (message.matches("/lbcsetvk")) {
            HolyModeration.SetVkUrl(message.split(" ", 0)[1]);
            HolyModeration.ClientMessage("Now your vk is: " + HolyModeration.GetVkUrl());
        }

        if (message.matches("/lbcdupeip")) {
            HolyModeration.SetDupeIp(!HolyModeration.IsDupeIpEnabled());
            if (HolyModeration.IsDupeIpEnabled())
                HolyModeration.ClientMessage("auto dupeip ON");
            else
                HolyModeration.ClientMessage("auto dupeip OFF");
        }

        if (message.matches("/lbcsavecfg")) {
            new HolyModeration().SaveCfg();
            HolyModeration.ClientMessage("Your cfg has been saved!");
        }
    }
}
