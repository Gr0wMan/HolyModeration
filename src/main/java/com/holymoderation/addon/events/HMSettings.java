package com.holymoderation.addon.events;

import com.holymoderation.addon.HolyModeration;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

public class HMSettings {

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        if (message.matches("/hmtextlist")) {
            event.setCancelled(true);
            HolyModeration.ClientMessage("Список ваших текстов:");
            if (FreezerEvent.GetTexts() == null)
                HolyModeration.ClientMessage("У вас нет настроенных текстов");
            else
                for (int i = 0; i < FreezerEvent.GetSplitTexts().length; i++)
                {
                    HolyModeration.ClientMessage((i+1) + ". " + FreezerEvent.GetSplitTexts()[i]);
                }
        }

        else if (message.matches("/hmdupeip")) {
            event.setCancelled(true);
            FreezerEvent.SetDupeIp(!FreezerEvent.GetDupeIp());
            if (FreezerEvent.GetDupeIp())
                HolyModeration.ClientMessage("автоматический /dupeip ВКЛЮЧЁН");
            else
                HolyModeration.ClientMessage("автоматический /dupeip ВЫКЛЮЧЕН");
        }

        else if (message.matches("/hmgetvk")) {
            event.setCancelled(true);
            HolyModeration.ClientMessage("Ваша ссылка на вк: " + PunishmentsSimplifier.GetVkUrl());
        }

        else if (message.startsWith("/hmsetvk")) {
            event.setCancelled(true);
            String value = message.split(" ")[1];
            PunishmentsSimplifier.SetVkUrl(value);
            HolyModeration.ClientMessage("Теперь ваша ссылка на вк: " + PunishmentsSimplifier.GetVkUrl());
        }

        else if (message.startsWith("/hmtextadd")) {
            event.setCancelled(true);
            String value = message.split(" ", 2)[1];
            FreezerEvent.AddText(value);
            HolyModeration.ClientMessage("Вы добавили новый текст!");
        }

        else if (message.startsWith("/hmtextremove")) {
            event.setCancelled(true);
            String value = message.split(" ")[1];
            FreezerEvent.RemoveText(value);
            HolyModeration.ClientMessage("Вы удалили текст номер  " + message.split(" ", 0)[1] + "!");
        }

        else if (message.startsWith("/hmtextedit")) {
            event.setCancelled(true);
            int number = Integer.parseInt(message.split(" ")[1]);
            String text = message.split(" ", 3)[2];
            FreezerEvent.EditText(number, text);
            HolyModeration.ClientMessage("Вы изменили текст номер " + number);
        }

        else if (message.startsWith("/hmsetcords")) {
            event.setCancelled(true);
            String x = message.split(" ")[1];
            String y = message.split(" ")[2];
            RenderEvent.setxCoords(Integer.parseInt(x));
            RenderEvent.setyCoords(Integer.parseInt(y));
            HolyModeration.ClientMessage("Успешно применено!");
        }
    }
}
