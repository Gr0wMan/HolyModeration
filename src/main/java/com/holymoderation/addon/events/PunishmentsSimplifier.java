package com.holymoderation.addon.events;

import com.holymoderation.addon.HolyModeration;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

public class PunishmentsSimplifier {
    private static String player = null;
    private static String vkUrl = null;

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        if (message.startsWith("/sban")) {
            event.setCancelled(true);
            if (player == null) {
                HolyModeration.ClientMessage("Вы никого не проверяете!");
                return;
            }
            if (vkUrl == null) {
                HolyModeration.ClientMessage("Вы не установили ссылку на вк!");
                return;
            }
            String time = message.split(" ", 3)[1];
            String reason = message.split(" ", 3)[2];
            HolyModeration.SendMessage("/banip " + player + " " + time + " 2.4 (" + reason + ") | Вопросы? " + vkUrl + " -s");
            HolyModeration.SendMessage("/freezing " + player);
            HolyModeration.SendMessage("/prova");
            RenderEvent.setOnCheck(false);
            player = vkUrl = null;
            RenderEvent.setPlayer(player);
        }
    }

    public static String GetVkUrl() {
        return vkUrl;
    }

    public static void SetVkUrl(String value) {
        vkUrl = value;
    }

    public static void SetPlayer(String value) {
        player = value;
    }
}
