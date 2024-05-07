package com.holymoderation.addon.ChatUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;

import net.labymod.core.LabyModCore;

public class ChatManager {

    private static String vkUrl = null;

    public static void SendMessage(String message) {
        LabyModCore.getMinecraft().getPlayer().sendChatMessage(message);
    }

    public static void ClientMessage(String message) {
        Minecraft.getInstance().player.sendMessage(new StringTextComponent(message), null);
    }

    public static void GivePunishment(String command, String player, String reason) {
        SendMessage(command + " " + player + " " + reason + " | Вопросы? " + vkUrl + " -s");
    }

    public static void GivePunishment(String command, String player, String reason, String time) {
        SendMessage(command + " " + player + " " + time + " " + reason + " | Вопросы? " + vkUrl + " -s");
    }

    public static String GetVkUrl() {
        return vkUrl;
    }

    public static void SetVkUrl(String value) {
        vkUrl = value;
    }
}