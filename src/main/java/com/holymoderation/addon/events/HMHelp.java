package com.holymoderation.addon.events;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import com.holymoderation.addon.ChatUtils.Colors;
import com.holymoderation.addon.ChatUtils.MessageManager;

public class HMHelp {

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        if (message.matches("/hmhelp") || message.matches("/hm")) {
            event.setCancelled(true);
            MessageManager.ClientMessage(Colors.BLUE + "HM Help:");
            MessageManager.ClientMessage(Colors.GOLD + "/hmhelp" + Colors.RESET + " или "
                    + Colors.GOLD + "/hm" + Colors.RESET + " - показывает HM Help");
            MessageManager.ClientMessage(Colors.GOLD + "/freezing" + Colors.GREEN + " player" + Colors.RESET
                    + " или " + Colors.GOLD + "/frz" + Colors.GREEN + " player" + Colors.RESET
                    + " - замораживает игрока и начинает проверку");
            MessageManager.ClientMessage(Colors.GOLD + "/unfreezing" + Colors.GREEN + " player" + Colors.RESET
                    + " или " + Colors.GOLD + "/unfrz" + Colors.GREEN + " player" + Colors.RESET
                    + " - размораживает игрока и заканчивает проверку");
            MessageManager.ClientMessage(Colors.GOLD + "/sfreezing" + Colors.GREEN + " player" + Colors.RESET
                    + " или " + Colors.GOLD + "/sfrz" + Colors.GREEN + " player" + Colors.RESET
                    + " - просто замораживает/размораживает игрока (обычный /freeze), " +
                    "при условии, что он не находится на вашей проверке");
            MessageManager.ClientMessage(Colors.GOLD + "/sban" + Colors.GREEN + " time reason" + Colors.RESET
                    + " - банит игрока, который сейчас на вашей проверке");
            MessageManager.ClientMessage(Colors.GOLD + "/hmtextlist" + Colors.RESET + Colors.RESET
                    + " - показывает настроенные тексты");
            MessageManager.ClientMessage(Colors.GOLD + "/hmtextadd" + Colors.GREEN + " text" + Colors.RESET
                    + " - добавляет новый текст");
            MessageManager.ClientMessage(Colors.GOLD + "/hmtextremove" + Colors.GREEN + " number" + Colors.RESET
                    + " - удаляет текст по его номеру");
            MessageManager.ClientMessage(Colors.GOLD + "/hmtextedit" + Colors.GREEN + " number newtext" + Colors.RESET
                    + " - изменяет текст на новый");
            MessageManager.ClientMessage(Colors.GOLD + "/hmsetvk" + Colors.GREEN + " your vk" + Colors.RESET
                    + " - установливает ссылку на вк (для банов), устанавливайте в формате 'vk.com/id'");
            MessageManager.ClientMessage(Colors.GOLD + "/hmgetvk" + Colors.RESET
                    + " - показывает установленный вк");
            MessageManager.ClientMessage(Colors.GOLD + "/hmdupeip" + Colors.RESET
                    + " - включает/выключает автоматический /dupeip при проверке");
            MessageManager.ClientMessage(Colors.GOLD + "/hmsetcords" + Colors.GREEN + " x y" + Colors.RESET
                    + " - устанавливает позицию для таймера (считая от левого верхнего угла)");
            MessageManager.ClientMessage(Colors.GOLD + "/hmsavecfg" + Colors.RESET + Colors.RESET
                    + " - сохраняет конфиг");
        }
    }
}
