package com.holymoderation;

import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HolyModeration implements ModInitializer {
	public static final String MOD_ID = "holymoderation";

    public static final Logger LOGGER = LogManager.getLogger("holymoderation");

	@Override
	public void onInitialize() {

		LOGGER.info("Hello Fabric world!");
	}
}