package com.holymoderation.addon.events;

import com.holymoderation.addon.HolyModeration;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class FreezerEvent {

    private static Boolean dupeIpEnabled = false;
    private static String texts = null;

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String player;
        String message = event.getMessage();

        if (message.startsWith("/freezing") || message.startsWith("/frz")) {
            event.setCancelled(true);
            player = event.getMessage().split(" ")[1];
            RenderEvent.setPlayer(player);
            RenderEvent.setOnCheck(true);
            PunishmentsSimplifier.SetPlayer(player);
            HolyModeration.SendMessage("/freezing " + player);
            HolyModeration.SendMessage("/checkmute " + player);
            HolyModeration.SendMessage("/prova");
            RenderEvent.StopWatchStart();
            if (dupeIpEnabled)
                HolyModeration.SendMessage("/dupeip " + player);
            for (String text : GetSplitTexts()) {
                if (GetTexts() == null)
                    HolyModeration.ClientMessage("You don't have any texts to write!");
                else
                    HolyModeration.SendMessage("/msg " + player + " " + text);
            }
        }

        else if (message.startsWith("/unfreezing") || message.startsWith("/unfrz")) {
            event.setCancelled(true);
            player = message.split(" ")[1];
            HolyModeration.SendMessage("/freezing " + player);
            HolyModeration.SendMessage("/prova");
            RenderEvent.setPlayer(null);
            RenderEvent.setOnCheck(false);
            PunishmentsSimplifier.SetPlayer(player);
            HolyModeration.ClientMessage("Successfully unfreezed");
        }
    }

    public static Boolean GetDupeIp() {
        return dupeIpEnabled;
    }

    public static String GetTexts() {
        return texts;
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

    public static void RemoveText(String value){
        ArrayList<String> textsarlist = new ArrayList<>(Arrays.asList(texts.split("%", 0)));
        textsarlist.remove(Integer.parseInt(value) - 1);
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
        textsList[index-1] = value;
        texts = null;
        for (String text : textsList) {
            if (texts == null)
                texts = text;
            else
                texts = texts + "%" + text;
        }
    }

    public static void SetDupeIp(boolean value) {
        dupeIpEnabled = value;
    }

    public static String[] GetSplitTexts() {
        if (texts == null)
            return null;
        else
            return texts.split("%", 0);
    }
}
