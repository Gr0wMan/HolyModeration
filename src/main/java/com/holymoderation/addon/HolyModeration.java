package com.holymoderation.addon;

import com.holymoderation.addon.events.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import com.mojang.datafixers.FunctionType;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import net.labymod.api.LabyModAddon;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;
import net.labymod.core.LabyModCore;
import net.labymod.settings.elements.*;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;

public class HolyModeration extends LabyModAddon {


  @Override
  public void onEnable() {
    getApi().getEventService().registerListener(this);
    getApi().getEventService().registerListener(new FreezerEvent());
    getApi().getEventService().registerListener(new RenderEvent());
    getApi().getEventService().registerListener(new HMSettings());
    getApi().getEventService().registerListener(new HMHelp());
    getApi().getEventService().registerListener(new PunishmentsSimplifier());
  }

  @Override
  public void loadConfig() {
    RenderEvent.setxCoords(getConfig().has("X") ? getConfig().get("X").getAsInt() : 0);
    RenderEvent.setyCoords(getConfig().has("Y") ? getConfig().get("Y").getAsInt() : 0);
    PunishmentsSimplifier.SetVkUrl(getConfig().has("vk_url") ? getConfig().get("vk_url").getAsString() : null);
    FreezerEvent.SetDupeIp(getConfig().has("enable_dupe_ip") ? getConfig().get("enable_dupe_ip").getAsBoolean() : false);
    FreezerEvent.SetTexts(getConfig().has("texts_list") ? getConfig().get("texts_list").getAsString() : null);
  }

  @Override
  protected void fillSettings(List<SettingsElement> list) {
    /*list.add(new BooleanElement("/dupeip", new ControlElement.IconData(Material.LEVER), new Consumer<Boolean>() {
      public void accept(Boolean accepted) {
        SetDupeIp(accepted.booleanValue());
        LabyCheck.this.getConfig().addProperty("enable_dupe_ip", accepted);
        LabyCheck.this.saveConfig();
      }
    }, IsDupeIpEnabled()));

    list.add(new StringElement("VK URL", new ControlElement.IconData(Material.ANVIL), GetVkUrl(), new Consumer<String>() {
      public void accept(String accepted) {
        SetVkUrl(accepted);
        LabyCheck.this.getConfig().addProperty("vk_url", accepted);
        LabyCheck.this.saveConfig();
      }
    }));*/
  }

  @Subscribe
  public void OnUpdate(MessageSendEvent event) {
    if (event.getMessage().matches("/hmsavecfg"))
    {
      event.setCancelled(true);
      HolyModeration.this.getConfig().addProperty("X", RenderEvent.getxCoords());
      HolyModeration.this.getConfig().addProperty("Y", RenderEvent.getyCoords());
      HolyModeration.this.getConfig().addProperty("vk_url", PunishmentsSimplifier.GetVkUrl());
      HolyModeration.this.getConfig().addProperty("enable_dupe_ip", FreezerEvent.GetDupeIp());
      HolyModeration.this.getConfig().addProperty("texts_list", FreezerEvent.GetTexts());
      HolyModeration.this.saveConfig();
      ClientMessage("Your cfg have been saved!");
    }
  }

  public static void SendMessage(String message) {
    LabyModCore.getMinecraft().getPlayer().sendChatMessage(message);
  }

  public static void ClientMessage(String message) {
    Minecraft.getInstance().player.sendMessage(new StringTextComponent(message), null);
  }
}