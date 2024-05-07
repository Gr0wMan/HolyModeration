package com.holymoderation.addon.events;

import java.util.ArrayList;
import java.util.Arrays;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import com.holymoderation.addon.ChatUtils.ChatManager;
import com.holymoderation.addon.ChatUtils.Colors;

public class FreezerEvent {

    private static Boolean dupeIpEnabled = false;
    private static String texts = null;
    private static String player = null;

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();

        if (message.startsWith("/hmfreezing") || message.startsWith("/hmfrz")) {
            event.setCancelled(true);
            if (player != null) {
                ChatManager.ClientMessage(Colors.RED + "Вы уже проверяете какого-то игрока! " +
                        Colors.RED + "Сначала закончите текущую проверку. --> " + Colors.GOLD + "/unfreezing"
                        + " или " + Colors.GOLD + "/unfrz");
                return;
            }
            player = event.getMessage().split(" ")[1];
            RenderEvent.SetPlayer(player);
            PunishmentsSimplifier.SetPlayer(player);
            RenderEvent.SetOnCheck(true);
            PunishmentsSimplifier.SetOnCheck(true);
            ChatManager.SendMessage("/freezing " + player);
            ChatManager.SendMessage("/checkmute " + player);
            ChatManager.SendMessage("/prova");
            RenderEvent.StopWatchStart();
            if (dupeIpEnabled)
                ChatManager.SendMessage("/dupeip " + player);
            if (texts == null) {
                ChatManager.ClientMessage(Colors.RED + "У вас нет настроенных текстов для отправки! " +
                        "Добавить тексты --> " + Colors.GOLD + "/hmaddtext");
                ChatManager.ClientMessage(Colors.RED + "Просмотреть тексты --> " + Colors.GOLD + "/hmtextlist");
            }
            else
                for (String text : GetSplitTexts()) {
                    ChatManager.SendMessage("/msg " + player + " " + text);
            }
        }

        else if (message.startsWith("/unfreezing") || message.startsWith("/unfrz")) {
            event.setCancelled(true);
            if (!message.split(" ")[1].equals(player)) {
                ChatManager.ClientMessage(Colors.RED + "Этот игрок не находится на вашей проверке! " +
                        Colors.RED + "Для его разморозки используйте" + Colors.GOLD + " /sfreezing" + Colors.RED
                        + " или " + Colors.GOLD + "/sfrz");
                return;
            }
            ChatManager.SendMessage("/freezing " + player);
            ChatManager.SendMessage("/prova");
            player = null;
            RenderEvent.SetOnCheck(false);
            PunishmentsSimplifier.SetOnCheck(false);
            RenderEvent.SetPlayer(player);
            PunishmentsSimplifier.SetPlayer(player);
        }

        else if (message.startsWith("/freezing") || message.startsWith("/frz")) {
            event.setCancelled(true);
            String unFrzPlayer = message.split(" ")[1];
            if (unFrzPlayer.equals(player)) {
                ChatManager.ClientMessage(Colors.RED + "Этот игрок находиться у вас на проверке! " +
                        "Для его разморозки используйте" + Colors.GOLD + " /unfreezing" + Colors.RED
                        + " или " + Colors.GOLD + "/unfrz");
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
