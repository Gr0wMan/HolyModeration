package com.holymoderation.addon;

import java.util.List;

import com.holymoderation.addon.ChatUtils.PunishmentsManager;
import net.labymod.api.LabyModAddon;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;
import net.labymod.settings.elements.*;

import com.holymoderation.addon.events.*;
import com.holymoderation.addon.ChatUtils.ChatManager;
import com.holymoderation.addon.ChatUtils.Colors;

public class HolyModeration extends LabyModAddon {


  @Override
  public void onEnable() {
    getApi().getEventService().registerListener(this);
    getApi().getEventService().registerListener(new FreezerEvent());
    getApi().getEventService().registerListener(new RenderEvent());
    getApi().getEventService().registerListener(new HMSettings());
    getApi().getEventService().registerListener(new HMHelp());
    getApi().getEventService().registerListener(new Punishments());
  }

  @Override
  public void loadConfig() {
    RenderEvent.SetxCoords(getConfig().has("X") ? getConfig().get("X").getAsInt() : 0);
    RenderEvent.SetyCoords(getConfig().has("Y") ? getConfig().get("Y").getAsInt() : 0);
    PunishmentsManager.SetVkUrl(getConfig().has("vk_url") ? getConfig().get("vk_url").getAsString() : null);
    FreezerEvent.SetDupeIp(getConfig().has("enable_dupe_ip") ? getConfig().get("enable_dupe_ip").getAsBoolean() : false);
    FreezerEvent.SetTexts(getConfig().has("texts_list") ? getConfig().get("texts_list").getAsString() : null);
  }

  @Override
  protected void fillSettings(List<SettingsElement> list) {
  }

  @Subscribe
  public void SaveCfg(MessageSendEvent event) {
    if (event.getMessage().equals(".savecfg"))
    {
      event.setCancelled(true);
      HolyModeration.this.getConfig().addProperty("X", RenderEvent.GetxCoords());
      HolyModeration.this.getConfig().addProperty("Y", RenderEvent.GetyCoords());
      HolyModeration.this.getConfig().addProperty("vk_url", PunishmentsManager.GetVkUrl());
      HolyModeration.this.getConfig().addProperty("enable_dupe_ip", FreezerEvent.GetDupeIp());
      HolyModeration.this.getConfig().addProperty("texts_list", FreezerEvent.GetTexts());
      HolyModeration.this.saveConfig();
      ChatManager.ClientMessage(Colors.GREEN + "Ваш конфиг был успешно сохранён!");
    }
  }

}