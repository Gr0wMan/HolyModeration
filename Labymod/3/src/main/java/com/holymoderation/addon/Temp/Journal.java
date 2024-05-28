package com.holymoderation.addon.Temp;

import com.holymoderation.addon.utils.ChatManager;
import com.holymoderation.addon.utils.Colors;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Journal {
    private static String token;

    @Subscribe
    public void JournalEvent(MessageSendEvent event) throws IOException {
        String message = event.getMessage();

        if (message.startsWith("/checkout")) {
            event.setCancelled(true);
            String[] messageSplit = message.split(" ");
            switch (messageSplit.length) {
                case (1):
                    ChatManager.ClientMessage(Color.RED + "Вы не указали ник игрока, режим и скриншот");
                    return;
                case (2):
                    ChatManager.ClientMessage(Color.RED + "Вы не указали режим и скриншот");
                    return;
                case (3):
                    ChatManager.ClientMessage(Color.RED + "Вы не указали скриншот");
                    return;
                case (4):
                    CheckOut(messageSplit[1], messageSplit[2], messageSplit[3]);
                    break;
                case (5):
                    CheckOut(messageSplit[1], messageSplit[2] + " " + messageSplit[3], messageSplit[4]);
                    break;
            }
        }

        else if (message.startsWith("/switch")) {
            event.setCancelled(true);
            String[] messageSplit = message.split(" ");
            if (messageSplit.length == 1) {
                ChatManager.ClientMessage(Colors.RED + "Вы не указали значение!");
                return;
            }
            SwitchOnline(messageSplit[1]);
        }
    }

    private static void CheckOut(String nick, String anarchy, String screenshot) throws IOException {
        ChatManager.ClientMessage(nick + " " + anarchy + " " + screenshot);
        /*try {
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
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }*/
    }

    private static void SwitchOnline(String state) {

        if (state.equals("1")) {
            try {
                // URL и параметры запроса
                URL url = new URL("https://holyjournal.gay/switch/online");
                String params = "csrfmiddlewaretoken=" + token;

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
                ChatManager.ClientMessage(Colors.GOLD + "Ответ: " + responseCode);
            }
            catch (IOException e) {
                e.printStackTrace();
                return;
            }
            ChatManager.ClientMessage(Colors.GREEN + "Вы включили онлайн!");
        }
        else if (state.equals("0")) {
            ChatManager.ClientMessage(Colors.RED + "Вы выключили онлайн!");
        }
        else {
            ChatManager.ClientMessage(Colors.RED + "Неверное значение!");
        }
    }

    public static void SetToken(String value) {
        token = value;
        ChatManager.ClientMessage("Токен: " + token);
    }
}