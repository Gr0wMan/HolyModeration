package com.holymoderation.addon.events;

import com.holymoderation.addon.HolyModeration;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

public class HMHelp {
    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        if (message.matches("/hmhelp") || message.matches("/hm")) {
            event.setCancelled(true);
            HolyModeration.ClientMessage("HM Help:");
            HolyModeration.ClientMessage("/hmhelp или /hm покажет это");
            HolyModeration.ClientMessage("/freezing player или /frz player заморозит игрока и начнёт проверку");
            HolyModeration.ClientMessage("/unfreezing player или /unfrz player разморозит игрока и закончит проверку");
            HolyModeration.ClientMessage("/sfreezing или /sfrz player просто разморозит игрока (обычный /freeze)");
            HolyModeration.ClientMessage("/hmsavecfg - сохранить конфиг");
            HolyModeration.ClientMessage("/hmtextlist - показать настроенные тексты");
            HolyModeration.ClientMessage("/hmtextadd text - добавить новый текст");
            HolyModeration.ClientMessage("/hmtextremove number - удалить текст по его номеру");
            HolyModeration.ClientMessage("/hmtextedit number newtext - изменить текст на новый");
            HolyModeration.ClientMessage("/hmsetvk your vk - установить ссылку на вк (для банов)");
            HolyModeration.ClientMessage("/hmgetvk - показать установленный вк");
            HolyModeration.ClientMessage("/hmdupeip - включает/выключает автоматический /dupeip при проверке");
            HolyModeration.ClientMessage("/hmsetcords x y - устанавливить позицию для таймера (считая от левого верхнего угла)");
            HolyModeration.ClientMessage("/sban time reason - забанить игрока, который сейчас на вашей проверке");
        }
    }
}
