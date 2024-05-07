package com.holymoderation.addon.events;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import com.holymoderation.addon.ChatUtils.ChatManager;
import com.holymoderation.addon.ChatUtils.Colors;
import com.holymoderation.addon.ChatUtils.PunishmentsManager;

public class Punishments {
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
            if (PunishmentsManager.GetVkUrl() == null) {
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
            String time = message.split(" ", 3)[1];
            if (!(time.equals("20d")) && !(time.equals("20D")) && !(time.equals("30d")) && !(time.equals("30D"))){
                ChatManager.ClientMessage(Colors.RED + "Некорректное время бана! (Должно быть 20d/20D или 30d/20D)");
                return;
            }
            String reason = message.split(" ", 3)[2];
            PunishmentsManager.Punish("/banip", player, time, reason);
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
            PunishmentsManager.Punish("/mute", nick, reason);
            ChatManager.SendMessage("/mute " + nick + " " + reason + " -s");
        }

        else if (message.startsWith("/iban")) {
            event.setCancelled(true);
            if (PunishmentsManager.GetVkUrl() == null) {
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
            PunishmentsManager.Punish("/ban", nick, reason);
        }

        else if (message.startsWith("/ibanip")) {
            event.setCancelled(true);
            if (PunishmentsManager.GetVkUrl() == null) {
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
            PunishmentsManager.Punish("/banip", nick, reason);
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
            if (!PunishmentsManager.CheckTimeFormat(time)) {
                ChatManager.ClientMessage(Colors.RED + "Неверный формат времени! Должно быть *h, *H, *d или *D");
                return;
            }
            String reason = message.split(" ", 4)[3];
            PunishmentsManager.Punish("/tempmute", nick, time, reason);
        }

        else if (message.startsWith("/tban")) {
            event.setCancelled(true);
            if (PunishmentsManager.GetVkUrl() == null) {
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
            if (!PunishmentsManager.CheckTimeFormat(time)) {
                ChatManager.ClientMessage(Colors.RED + "Неверный формат времени! Должно быть *h, *H, *d или *D");
                return;
            }
            String reason = message.split(" ", 4)[3];
            PunishmentsManager.Punish("/tempban", nick, time, reason);
        }

        else if (message.startsWith("/tbanip")) {
            event.setCancelled(true);
            if (PunishmentsManager.GetVkUrl() == null) {
                ChatManager.ClientMessage(Colors.RED + "У вас не установлена ссылка на вк!");
                return;
            }
            String nick = message.split(" ", 4)[1];
            String time = message.split(" ", 4)[2];
            if (!PunishmentsManager.CheckTimeFormat(time)) {
                ChatManager.ClientMessage(Colors.RED + "Неверный формат времени! Должно быть *h, *H, *d или *D");
                return;
            }
            String reason = message.split(" ", 4)[3];
            PunishmentsManager.Punish("/banip", nick, time, reason);
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
