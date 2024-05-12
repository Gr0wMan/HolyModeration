package com.holymoderation;

import com.holymoderation.events.Render;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HolyModeration implements ModInitializer {
	public static final String MOD_ID = "holymoderation";

    public static final Logger LOGGER = LogManager.getLogger("holymoderation");

	@Override
	public void onInitialize() {
		HudRenderCallback.EVENT.register(context, );
		LOGGER.info("Hello Fabric world!");
	}
}