package com.github.bunnyi116.litematica.printer;

import com.github.bunnyi116.litematica.printer.config.InputHandler;
import fi.dy.masa.malilib.event.InputEventHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("ALL")
public class PrinterMod implements ModInitializer, ClientModInitializer, DedicatedServerModInitializer {
    public static final String MOD_ID = /*$ mod_id*/ "litematica-printer";
    public static final String MOD_NAME = /*$ mod_name*/ "Litematica-Printer";
    public static final String MOD_VERSION = /*$ mod_version*/ "1.3.1";
    public static final String GAME_VERSION = /*$ game_version*/ "26.3";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Context CONTEXT = Context.getInstance();

    public static final Logger getLogger(String name) {
        return LoggerFactory.getLogger(MOD_ID + "/" + name);
    }

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initialized! mod_version: {}, game_version: {}", MOD_VERSION, GAME_VERSION);
        
        InputEventHandler.getKeybindManager().registerKeybindProvider(InputHandler.getInstance());
    }

    @Override
    public void onInitializeServer() {
    }

    @Override
    public void onInitialize() {
    }
}
