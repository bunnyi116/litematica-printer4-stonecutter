package com.github.bunnyi116.litematica.printer.config;

import com.github.bunnyi116.litematica.printer.PrinterMod;
import com.github.bunnyi116.litematica.printer.config.enums.WorkMode;
import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.config.options.ConfigOptionList;
import fi.dy.masa.malilib.config.options.ConfigStringList;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;

import java.io.File;

import static com.github.bunnyi116.litematica.printer.config.ConfigBuilders.*;

public class Configs implements IConfigHandler {
    private static final File CONFIG_DIR = new File("./config");
    private static final String FILE_NAME = PrinterMod.MOD_ID + ".json";
    private static final String FILE_PATH = new File(CONFIG_DIR, FILE_NAME).getAbsolutePath();

    public static final ConfigBooleanHotkeyed WORK_TOGGLE = booleanHotkey("workToggle")
            .defaultValue(false)
            .defaultHotkey("CAPS_LOCK")
            .keybindSettings(KeybindSettings.PRESS_ALLOWEXTRA_EMPTY)
            .build();

    public static final ConfigOptionList WORK_MODE = optionList("workMode")
            .defaultValue(WorkMode.SINGLE)
            .build();

    public static final ConfigBooleanHotkeyed PRINT = booleanHotkey("print")
            .defaultValue(false)
            .visibleWhen(() -> WORK_MODE.getOptionListValue() == WorkMode.MULTI)
            .build();

    public static final ConfigBooleanHotkeyed MINE = booleanHotkey("mine")
            .defaultValue(false)
            .visibleWhen(() -> WORK_MODE.getOptionListValue() == WorkMode.MULTI)
            .build();

    public static final ConfigBooleanHotkeyed FILL = booleanHotkey("fill")
            .defaultValue(false)
            .visibleWhen(() -> WORK_MODE.getOptionListValue() == WorkMode.MULTI)
            .build();

    public static final ConfigBooleanHotkeyed FLUID = booleanHotkey("fluid")
            .defaultValue(false)
            .visibleWhen(() -> WORK_MODE.getOptionListValue() == WorkMode.MULTI)
            .build();

    public static final ConfigInteger WORK_RANGE = integer("workRange")
            .defaultValue(6)
            .range(1, 256)
            .build();

    public static final ConfigInteger TEST = integer("test")
            .defaultValue(6)
            .range(1, 256)
            .build();

    public static final ConfigBooleanHotkeyed TEST2 = booleanHotkey("test")
            .defaultValue(false)
            .visibleWhen(() -> TEST.getIntegerValue() > 10)
            .build();

    public static final ConfigStringList EXCAVATE_WHITELIST = stringList("excavateWhitelist")
            .build();

    public static final ConfigStringList EXCAVATE_BLACKLIST = stringList("excavateBlacklist")
            .build();

    public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
            WORK_TOGGLE,
            WORK_MODE,
            PRINT, MINE, FILL, FLUID,
            WORK_RANGE,
            TEST, TEST2,
            EXCAVATE_WHITELIST,
            EXCAVATE_BLACKLIST
    );

    @Override
    public void load() {

    }

    @Override
    public void save() {

    }
}
