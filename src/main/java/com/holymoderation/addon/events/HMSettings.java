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
            MessageManager.ClientMessage(Colors.AQUA + "Ваша ссылка на вк: " + PunishmentsSimplifier.GetVkUrl());
        }

        else if (message.startsWith("/hmsetvk")) {
            event.setCancelled(true);
            String value = message.split(" ")[1];
            PunishmentsSimplifier.SetVkUrl(value);
            MessageManager.ClientMessage(Colors.GREEN + "Теперь ваша ссылка на вк: " + PunishmentsSimplifier.GetVkUrl());
        }

        else if (message.startsWith("/hmtextadd")) {
            event.setCancelled(true);
            String value = message.split(" ", 2)[1];
            FreezerEvent.AddText(value);
            MessageManager.ClientMessage(Colors.GREEN + "Вы добавили новый текст!");
        }

        else if (message.startsWith("/hmtextremove")) {
            event.setCancelled(true);
            int number = Integer.parseInt(message.split(" ")[1]);
            int index = number - 1;
            if (index >= FreezerEvent.GetSplitTexts().length || number < 0) {
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
            if (message.split(" ").length < 2) {
                MessageManager.ClientMessage(Colors.RED + "Вы не указали новый текст!");
                return;
            }
            int number = Integer.parseInt(message.split(" ")[1]);
            int index = number - 1;
            if (index >= FreezerEvent.GetSplitTexts().length || number < 0) {
                MessageManager.ClientMessage(Colors.RED
                        + "Элемента с таким номером в списке ваших текстов не существует!");
                return;
            }
            String text = message.split(" ", 3)[2];
            FreezerEvent.EditText(index, text);
            MessageManager.ClientMessage(Colors.YELLOW + "Вы изменили текст номер " + Colors.GREEN + number);
        }

        else if (message.equals("/hmtextclear")) {
            event.setCancelled(true);
            FreezerEvent.ClearTexts();
            MessageManager.ClientMessage(Colors.GREEN + "Вы успешно очистили все тектсы!");
        }

        else if (message.startsWith("/hmsetcords")) {
            event.setCancelled(true);
            if (message.split(" ").length < 2) {
                MessageManager.ClientMessage(Colors.RED + "Вы не указали y координаты!");
                return;
            }
            String x = message.split(" ")[1];
            String y = message.split(" ")[2];
            RenderEvent.setxCoords(Integer.parseInt(x));
            RenderEvent.setyCoords(Integer.parseInt(y));
            MessageManager.ClientMessage(Colors.GREEN + "Успешно применено!");
        }
    }
}
