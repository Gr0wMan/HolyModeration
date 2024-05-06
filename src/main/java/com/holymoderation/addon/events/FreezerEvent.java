package com.holymoderation.addon.events;

import com.holymoderation.addon.HolyModeration;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class FreezerEvent {

    private static Boolean dupeIpEnabled = false;
    private static String vkUrl;
    private static String texts = "";

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        boolean defFrz = false;
        boolean advFrz = false;
        String player;
        String message = event.getMessage();

        if (message.startsWith("/freezing"))
            defFrz = true;
        else if (message.startsWith("/frz"))
            advFrz = true;

        if (defFrz || advFrz) {
            event.setCancelled(true);
            player = event.getMessage().split(" ")[1];
            if (advFrz)
                HolyModeration.SendMessage("/freezing " + player);
            HolyModeration.SendMessage("/checkmute " + player);
            HolyModeration.SendMessage("/prova");
            HolyModeration.SendMessage("/afk");
            if (dupeIpEnabled)
                HolyModeration.SendMessage("/dupeip " + player);
            for (String text : GetSplitTexts()){
                if (texts != null)
                    HolyModeration.SendMessage("/msg " + player + " " + text);
                else
                    HolyModeration.ClientMessage("You don't have any texts, so.. add them!");
            }
        }
    }

    public static String GetVkUrl() {
        return vkUrl;
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

        if (texts == "")
            texts = value;
        else
            texts = texts + "%" + value;
    }

    public static void RemoveText(String value){
        ArrayList<String> textsarlist = new ArrayList<>(Arrays.asList(texts.split("%", 0)));
        textsarlist.remove(Integer.parseInt(value) - 1);
        texts = "";
        for (int i = 0; i < textsarlist.size(); i++) {
            if (texts == "")
                texts = textsarlist.get(i);
            else
                texts = texts + "%" + textsarlist.get(i);
        }
    }

    public static void SetVkUrl(String value) {
        vkUrl = value;
    }

    public static void SetDupeIp(boolean value) {
        dupeIpEnabled = value;
    }

    public static String[] GetSplitTexts() {
        if (texts == "")
            return null;
        else
            return texts.split("%", 0);
    }
}
