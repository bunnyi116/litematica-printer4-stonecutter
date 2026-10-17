package com.github.bunnyi116.litematica.printer.config;

import com.github.bunnyi116.litematica.printer.I18n;
import com.github.bunnyi116.litematica.printer.PrinterMod;
import fi.dy.masa.malilib.gui.GuiConfigsBase;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ConfigMasaUI extends GuiConfigsBase {
    public static final I18n FREE_NOTICE = I18n.of("freeNotice");

    public ConfigMasaUI(@Nullable Screen parent) {
        super(10, 50, PrinterMod.MOD_ID, parent, PrinterMod.MOD_NAME + " " + PrinterMod.MOD_VERSION + "   " + FREE_NOTICE.getName().getString());
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public List<ConfigOptionWrapper> getConfigs() {
        return ConfigOptionWrapper.createFor(Configs.OPTIONS);
    }
}
