package com.holymoderation.addon.ChatUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;

import net.labymod.core.LabyModCore;

public class ChatManager {

    public static void SendMessage(String message) {
        LabyModCore.getMinecraft().getPlayer().sendChatMessage(message);
    }

    public static void ClientMessage(String message) {
        Minecraft.getInstance().player.sendMessage(new StringTextComponent(message), null);
    }
}