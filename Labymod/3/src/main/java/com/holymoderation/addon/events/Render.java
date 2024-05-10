package com.holymoderation.addon.events;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import com.holymoderation.addon.utils.ChatManager;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.gui.RenderGameOverlayEvent;
import net.labymod.core.LabyModCore;

import org.apache.commons.lang3.time.StopWatch;

public class Render {
    private static StopWatch stopWatch;

    private static String player = null;

    private static int xCoords = 0;
    private static int yCoords = 0;

    private static int rainbowDelay = 300;

    public static int customColor = 0x0;

    @Subscribe
    public void onRender(RenderGameOverlayEvent event) {
        if (player != null) {
            DrawString(event, "Текущая проверка:", xCoords, yCoords, customColor == 0x0 ? Rainbow(rainbowDelay) : customColor);
            DrawString(event, player + " | " + stopWatch.getTime(TimeUnit.MINUTES) + ":"
                    + (stopWatch.getTime(TimeUnit.SECONDS) - stopWatch.getTime(TimeUnit.MINUTES)*60),
                    xCoords, (yCoords + 10), customColor == 0x0 ? Rainbow(rainbowDelay) : customColor);
        }
    }

    public static int Rainbow(int delay) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0D);
        rainbowState %= 360.0D;
        return Color.getHSBColor((float)(rainbowState / 360.0D), 0.5F, 1.0F).getRGB();
    }

    public static void StopWatchStart() {
        stopWatch = new StopWatch();
        stopWatch.start();
    }

    public static int GetxCoords() {
        return xCoords;
    }

    public static int GetyCoords() {
        return yCoords;
    }

    public static void SetxCoords(int value) {
        xCoords = value;
    }

    public static void SetyCoords(int value) {
        yCoords = value;
    }

    public static int GetCustomColor() {
        return customColor;
    }

    public static void SetPlayer(String value) {
        player = value;
    }

    public static void SetCustomColor(int color) {
        customColor = color;
    }

    private static void DrawString(RenderGameOverlayEvent event, String text, int x, int y, int color) {
        LabyModCore.getMinecraft().getFontRenderer().drawString(event.getMatrixStack(), text, x, y, color);
    }
}
