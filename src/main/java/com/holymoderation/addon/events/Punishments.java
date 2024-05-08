package com.holymoderation.addon.events;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import com.holymoderation.addon.ChatUtils.ChatManager;
import com.holymoderation.addon.ChatUtils.Colors;
import com.holymoderation.addon.ChatUtils.PunishmentsManager;

public class Punishments {
    private static String player = null;
    private static String[] messageSplit;

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        String command = message.split(" ")[0];

        if (PunishmentsManager.IsArrayContains(ChatManager.punishmentsCommands, command)) {
            event.setCancelled(true);
            if (PunishmentsManager.IsArrayContains(ChatManager.tempPunishments, command)) {
                messageSplit = message.split(" ", 4);
                String nick = messageSplit[1];
                String time = messageSplit[2];
                String reason = messageSplit[3];
                if (PunishmentsManager.CheckPlayerOnCheck(player, nick)) {
                    return;
                }
                if (!PunishmentsManager.CheckTimeFormat(time)) {
                    return;
                }
                if (PunishmentsManager.IsArrayContains(ChatManager.muteCommands, command)) {
                    switch (messageSplit.length) {
                        case (1):
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали ник игрока, время и причину мута!");
                            return;
                        case (2):
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали время и причину мута!");
                            return;
                        case (3):
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали причину мута!");
                            return;
                    }
                    PunishmentsManager.Punish(command, nick, time, reason, false);
                }
                if (PunishmentsManager.IsArrayContains(ChatManager.banCommands, command)) {
                    if (!PunishmentsManager.CheckVK()) {
                        return;
                    }
                    switch (messageSplit.length) {
                        case (1):
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали ник игрока, время и причину бана!");
                            return;
                        case (2):
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали время и причину бана!");
                            return;
                        case (3):
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали причину бана!");
                            return;
                    }
                    PunishmentsManager.Punish(command, nick, time, reason, true);
                }
            }
            else if (PunishmentsManager.IsArrayContains(ChatManager.infinityPunishments, command)) {
                messageSplit = message.split(" ", 3);
                String nick = messageSplit[1];
                String reason = messageSplit[2];
                if (PunishmentsManager.CheckPlayerOnCheck(player, nick)) {
                    return;
                }
                if (PunishmentsManager.IsArrayContains(ChatManager.muteCommands, command)) {
                    switch (messageSplit.length) {
                        case (1):
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали ник игрока и причину мута!");
                            return;
                        case (2):
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали причину мута!");
                            return;
                    }
                    PunishmentsManager.Punish(command, nick, reason, false);
                }
                if (PunishmentsManager.IsArrayContains(ChatManager.banCommands, command)) {
                    if (!PunishmentsManager.CheckVK()) {
                        return;
                    }
                    switch (messageSplit.length) {
                        case (1):
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали ник игрока и причину бана!");
                            return;
                        case (2):
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали причину бана!");
                            return;
                    }
                    PunishmentsManager.Punish(command, nick, reason, true);
                }
            }
        }
    }

    public static void SetPlayer(String value) {
        player = value;
    }
}