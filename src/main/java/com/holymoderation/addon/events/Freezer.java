package com.holymoderation.addon.events;

import java.util.ArrayList;
import java.util.Arrays;

import com.holymoderation.addon.ChatUtils.PunishmentsManager;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import com.holymoderation.addon.ChatUtils.ChatManager;
import com.holymoderation.addon.ChatUtils.Colors;

public class Freezer {

    private static Boolean dupeIpEnabled = false;
    private static String texts = null;
    private static String player = null;

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        String command = message.split(" ")[0];

        if (command.equals(".freezing") || command.equals(".frz")) {
            event.setCancelled(true);
            if (player != null) {
                ChatManager.ClientMessage(Colors.RED + "Вы уже проверяете какого-то игрока! " +
                        Colors.RED + "Сначала закончите текущую проверку. --> " + Colors.GOLD + ".unfreezing"
                        + " или " + Colors.GOLD + ".unfrz");
                return;
            }
            player = event.getMessage().split(" ")[1];
            Render.SetPlayer(player);
            Punishments.SetPlayer(player);
            ChatManager.SendMessage("/freezing " + player);
            ChatManager.SendMessage("/checkmute " + player);
            ChatManager.SendMessage("/prova");
            Render.StopWatchStart();
            if (dupeIpEnabled)
                ChatManager.SendMessage("/dupeip " + player);
            if (texts == null) {
                ChatManager.ClientMessage(Colors.RED + "У вас нет настроенных текстов для отправки! " +
                        "Добавить тексты --> " + Colors.GOLD + ".addtext");
                ChatManager.ClientMessage(Colors.RED + "Просмотреть тексты --> " + Colors.GOLD + ".textlist");
            }
            else
                for (String text : GetSplitTexts()) {
                    ChatManager.SendMessage("/msg " + player + " " + text);
            }
        }

        else if (command.equals(".unfreezing") || message.equals(".unfrz")) {
            event.setCancelled(true);
            if (player == null) {
                ChatManager.ClientMessage(Colors.RED + "Вы никого не проверяете!");
                return;
            }
            ChatManager.SendMessage("/freezing " + player);
            ChatManager.SendMessage("/prova");
            player = null;
            Render.SetPlayer(player);
            Punishments.SetPlayer(player);
        }

        else if (command.equals(".sban")) {
            if (player == null) {
                ChatManager.ClientMessage(Colors.RED + "Вы никого не проверяете!");
                return;
            }
            switch (message.split(" ", 3).length) {
                case (1):
                    ChatManager.ClientMessage(Colors.RED + "Вы не указали время и причину бана!");
                    return;
                case (2):
                    ChatManager.ClientMessage(Colors.RED + "Вы не указали причину бана!");
                    return;
            }
            String time = message.split(" ", 3)[1];
            String reason = message.split(" ", 3)[2];
            if (!PunishmentsManager.CheckTimeFormat(time)) {
                return;
            }
            PunishmentsManager.Punish("/banip", player, time, reason, true);
            ChatManager.SendMessage("/freezing " + player);
            ChatManager.SendMessage("/prova");
            player = null;
            Punishments.SetPlayer(player);
            Render.SetPlayer(player);
        }

        else if (command.equals("/freezing") || command.equals("/frz")) {
            event.setCancelled(true);
            String unFrzPlayer = message.split(" ")[1];
            if (unFrzPlayer.equals(player)) {
                ChatManager.ClientMessage(Colors.RED + "Этот игрок находиться у вас на проверке! " +
                        "Для его разморозки используйте" + Colors.GOLD + " .unfreezing" + Colors.RED
                        + " или " + Colors.GOLD + ".unfrz");
                return;
            }
            ChatManager.SendMessage("/freezing " + unFrzPlayer);
        }
    }

    public static Boolean GetDupeIp() {
        return dupeIpEnabled;
    }

    public static String GetTexts() {
        return texts;
    }

    public static String[] GetSplitTexts() {
        if (texts == null)
            return null;
        else
            return texts.split("%", 0);
    }

    public static void SetPlayer(String value) {
        player = value;
    }

    public static void SetTexts(String value) {
        texts = value;
    }

    public static void AddText(String value) {
        if (value == null)
            return;

        if (texts == null)
            texts = value;
        else
            texts = texts + "%" + value;
    }

    public static void RemoveText(int index){
        ArrayList<String> textsarlist = new ArrayList<>(Arrays.asList(texts.split("%", 0)));
        textsarlist.remove(index);
        texts = null;
        for (int i = 0; i < textsarlist.size(); i++) {
            if (texts == null)
                texts = textsarlist.get(i);
            else
                texts = texts + "%" + textsarlist.get(i);
        }
    }

    public static void EditText(int index, String value) {
        String[] textsList = texts.split("%");
        textsList[index] = value;
        texts = null;
        for (String text : textsList) {
            if (texts == null)
                texts = text;
            else
                texts = texts + "%" + text;
        }
    }

    public static void ClearTexts() {
        texts = null;
    }

    public static void SetDupeIp(boolean value) {
        dupeIpEnabled = value;
    }
}
