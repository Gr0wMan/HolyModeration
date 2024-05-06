package com.holymoderation.addon.events;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.gui.RenderGameOverlayEvent;
import net.labymod.core.LabyModCore;

import org.apache.commons.lang3.time.StopWatch;

public class RenderEvent {
    private static String player = null;

    private static boolean onCheck = false;

    private static int xCoords = 10;
    private static int yCoords = 10;

    private static StopWatch stopWatch;

    @Subscribe
    public void onRender(RenderGameOverlayEvent event) {
        if (onCheck) {
            DrawString(event, "Current check:", xCoords, yCoords, rainbow(300));
            DrawString(event, player + " | " + stopWatch.getTime(TimeUnit.MINUTES) + ":"
                    + (stopWatch.getTime(TimeUnit.SECONDS) - stopWatch.getTime(TimeUnit.MINUTES)*60),
                    (xCoords + 10), (yCoords + 10), rainbow(300));
        }
    }

    public static int rainbow(int delay) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0D);
        rainbowState %= 360.0D;
        return Color.getHSBColor((float)(rainbowState / 360.0D), 0.5F, 1.0F).getRGB();
    }

    public static void StopWatchStart() {
        stopWatch = new StopWatch();
        stopWatch.start();
    }

    public static int getxCoords() {
        return xCoords;
    }

    public static int getyCoords() {
        return yCoords;
    }

    public static void setxCoords(int value) {
        xCoords = value;
    }

    public static void setyCoords(int value) {
        yCoords = value;
    }

    public static void setPlayer(String value) {
        player = value;
    }

    public static void setOnCheck(boolean value) {
        onCheck = value;
    }

    private static void DrawString(RenderGameOverlayEvent event, String text, int x, int y, int color) {
        LabyModCore.getMinecraft().getFontRenderer().drawString(event.getMatrixStack(), text, x, y, color);
    }
}
