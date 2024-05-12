package com.holymoderation.events;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.event.*;

import net.fabricmc.fabric.api.event.Event;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import org.apache.commons.lang3.time.StopWatch;

import java.awt.*;

public class Render {
    private static StopWatch stopWatch;

    private static String player = null;

    private static int xCords = 0;
    private static int yCords = 0;

    private static int customColor = 0x0;



    private static void onRender() {
        if (player != null) {

        }
    }

    private static int Rainbow(int delay) {
        double rainbowState = Math.ceil((System.currentTimeMillis() + delay) / 20.0D);
        rainbowState %= 360.0D;
        return Color.getHSBColor((float)(rainbowState / 360.0D), 0.5F, 1.0F).getRGB();
    }

    private static void StopWatchStart() {
        stopWatch = new StopWatch();
        stopWatch.start();
    }

    public static int GetXCords() {
        return xCords;
    }

    public static int GetYCords() {
        return yCords;
    }

    public static void SetXCords(int value) {
        xCords = value;
    }

    public static void SetYCords(int value) {
        yCords = value;
    }

    public static int GetCustomColor() {
        return customColor;
    }

    public static void SetCustomColor(int color) {
        customColor = color;
    }

    public static void SetPlayer(String value) {
        player = value;
    }

    private static void DrawString(Event<HudRenderCallback> event, String text, float x, float y, int color) {
        MinecraftClient.getInstance().inGameHud.getFontRenderer().draw()
    }
}
