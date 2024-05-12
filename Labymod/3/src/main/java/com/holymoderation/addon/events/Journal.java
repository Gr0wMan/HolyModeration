package com.dezc.labycheck.events;

import com.mojang.realmsclient.client.Request;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;
import net.labymod.main.LabyMod;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Journal {

    @Subscribe
    public void CheckOut(MessageSendEvent event) throws IOException {
        if (event.getMessage().startsWith("/checkout")) {
            String[] messageSplit = event.getMessage().split(" ");
            HolyJournal(messageSplit[1], messageSplit[2], messageSplit[3], messageSplit[4]);
        }
    }

    public static void HolyJournal(String nick, String anarchy, String screenshot, String token) throws IOException {
        try {
            // URL и параметры запроса
            URL url = new URL("https://holyjournal.gay/switch/checkout");
            String params = "csrfmiddlewaretoken=" + token + "&nickname=" + nick + "&server=" + anarchy + "&screenshot=" + screenshot;

            // Установка соединения
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Cookie", "csrftoken=TOKEN; sessionid=SESSID");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            // Передача параметров запроса
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(params);
            writer.flush();
            writer.close();
            os.close();

            // Получение ответа
            int responseCode = connection.getResponseCode();
            LabyMod.getInstance().displayMessageInChat("§e* Внесение проверки: " + responseCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}