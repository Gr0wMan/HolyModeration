package com.holymoderation.addon.ChatUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;

import net.labymod.core.LabyModCore;

import java.util.Arrays;

public class ChatManager {

    public static String[] HelpCommands = {".hm", ".hmhelp"};

    public static String[] FreezerCommands = {".sban", ".freezing", ".frz", ".unfreezing", ".unfrz", "/freezing", "/frz",};

    public static String[] SettingsCommands = {".hm", ".hmhelp", ".textlist", ".textclear", ".textadd",
            ".textremove", ".textedit", ".setvk", ".getvk", ".dupeip", ".setcords", ".savecfg"};
    public static String[] SettingsWithoutArguments = {".textlist", "textclear", ".getvk", ".dupeip", ".savecfg"};
    public static String[] SettingsWithOneArguments = {".textadd", ".textremove", ".setvk"};
    public static String[] SettingsWithTwoArguments = {".textedit",".setcords"};

    public static String[] PunishmentsCommands = {".imute", ".imuteip",
            ".tmute", ".tmuteip", ".iban", ".ibanip", ".tban", ".tbanip"};
    public static String[] TempPunishments = {".tmute", ".tmuteip", ".tban", ".tbanip"};
    public static String[] InfinityPunishments = {".imute", ".imuteip", ".iban", ".ibanip"};
    public static String[] BanCommands = {".iban", ".ibanip", ".tban", ".tbanip"};
    public static String[] MuteCommands = {".imute", ".imuteip", ".tmute", ".tmuteip"};


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