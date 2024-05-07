package com.holymoderation.addon.ChatUtils;

import com.holymoderation.addon.ChatUtils.ChatManager;

public class PunishmentsHelper {

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

    public static boolean CheckVk() {
        if (ChatManager.GetVkUrl() == null)
            return false;
        return false;
    }
}
