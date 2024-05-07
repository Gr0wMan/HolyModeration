package com.holymoderation.addon.events;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import com.holymoderation.addon.ChatUtils.MessageManager;
import com.holymoderation.addon.ChatUtils.Colors;



public class HMSettings {

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        if (message.matches("/hmtextlist")) {
            event.setCancelled(true);
            MessageManager.ClientMessage(Colors.AQUA + "Список ваших текстов:");
            if (FreezerEvent.GetTexts() == null)
                MessageManager.ClientMessage(Colors.RED + "У вас нет настроенных текстов");
            else
                for (int i = 0; i < FreezerEvent.GetSplitTexts().length; i++)
                {
                    MessageManager.ClientMessage((i+1) + ". " + FreezerEvent.GetSplitTexts()[i]);
                }
        }

        else if (message.matches("/hmdupeip")) {
            event.setCancelled(true);
            FreezerEvent.SetDupeIp(!FreezerEvent.GetDupeIp());
            if (FreezerEvent.GetDupeIp())
                MessageManager.ClientMessage(Colors.YELLOW + "Автоматический /dupeip" + Colors.GREEN + " ВКЛЮЧЁН");
            else
                MessageManager.ClientMessage(Colors.YELLOW + "Автоматический /dupeip" + Colors.RED + " ВЫКЛЮЧЕН");
        }

        else if (message.matches("/hmgetvk")) {
            event.setCancelled(true);
            if (PunishmentsSimplifier.GetVkUrl() == null) {
                MessageManager.ClientMessage(Colors.RED + "У вас не установлена ссылка на вк!");
            }
            MessageManager.ClientMessage(Colors.AQUA + "Ваша ссылка на вк: " + PunishmentsSimplifier.GetVkUrl());
        }

        else if (message.startsWith("/hmsetvk")) {
            event.setCancelled(true);
            if (message.split(" ").length == 1) {
                MessageManager.ClientMessage(Colors.RED + "Вы не указали ссылку на вк!");
            }
            String value = message.split(" ", 2)[1];
            boolean hasSpaces = false;
            for (int i = 0; i < value.length(); i++)
                if (value.charAt(i) == ' ')
                    hasSpaces = true;
            if (hasSpaces) {
                MessageManager.ClientMessage(Colors.RED + "В сообщении обнаружены пробелы, пожалуйста, " +
                        "указывайте ссылку на вк в формате 'vk.com/id'");
            }
            PunishmentsSimplifier.SetVkUrl(value);
            MessageManager.ClientMessage(Colors.GREEN + "Теперь ваша ссылка на вк: " + PunishmentsSimplifier.GetVkUrl());
        }

        else if (message.startsWith("/hmtextadd")) {
            event.setCancelled(true);
            if (message.split(" ").length == 1) {
                MessageManager.ClientMessage(Colors.RED + "Вы не указали текст!");
            }
            String value = message.split(" ", 2)[1];
            FreezerEvent.AddText(value);
            MessageManager.ClientMessage(Colors.GREEN + "Вы добавили новый текст!");
        }

        else if (message.startsWith("/hmtextremove")) {
            event.setCancelled(true);
            if (FreezerEvent.GetTexts() == null) {
                MessageManager.ClientMessage(Colors.RED + "У вас нет настроенных текстов");
            }
            int index; {
                try {
                    index = Integer.parseInt(message.split(" ", 2)[1]) - 1;
                }
                catch (NumberFormatException e) {
                    MessageManager.ClientMessage(Colors.RED + "Некорректный номер текста!");
                    return;
                }
            }
            if (message.split(" ").length == 1) {
                MessageManager.ClientMessage(Colors.RED + "Вы не указали номер текста!");
            }
            if (index >= FreezerEvent.GetSplitTexts().length || index < 0) {
                MessageManager.ClientMessage(Colors.RED
                        + "Элемента с таким номером в списке ваших текстов не существует!");
                return;
            }
            FreezerEvent.RemoveText(index);
            MessageManager.ClientMessage(Colors.RED + "Вы удалили текст номер "
                    + Colors.GREEN + message.split(" ", 0)[1] + "!");
        }

        else if (message.startsWith("/hmtextedit")) {
            event.setCancelled(true);
            if (message.split(" ").length == 1) {
                MessageManager.ClientMessage(Colors.RED + "Вы не указали номер текста и новый текст!");
                return;
            }
            else if (message.split(" ").length == 2) {
                MessageManager.ClientMessage(Colors.RED + "Вы не указали новый текст!");
                return;
            }
            int index; {
                try {
                    index = Integer.parseInt(message.split(" ")[1]) - 1;
                }
                catch (NumberFormatException e) {
                    MessageManager.ClientMessage(Colors.RED + "Некорректный номер текста!");
                    return;
                }
            }
            if (FreezerEvent.GetTexts() == null) {
                MessageManager.ClientMessage(Colors.RED + "У вас нет настроенных текстов");
            }
            if (index >= FreezerEvent.GetSplitTexts().length || index < 0) {
                MessageManager.ClientMessage(Colors.RED
                        + "Элемента с таким номером в списке ваших текстов не существует!");
                return;
            }
            String text = message.split(" ", 3)[2];
            FreezerEvent.EditText(index, text);
            MessageManager.ClientMessage(Colors.YELLOW + "Вы изменили текст номер " + Colors.GREEN + (index + 1));
        }

        else if (message.equals("/hmtextclear")) {
            event.setCancelled(true);
            FreezerEvent.ClearTexts();
            MessageManager.ClientMessage(Colors.GREEN + "Вы успешно очистили все тектсы!");
        }

        else if (message.startsWith("/hmsetcords")) {
            event.setCancelled(true);
            if (message.split(" ").length == 1) {
                MessageManager.ClientMessage(Colors.RED + "Вы не указали x и y координаты!");
                return;
            }
            else if (message.split(" ").length == 2) {
                MessageManager.ClientMessage(Colors.RED + "Вы не указали y координаты!");
                return;
            }
            boolean incorrectX;
            boolean incorrectY;
            int x = 0; {
                try {
                    x = Integer.parseInt(message.split(" ", 3)[1]);
                    incorrectX = false;
                }
                catch (NumberFormatException e) {
                    incorrectX = true;
                }
            }
            int y = 0; {
                try {
                    y = Integer.parseInt(message.split(" ", 3)[2]);
                    incorrectY = false;
                }
                catch (NumberFormatException e) {
                    incorrectY = true;
                }
            }
            if (incorrectX || incorrectY) {
                if (incorrectX && incorrectY)
                    MessageManager.ClientMessage("Некорректные координаты X и Y!");
                else if (!incorrectX && incorrectY)
                    MessageManager.ClientMessage("Некорректная координата X!");
                else if (incorrectX && !incorrectY)
                    MessageManager.ClientMessage("Некорректная координата Y!");
                return;
            }
            RenderEvent.setxCoords(x);
            RenderEvent.setyCoords(y);
            MessageManager.ClientMessage(Colors.GREEN + "Успешно применено!");
        }
    }
}
