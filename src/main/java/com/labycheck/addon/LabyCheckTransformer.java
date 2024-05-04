package com.labycheck.addon;

import net.labymod.addon.AddonTransformer;
import net.labymod.api.TransformerType;

public class LabyCheckTransformer extends AddonTransformer {

  @Override
  public void registerTransformers() {
    this.registerTransformer(TransformerType.VANILLA, "example.mixin.json");
  }
}
