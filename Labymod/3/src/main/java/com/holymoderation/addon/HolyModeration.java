package com.holymoderation.addon;

import java.util.List;

import com.holymoderation.addon.utils.PunishmentsManager;
import net.labymod.api.LabyModAddon;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;
import net.labymod.settings.elements.*;

import com.holymoderation.addon.events.*;
import com.holymoderation.addon.utils.ChatManager;
import com.holymoderation.addon.utils.Colors;

public class HolyModeration extends LabyModAddon {


  @Override
  public void onEnable() {
    getApi().getEventService().registerListener(this);
    getApi().getEventService().registerListener(new Freezer());
    getApi().getEventService().registerListener(new Timer());
    getApi().getEventService().registerListener(new Settings());
    getApi().getEventService().registerListener(new Help());
    getApi().getEventService().registerListener(new Punishments());
    getApi().getEventService().registerListener(new Journal());
  }

  @Override
  public void loadConfig() {
    Timer.SetCustomColor(getConfig().has("custom_color") ? getConfig().get("custom_color").getAsInt() : 0x0);
    Timer.SetxCoords(getConfig().has("X") ? getConfig().get("X").getAsInt() : 0);
    Timer.SetyCoords(getConfig().has("Y") ? getConfig().get("Y").getAsInt() : 0);
    PunishmentsManager.SetVkUrl(getConfig().has("vk_url") ? getConfig().get("vk_url").getAsString() : null);
    Freezer.SetDupeIp(getConfig().has("enable_dupe_ip") ? getConfig().get("enable_dupe_ip").getAsBoolean() : false);
    Freezer.SetTexts(getConfig().has("texts_list") ? getConfig().get("texts_list").getAsString() : null);
  }

  @Override
  protected void fillSettings(List<SettingsElement> list) {
  }

  @Subscribe
  public void SaveCfg(MessageSendEvent event) {
    if (event.getMessage().equals(".savecfg")) {
      event.setCancelled(true);
      HolyModeration.this.getConfig().addProperty("custom_color", Timer.GetCustomColor());
      HolyModeration.this.getConfig().addProperty("X", Timer.GetxCoords());
      HolyModeration.this.getConfig().addProperty("Y", Timer.GetyCoords());
      HolyModeration.this.getConfig().addProperty("vk_url", PunishmentsManager.GetVkUrl());
      HolyModeration.this.getConfig().addProperty("enable_dupe_ip", Freezer.GetDupeIp());
      HolyModeration.this.getConfig().addProperty("texts_list", Freezer.GetTexts());
      HolyModeration.this.saveConfig();
      ChatManager.ClientMessage(Colors.GREEN + "Ваш конфиг был успешно сохранён!");
    }
  }
}