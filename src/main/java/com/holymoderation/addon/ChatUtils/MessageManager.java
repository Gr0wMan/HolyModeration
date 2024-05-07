package com.holymoderation.addon.ChatUtils;

import net.labymod.core.LabyModCore;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;

public final class MessageManager {

    public static void SendMessage(String message) {
        LabyModCore.getMinecraft().getPlayer().sendChatMessage(message);
    }

    public static void ClientMessage(String message) {
        Minecraft.getInstance().player.sendMessage(new StringTextComponent(message), null);
    }
}