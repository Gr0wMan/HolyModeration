package com.labycheck.addon.events;

import com.labycheck.addon.LabyCheck;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;
import net.labymod.core.LabyModCore;

public class FreezerEvent {

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String[] array;
        if (event.getMessage().startsWith("/frz")) {
            array = event.getMessage().split(" ");
            event.setCancelled(true);
            SendMessage("Freezed: " + array[1] + ", vk: " + LabyCheck.getVkUrl() + ", dupeip enabled: " + LabyCheck.IsDupeIpEnabled());
        }
    }

    private static void SendMessage(String message){
        LabyModCore.getMinecraft().getPlayer().sendChatMessage(message);
    }
}
