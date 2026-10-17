package com.github.bunnyi116.litematica.printer.util;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class MessageUtils {
    public static void setOverlayMessage(Component message, boolean bl) {
        //? if >=26.2 {
        Minecraft.getInstance().gui.hud.setOverlayMessage(message, false);
        //?} else {
        /*Minecraft.getInstance().gui.setOverlayMessage(message, false);
        *///?}
    }

    public static void setOverlayMessage(Component message) {
        setOverlayMessage(message, false);
    }

    public static void setOverlayMessage(String message) {
        setOverlayMessage(StringUtils.literal(message));
    }

    public static void addMessage(Component message) {
        //? if >=26.2 {
        Minecraft.getInstance().gui.hud.getChat().addClientSystemMessage(message);
        //?} else if >=26.1 {
        /*Minecraft.getInstance().gui.getChat().addClientSystemMessage(message);*/
        //?} else {
        /*Minecraft.getInstance().gui.getChat().addMessage(message);
        *///?}
    }

    public static void addMessage(String message) {
        addMessage(StringUtils.literal(message));
    }
}
