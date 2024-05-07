package com.holymoderation.addon.ChatUtils;

public class PunishmentsManager {
    private static String vkUrl = null;

    public static boolean CheckIncorrectInt(String message) {
        int value = 0; {
            try {
                value = Integer.parseInt(message);
                return(false);
            }
            catch (NumberFormatException e) {
                return(true);
            }
        }
    }

    public static boolean CheckTimeFormat(String message) {
        char lastChar = message.charAt(message.length() - 1);
        if (lastChar != 'h' && lastChar != 'H' && lastChar != 'd' && lastChar != 'D') {
            return false;
        }
        return true;
    }

    public static void Punish(String command, String player, String reason) {
        ChatManager.SendMessage(command + " " + player + " " + reason + " | Вопросы? " + vkUrl + " -s");
    }

    public static void Punish(String command, String player, String time, String reason) {
        ChatManager.SendMessage(command + " " + player + " " + time + " " + reason + " | Вопросы? " + vkUrl + " -s");
    }

    public static String GetVkUrl() {
        return vkUrl;
    }

    public static void SetVkUrl(String value) {
        vkUrl = value;
    }
}
