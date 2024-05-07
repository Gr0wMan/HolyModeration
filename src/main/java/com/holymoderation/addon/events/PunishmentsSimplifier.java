package com.holymoderation.addon.events;

import ca.weblite.objc.Client;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import com.holymoderation.addon.ChatUtils.MessageManager;
import com.holymoderation.addon.ChatUtils.Colors;

public class PunishmentsSimplifier {
    private static Boolean onCheck = false;
    private static String player = null;
    private static String vkUrl = null;

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        if (message.startsWith("/sban")) {
            event.setCancelled(true);
            if (!onCheck) {
                MessageManager.ClientMessage(Colors.RED + "Вы никого не проверяете!");
                return;
            }
            if (vkUrl == null) {
                MessageManager.ClientMessage(Colors.RED + "Вы не установили ссылку на вк!");
                return;
            }
            if (message.split(" ").length == 1) {
                MessageManager.ClientMessage(Colors.RED + "Вы не указали время и причину бана!");
                return;
            }
            if (message.split(" ").length == 2) {
                MessageManager.ClientMessage(Colors.RED + "Вы не указали причину бана!");
                return;
            }
            String time = message.split(" ", 3)[1];
            String reason = message.split(" ", 3)[2];
            MessageManager.SendMessage("/banip " + player + " " + time + " 2.4 (" + reason + ") | Вопросы? " + vkUrl + " -s");
            MessageManager.SendMessage("/freezing " + player);
            MessageManager.SendMessage("/prova");
            onCheck = false;
            player = null;
            RenderEvent.setOnCheck(onCheck);
            FreezerEvent.SetPlayer(player);
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

    public static void SetOnCheck(Boolean value) {
        onCheck = value;
    }
}
