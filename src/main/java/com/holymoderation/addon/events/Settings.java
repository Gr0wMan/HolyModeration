package com.holymoderation.addon.events;

import com.labymedia.connect.api.chat.Chat;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import com.holymoderation.addon.utils.ChatManager;
import com.holymoderation.addon.utils.Colors;
import com.holymoderation.addon.utils.PunishmentsManager;

import java.awt.*;

public class Settings {
    private static String[] messageSplit;

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        String command = message.split(" ")[0];

        if (ChatManager.IsArrayContains(ChatManager.SettingsCommands, command)) {
            event.setCancelled(true);
            if (ChatManager.IsArrayContains(ChatManager.SettingsWithoutArguments, command)) {
                switch (command) {
                    case (".textlist"):
                        ChatManager.ClientMessage(Colors.AQUA + "Список ваших текстов:");
                        if (Freezer.GetTexts() == null) {
                            ChatManager.ClientMessage(Colors.RED + "У вас нет настроенных текстов");
                        }
                        else {
                            for (int i = 0; i < Freezer.GetSplitTexts().length; i++) {
                                ChatManager.ClientMessage((i+1) + ". " + Freezer.GetSplitTexts()[i]);
                            }
                        }
                        break;
                    case (".textclear"):
                        Freezer.ClearTexts();
                        ChatManager.ClientMessage(Colors.GREEN + "Вы успешно очистили все тексты!");
                        break;
                    case (".getvk"):
                        if (PunishmentsManager.GetVkUrl() == null) {
                            ChatManager.ClientMessage(Colors.RED + "У вас не установлена ссылка на вк!");
                            return;
                        }
                        ChatManager.ClientMessage(Colors.AQUA + "Ваша ссылка на вк: " + PunishmentsManager.GetVkUrl());
                        break;
                    case (".dupeip"):
                        Freezer.SetDupeIp(!Freezer.GetDupeIp());
                        if (Freezer.GetDupeIp()) {
                            ChatManager.ClientMessage(Colors.YELLOW + "Автоматический /dupeip" + Colors.GREEN + " ВКЛЮЧЁН");
                        }
                        else {
                            ChatManager.ClientMessage(Colors.YELLOW + "Автоматический /dupeip" + Colors.RED + " ВЫКЛЮЧЕН");
                        }
                        break;
                }
            }
            else if (ChatManager.IsArrayContains(ChatManager.SettingsWithOneArguments, command)) {
                messageSplit = message.split(" ", 2);
                switch (command) {
                    case (".setvk"):
                        if (messageSplit.length == 1) {
                            ChatManager.ClientMessage(Colors.RED + "Вы не ввели ссылку на вк!");
                            return;
                        }
                        String vkUrl = messageSplit[1];
                        boolean hasSpaces = false;
                        for (int i = 0; i < vkUrl.length(); i++) {
                            if (vkUrl.charAt(i) == ' ') {
                                hasSpaces = true;
                            }
                        }
                        if (hasSpaces) {
                            ChatManager.ClientMessage(Colors.RED + "В ссылке обнаружены пробелы, пожалуйста, " +
                                    "указывайте ссылку на вк в формате 'vk.com/id'");
                            return;
                        }
                        PunishmentsManager.SetVkUrl(vkUrl);
                        ChatManager.ClientMessage(Colors.GREEN + "Теперь ваша ссылка на вк: " + PunishmentsManager.GetVkUrl());
                        break;
                    case (".textadd"):
                        if (messageSplit.length == 1) {
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали текст!");
                            return;
                        }
                        String text = messageSplit[1];
                        Freezer.AddText(text);
                        ChatManager.ClientMessage(Colors.GREEN + "Вы добавили новый текст!");
                        break;
                    case (".textremove"):
                        if (Freezer.GetTexts() == null) {
                            ChatManager.ClientMessage(Colors.RED + "У вас нет настроенных текстов");
                            return;
                        }
                        if (messageSplit.length == 1) {
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали номер текста!");
                            return;
                        }
                        String indexText = messageSplit[1];
                        if (!ChatManager.CheckCorrectInt(indexText)) {
                            ChatManager.ClientMessage(Colors.RED + "Некорректный номер текста!");
                            return;
                        }
                        int intIndex = Integer.parseInt(indexText) - 1;
                        if (intIndex >= Freezer.GetSplitTexts().length || intIndex < 0) {
                            ChatManager.ClientMessage(Colors.RED
                                    + "Элемента с таким номером в списке ваших текстов не существует!");
                            return;
                        }
                        Freezer.RemoveText(intIndex);
                        ChatManager.ClientMessage(Colors.RED + "Вы удалили текст номер "
                                + Colors.GREEN + messageSplit[1] + "!");
                        break;
                    case (".setrainbowdelay"):
                        String stringDelay = messageSplit[1];
                        if (!ChatManager.CheckCorrectInt(stringDelay)) {
                            ChatManager.ClientMessage(Colors.RED + "Некорректная задержка!");
                            return;
                        }
                        Render.SetRainbowDelay(Integer.parseInt(stringDelay));
                        ChatManager.ClientMessage(Colors.GREEN + "Успешно применено!");
                        break;
                    case (".setcolor"):
                        String stringColor = messageSplit[1];
                        if (!ChatManager.CheckCorrectInt(stringColor)) {
                            ChatManager.ClientMessage(Colors.RED + "Некорректный цветовой код!");
                            return;
                        }
                        Render.SetCustomColor(Integer.parseInt(stringColor));
                        ChatManager.ClientMessage(Colors.GREEN + "Успешно применено!");
                        break;
                }
            }
            else if (ChatManager.IsArrayContains(ChatManager.SettingsWithTwoArguments, command)) {
                messageSplit = message.split(" ", 3);
                switch (command) {
                    case (".textedit"):
                        if (Freezer.GetTexts() == null) {
                            ChatManager.ClientMessage(Colors.RED + "У вас нет настроенных текстов");
                            return;
                        }
                        if (messageSplit.length == 1) {
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали номер текста и новый текст!");
                            return;
                        }
                        String indexText = messageSplit[1];
                        if (!ChatManager.CheckCorrectInt(indexText)) {
                            ChatManager.ClientMessage(Colors.RED + "Некорректный номер текста!");
                            return;
                        }
                        int index = Integer.parseInt(indexText) - 1;
                        if (messageSplit.length == 2) {
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали новый текст!");
                            return;
                        }
                        if (index >= Freezer.GetSplitTexts().length || index < 0) {
                            ChatManager.ClientMessage(Colors.RED
                                    + "Элемента с таким номером в списке ваших текстов не существует!");
                            return;
                        }
                        String text = messageSplit[2];
                        Freezer.EditText(index, text);
                        ChatManager.ClientMessage(Colors.YELLOW + "Вы изменили текст номер " + Colors.GREEN + (index + 1));
                        break;
                    case (".setcords"):
                        if (messageSplit.length == 1) {
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали X и Y координаты!");
                            return;
                        }
                        else if (messageSplit.length == 2) {
                            ChatManager.ClientMessage(Colors.RED + "Вы не указали Y координату!");
                            return;
                        }
                        String xText = messageSplit[1];
                        String yText = messageSplit[2];
                        boolean isXcorrect = ChatManager.CheckCorrectInt(xText);
                        boolean isYcorrect = ChatManager.CheckCorrectInt(yText);

                        if (!isXcorrect || !isYcorrect) {
                            if (!isXcorrect && !isYcorrect)
                                ChatManager.ClientMessage(Colors.RED + "Некорректные X и Y координаты!");
                            else if (!isXcorrect && isYcorrect)
                                ChatManager.ClientMessage(Colors.RED + "Некорректная X координата!");
                            else if (isXcorrect && !isYcorrect)
                                ChatManager.ClientMessage(Colors.RED + "Некорректная Y координата!");
                            return;
                        }

                        Render.SetxCoords(Integer.parseInt(xText));
                        Render.SetyCoords(Integer.parseInt(yText));
                        ChatManager.ClientMessage(Colors.GREEN + "Успешно применено!");
                        break;
                }
            }
        }
    }
}
