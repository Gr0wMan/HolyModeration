package com.holymoderation.addon.events;

import com.holymoderation.addon.HolyModeration;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

public class HMHelp {
    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        if (message.matches("/hmhelp") || message.matches("/hm")) {
            event.setCancelled(true);
            HolyModeration.ClientMessage("HM help:");
            HolyModeration.ClientMessage("/hmhelp or /hm will show this texts");
            HolyModeration.ClientMessage("/hmsavecfg - saves your cfg");
            HolyModeration.ClientMessage("/hmtextlist - shows your texts");
            HolyModeration.ClientMessage("/hmtextadd text - adds new text");
            HolyModeration.ClientMessage("/hmtextremove number - removes text by his number in list");
            HolyModeration.ClientMessage("/hmsetvk your vk - sets your vk for bans");
            HolyModeration.ClientMessage("/hmgetvk your vk - shows your vk");
            HolyModeration.ClientMessage("/hmdupeip - turn on/off auto dupeip when you freeze the player");
        }
    }
}
