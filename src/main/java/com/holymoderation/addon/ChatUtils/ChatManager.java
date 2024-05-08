package com.holymoderation.addon.ChatUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;

import net.labymod.core.LabyModCore;

import java.util.Arrays;

public class ChatManager {

    public static String[] punishmentsCommands = {".imute", ".imuteip",
            ".tmute", ".tmuteip", ".iban", ".ibanip", ".tban", ".tbanip"};
    public static String[] settingsCommands = {".hm", ".hmhelp", ".freezing", ".frz",
            ".unfreezing", ".unfrz", ".sban", "/freezing", "/frz", ".textlist", ".textadd",
            ".textremove", ".textedit", ".setvk", ".getvk", ".dupeip", ".setcords", ".savecfg"};
    public static String[] tempPunishments = {".tmute", ".tmuteip", ".tban", ".tbanip"};
    public static String[] infinityPunishments = {".imute", ".imuteip", ".iban", ".ibanip"};
    public static String[] banCommands = {".iban", ".ibanip", ".tban", ".tbanip"};
    public static String[] muteCommands = {".imute", ".imuteip", ".tmute", ".tmuteip"};

    public static void SendMessage(String message) {
        LabyModCore.getMinecraft().getPlayer().sendChatMessage(message);
    }

    public static void ClientMessage(String message) {
        Minecraft.getInstance().player.sendMessage(new StringTextComponent(message), null);
    }

    public static boolean IsArrayContains(String[] array, String value) {
        return Arrays.asList(array).contains(value);
    }
}