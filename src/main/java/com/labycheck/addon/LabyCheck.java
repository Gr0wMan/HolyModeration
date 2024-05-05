package com.labycheck.addon;

import com.labycheck.addon.events.FreezerEvent;
import com.labycheck.addon.events.RenderEvent;

import java.util.List;

import net.labymod.api.LabyModAddon;
import net.labymod.settings.elements.*;
import net.labymod.settings.elements.ControlElement.IconData;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;

public class LabyCheck extends LabyModAddon {

  private static Boolean dupeIpEnabled = false;
  private static String vkUrl;

  @Override
  public void onEnable() {
    getApi().getEventService().registerListener(new FreezerEvent());
    getApi().getEventService().registerListener(new RenderEvent());
  }

  @Override
  public void loadConfig() {

  }

  @Override
  protected void fillSettings(List<SettingsElement> list) {
    list.add(new BooleanElement("/dupeip", new ControlElement.IconData(Material.LEVER),
            (Consumer<Boolean>) accepted -> SetDupeIp(accepted), IsDupeIpEnabled()));

    list.add(new StringElement("VK URL", new ControlElement.IconData(Material.PAPER), getVkUrl(),
            (Consumer<String>) accepted -> SetVkUrl(accepted)));
  }

  private static void SetVkUrl(String value) {
    vkUrl = value;
  }

  private static void SetDupeIp(boolean value) {
    dupeIpEnabled = value;
  }

  public static boolean IsDupeIpEnabled() {
    return dupeIpEnabled;
  }

  public static String getVkUrl() {
    return vkUrl;
  }
}
