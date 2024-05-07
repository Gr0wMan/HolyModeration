package com.holymoderation.addon.events;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import com.holymoderation.addon.ChatUtils.Colors;
import com.holymoderation.addon.ChatUtils.ChatManager;

public class HMHelp {

    @Subscribe
    public void OnUpdate(MessageSendEvent event) {
        String message = event.getMessage();
        if (message.equals(".help")) {
            event.setCancelled(true);
            ChatManager.ClientMessage(Colors.BLUE + "HM Help:");
            ChatManager.ClientMessage(Colors.GOLD + ".help" + Colors.RESET + " или "
                    + Colors.GOLD + "." + Colors.RESET + " - показывает HM Help");
            ChatManager.ClientMessage("");
            ChatManager.ClientMessage(Colors.DARK_RED + "ПОМОЩЬ ПО ПРОВЕРКАМ:");
            ChatManager.ClientMessage(Colors.GOLD + ".freezing" + Colors.GREEN + " player" + Colors.RESET
                    + " или " + Colors.GOLD + ".frz" + Colors.GREEN + " player" + Colors.RESET
                    + " - замораживает игрока и начинает проверку");
            ChatManager.ClientMessage(Colors.GOLD + ".unfreezing" + Colors.RESET
                    + " или " + Colors.GOLD + ".unfrz" + Colors.RESET
                    + " - размораживает игрока, который находится на вашей проверке и заканчивает проверку");
            ChatManager.ClientMessage(Colors.GOLD + ".sban" + Colors.GREEN + " time reason" + Colors.RESET
                    + " - банит игрока, который сейчас на вашей проверке");
            ChatManager.ClientMessage(Colors.GOLD + "/freezing" + Colors.GREEN + " player" + Colors.RESET
                    + " или " + Colors.GOLD + "/frz" + Colors.GREEN + " player" + Colors.RESET
                    + " - просто замораживает/размораживает игрока, " +
                    "при условии, что он не находится на вашей проверке");
            ChatManager.ClientMessage("");
            ChatManager.ClientMessage(Colors.DARK_RED + "ПОМОЩЬ ПО НАСТРОЙКЕ АДДОНА:");
            ChatManager.ClientMessage(Colors.GOLD + ".textlist" + Colors.RESET + Colors.RESET
                    + " - показывает настроенные тексты");
            ChatManager.ClientMessage(Colors.GOLD + ".textadd" + Colors.GREEN + " text" + Colors.RESET
                    + " - добавляет новый текст");
            ChatManager.ClientMessage(Colors.GOLD + ".textremove" + Colors.GREEN + " number" + Colors.RESET
                    + " - удаляет текст по его номеру");
            ChatManager.ClientMessage(Colors.GOLD + ".textedit" + Colors.GREEN + " number newtext" + Colors.RESET
                    + " - изменяет текст на новый");
            ChatManager.ClientMessage(Colors.GOLD + ".setvk" + Colors.GREEN + " your vk" + Colors.RESET
                    + " - установливает ссылку на вк (для банов), устанавливайте в формате 'vk.com/id'");
            ChatManager.ClientMessage(Colors.GOLD + ".getvk" + Colors.RESET
                    + " - показывает установленный вк");
            ChatManager.ClientMessage(Colors.GOLD + ".dupeip" + Colors.RESET
                    + " - включает/выключает автоматический /dupeip при проверке");
            ChatManager.ClientMessage(Colors.GOLD + ".setcords" + Colors.GREEN + " x y" + Colors.RESET
                    + " - устанавливает позицию для таймера (считая от левого верхнего угла)");
            ChatManager.ClientMessage(Colors.GOLD + ".savecfg" + Colors.RESET + Colors.RESET
                    + " - сохраняет конфиг");
            ChatManager.ClientMessage("");
            ChatManager.ClientMessage(Colors.DARK_RED + "ПОМОЩЬ ПО БЫСТРЫМ БАНАМ.МУТАМ:");
            ChatManager.ClientMessage(Colors.GOLD + ".imute" + Colors.GREEN + " nick reason" + Colors.RESET
                    + " - мут навсегда");
            ChatManager.ClientMessage(Colors.GOLD + ".iban" + Colors.GREEN + " nick reason" + Colors.RESET
                    + " - бан навсегда");
            ChatManager.ClientMessage(Colors.GOLD + ".ibanip" + Colors.GREEN + " nick reason" + Colors.RESET
                    + " - бан по айпи навсегда");
            ChatManager.ClientMessage(Colors.GOLD + ".tmute" + Colors.GREEN + " nick time reason" + Colors.RESET
                    + " - мут на время");
            ChatManager.ClientMessage(Colors.GOLD + ".tban" + Colors.GREEN + " nick time reason" + Colors.RESET
                    + " - бан на время");
            ChatManager.ClientMessage(Colors.GOLD + ".tbanip" + Colors.GREEN + " nick time reason" + Colors.RESET
                    + " - бан по айпи на время");
        }
    }
}
