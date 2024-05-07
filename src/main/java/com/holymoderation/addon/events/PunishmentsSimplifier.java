package com.holymoderation.addon.events;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import com.holymoderation.addon.ChatUtils.ChatManager;
import com.holymoderation.addon.ChatUtils.Colors;
import com.holymoderation.addon.ChatUtils.PunishmentsHelper;

public class PunishmentsSimplifier {
    private static Boolean onCheck = false;
    private static String player = null;

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        if (message.startsWith("/sban")) {
            event.setCancelled(true);
            if (!onCheck) {
                ChatManager.ClientMessage(Colors.RED + "Вы никого не проверяете!");
                return;
            }
            if (!PunishmentsHelper.CheckVk()) {
                ChatManager.ClientMessage(Colors.RED + "У вас не установлена ссылка на вк!");
                return;
            }
            if (message.split(" ", 3).length == 1) {
                ChatManager.ClientMessage(Colors.RED + "Вы не указали время и причину бана!");
                return;
            }
            if (message.split(" ", 3).length == 2) {
                ChatManager.ClientMessage(Colors.RED + "Вы не указали причину бана!");
                return;
            }
            if (!(message.split(" ", 3)[1].equals("20d")) && !(message.split(" ", 3)[1].equals("30d"))) {
                ChatManager.ClientMessage(Colors.RED + "Некорректное время бана! (Должно быть 20d или 30d)");
                return;
            }
            String time = message.split(" ", 3)[1];
            String reason = message.split(" ", 3)[2];
            ChatManager.SendMessage("/banip " + player + " " + time + " 2.4 (" + reason + ") | Вопросы? " + ChatManager.GetVkUrl() + " -s");
            ChatManager.SendMessage("/freezing " + player);
            ChatManager.SendMessage("/prova");
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
                ChatManager.ClientMessage(Colors.RED + "Вы не можете замутить этого игрока, " +
                        "так как он находится у вас на проверке!");
                return;
            }
            String reason = message.split(" ", 3)[2];
            ChatManager.SendMessage("/mute " + nick + " " + reason + " -s");
        }

        else if (message.startsWith("/iban")) {
            event.setCancelled(true);
            if (!PunishmentsHelper.CheckVk()) {
                ChatManager.ClientMessage(Colors.RED + "У вас не установлена ссылка на вк!");
                return;
            }
            String nick = message.split(" ", 3)[1];
            if (onCheck && nick.equals(player)) {
                ChatManager.ClientMessage(Colors.RED + "Вы не можете забанить этого игрока, " +
                        "так как он находится у вас на проверке!");
                return;
            }
            String reason = message.split(" ", 3)[2];
            ChatManager.SendMessage("/ban " + nick + " " + reason + " | Вопросы? " + ChatManager.GetVkUrl() + " -s");
        }

        else if (message.startsWith("/ibanip")) {
            event.setCancelled(true);
            if (!PunishmentsHelper.CheckVk()) {
                ChatManager.ClientMessage(Colors.RED + "У вас не установлена ссылка на вк!");
                return;
            }
            String nick = message.split(" ", 3)[1];
            if (onCheck && nick.equals(player)) {
                ChatManager.ClientMessage(Colors.RED + "Вы не можете забанить этого игрока, " +
                        "так как он находится у вас на проверке!");
                return;
            }
            String reason = message.split(" ", 3)[2];
            ChatManager.SendMessage("/banip " + nick + " " + reason + " | Вопросы? " + ChatManager.GetVkUrl() + " -s");
        }

        else if (message.startsWith("/tmute")) {
            event.setCancelled(true);
            String nick = message.split(" ", 4)[1];
            if (onCheck && nick.equals(player)) {
                ChatManager.ClientMessage(Colors.RED + "Вы не можете замутить этого игрока, " +
                        "так как он находится у вас на проверке!");
                return;
            }
            String time = message.split(" ", 4)[2];
            if (!PunishmentsHelper.CheckTimeFormat(time)) {
                ChatManager.ClientMessage(Colors.RED + "Неверный формат времени! Должно быть *h, *H, *d или *D");
                return;
            }
            String reason = message.split(" ", 4)[3];
            ChatManager.SendMessage("/tempmute " + nick + " " + time + " " + reason + " -s");
        }

        else if (message.startsWith("/tban")) {
            event.setCancelled(true);
            if (!PunishmentsHelper.CheckVk()) {
                ChatManager.ClientMessage(Colors.RED + "У вас не установлена ссылка на вк!");
                return;
            }
            String nick = message.split(" ", 4)[1];
            if (onCheck && nick.equals(player)) {
                ChatManager.ClientMessage(Colors.RED + "Вы не можете забанить этого игрока, " +
                        "так как он находится у вас на проверке!");
                return;
            }
            String time = message.split(" ", 4)[2];
            if (!PunishmentsHelper.CheckTimeFormat(time)) {
                ChatManager.ClientMessage(Colors.RED + "Неверный формат времени! Должно быть *h, *H, *d или *D");
                return;
            }
            String reason = message.split(" ", 4)[3];
            ChatManager.SendMessage("/tempban " + nick + " " + time + " " + reason + " | Вопросы? " + ChatManager.GetVkUrl() + " -s");
        }

        else if (message.startsWith("/tbanip")) {
            event.setCancelled(true);
            if (!PunishmentsHelper.CheckVk()) {
                ChatManager.ClientMessage(Colors.RED + "У вас не установлена ссылка на вк!");
                return;
            }
            String nick = message.split(" ", 4)[1];
            String time = message.split(" ", 4)[2];
            if (!PunishmentsHelper.CheckTimeFormat(time)) {
                ChatManager.ClientMessage(Colors.RED + "Неверный формат времени! Должно быть *h, *H, *d или *D");
                return;
            }
            String reason = message.split(" ", 4)[3];
            ChatManager.SendMessage("/banip " + nick + " " + time + " " + reason + " | Вопросы? " + ChatManager.GetVkUrl() + " -s");
            if (onCheck && nick.equals(player)) {
                onCheck = false;
                RenderEvent.SetOnCheck(onCheck);
                player = null;
                FreezerEvent.SetPlayer(player);
                RenderEvent.SetPlayer(player);
            }
        }
    }

    public static void SetPlayer(String value) {
        player = value;
    }

    public static void SetOnCheck(Boolean value) {
        onCheck = value;
    }
}
