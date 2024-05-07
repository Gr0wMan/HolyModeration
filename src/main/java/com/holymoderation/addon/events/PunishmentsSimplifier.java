package com.holymoderation.addon.events;

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
            if (message.split(" ", 3).length == 1) {
                MessageManager.ClientMessage(Colors.RED + "Вы не указали время и причину бана!");
                return;
            }
            if (message.split(" ", 3).length == 2) {
                MessageManager.ClientMessage(Colors.RED + "Вы не указали причину бана!");
                return;
            }
            if (!(message.split(" ", 3)[1].equals("20d")) && !(message.split(" ", 3)[1].equals("30d"))) {
                MessageManager.ClientMessage(Colors.RED + "Некорректное время бана! (Должно быть 20d или 30d)");
                return;
            }
            String time = message.split(" ", 3)[1];
            String reason = message.split(" ", 3)[2];
            MessageManager.SendMessage("/banip " + player + " " + time + " 2.4 (" + reason + ") | Вопросы? " + vkUrl + " -s");
            MessageManager.SendMessage("/freezing " + player);
            MessageManager.SendMessage("/prova");
            onCheck = false;
            player = null;
            RenderEvent.SetOnCheck(onCheck);
            FreezerEvent.SetPlayer(player);
            RenderEvent.SetPlayer(player);
        }

        else if (message.startsWith("/imute")) {
            event.setCancelled(true);
            String nick = message.split(" ", 3)[1];
            if (onCheck && nick.equals(player)) {
                MessageManager.ClientMessage(Colors.RED + "Вы не можете замутить этого игрока, " +
                        "так как он находится у вас на проверке!");
                return;
            }
            String reason = message.split(" ", 3)[2];
            MessageManager.SendMessage("/mute " + nick + " " + reason + " -s");
        }

        else if (message.startsWith("/iban")) {
            event.setCancelled(true);
            String nick = message.split(" ", 3)[1];
            if (onCheck && nick.equals(player)) {
                MessageManager.ClientMessage(Colors.RED + "Вы не можете забанить этого игрока, " +
                        "так как он находится у вас на проверке!");
                return;
            }
            String reason = message.split(" ", 3)[2];
            MessageManager.SendMessage("/ban " + nick + " " + reason + " | Вопросы? " + vkUrl + " -s");
        }

        else if (message.startsWith("/ibanip")) {
            event.setCancelled(true);
            String nick = message.split(" ", 3)[1];
            if (onCheck && nick.equals(player)) {
                MessageManager.ClientMessage(Colors.RED + "Вы не можете забанить этого игрока, " +
                        "так как он находится у вас на проверке!");
                return;
            }
            String reason = message.split(" ", 3)[2];
            MessageManager.SendMessage("/banip " + nick + " " + reason + " | Вопросы? " + vkUrl + " -s");
        }

        else if (message.startsWith("/tmute")) {
            event.setCancelled(true);
            String nick = message.split(" ", 4)[1];
            if (onCheck && nick.equals(player)) {
                MessageManager.ClientMessage(Colors.RED + "Вы не можете замутить этого игрока, " +
                        "так как он находится у вас на проверке!");
                return;
            }
            String time = message.split(" ", 4)[2];
            String reason = message.split(" ", 4)[3];
            MessageManager.SendMessage("/tempmute " + nick + " " + time + " " + reason + " -s");
        }

        else if (message.startsWith("/tban")) {
            event.setCancelled(true);
            String nick = message.split(" ", 4)[1];
            if (onCheck && nick.equals(player)) {
                MessageManager.ClientMessage(Colors.RED + "Вы не можете забанить этого игрока, " +
                        "так как он находится у вас на проверке!");
                return;
            }
            String time = message.split(" ", 4)[2];
            String reason = message.split(" ", 4)[3];
            MessageManager.SendMessage("/tempban " + nick + " " + time + " " + reason + " | Вопросы? " + vkUrl + " -s");
        }

        else if (message.startsWith("/tbanip")) {
            event.setCancelled(true);
            String nick = message.split(" ", 4)[1];
            String time = message.split(" ", 4)[2];
            String reason = message.split(" ", 4)[3];
            MessageManager.SendMessage("/banip " + nick + " " + time + " " + reason + " | Вопросы? " + vkUrl + " -s");
            if (onCheck && nick.equals(player)) {
                onCheck = false;
                RenderEvent.SetOnCheck(onCheck);
                player = null;
                FreezerEvent.SetPlayer(player);
                RenderEvent.SetPlayer(player);
            }
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
