package com.holymoderation.addon;

import com.holymoderation.addon.events.FreezerEvent;
import com.holymoderation.addon.events.LBCHelp;
import com.holymoderation.addon.events.RenderEvent;
import com.holymoderation.addon.events.LBCSettings;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import net.labymod.api.LabyModAddon;
import net.labymod.core.LabyModCore;
import net.labymod.settings.elements.*;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.StringTextComponent;

public class HolyModeration extends LabyModAddon {

  private static Boolean dupeIpEnabled = false;
  private static String vkUrl;
  private static String texts = "";

  @Override
  public void onEnable() {
    getApi().getEventService().registerListener(new FreezerEvent());
    getApi().getEventService().registerListener(new RenderEvent());
    getApi().getEventService().registerListener(new LBCSettings());
    getApi().getEventService().registerListener(new LBCHelp());
  }

  @Override
  public void loadConfig() {
    vkUrl = getConfig().has("vk_url") ? getConfig().get("vk_url").getAsString() : "";
    dupeIpEnabled = getConfig().has("enable_dupe_ip") ? getConfig().get("enable_dupe_ip").getAsBoolean() : false;
    texts = getConfig().has("texts_list") ? getConfig().get("texts_list").getAsString() : "";
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

  public void SaveCfg() {
    HolyModeration.this.getConfig().addProperty("enable_dupe_ip", dupeIpEnabled);
    HolyModeration.this.getConfig().addProperty("vk_url", vkUrl);
    HolyModeration.this.getConfig().addProperty("texts_list", texts);
    HolyModeration.this.saveConfig();
  }

  public static void SendMessage(String message) {
    LabyModCore.getMinecraft().getPlayer().sendChatMessage(message);
  }

  public static void ClientMessage(String message) {
    Minecraft.getInstance().player.sendMessage(new StringTextComponent(message), null);
  }

  public static boolean IsDupeIpEnabled() {
    return dupeIpEnabled;
  }

  public static String GetVkUrl() {
    return vkUrl;
  }

  public static String[] GetTexts() {
    if (texts == "")
      return null;
    else
      return texts.split("%", 0);
  }

  public static void AddText(String value) {
    if (value == null)
      return;

    if (texts == "")
      texts = value;
    else
      texts = texts + "%" + value;
  }

  public static void RemoveText(String value){
    ArrayList<String> textsarlist = new ArrayList<>(Arrays.asList(texts.split("%", 0)));
    textsarlist.remove(Integer.parseInt(value) - 1);
    texts = "";
    for (int i = 0; i < textsarlist.size(); i++) {
      if (texts == "")
        texts = textsarlist.get(i);
      else
        texts = texts + "%" + textsarlist.get(i);
    }
  }

  public static void SetVkUrl(String value) {
    vkUrl = value;
  }

  public static void SetDupeIp(boolean value) {
    dupeIpEnabled = value;
  }
}