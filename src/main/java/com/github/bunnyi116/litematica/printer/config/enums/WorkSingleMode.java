package com.github.bunnyi116.litematica.printer.config.enums;

import com.github.bunnyi116.litematica.printer.I18n;
import com.github.bunnyi116.litematica.printer.config.ConfigOptionListEntry;

/**
 * 工作单例模式
 */
public enum WorkSingleMode implements ConfigOptionListEntry<WorkSingleMode> {
    PRINT("workSingleMode.print"),
    MINE("workSingleMode.mine"),
    FLUID("workSingleMode.fluid"),
    FILL("workSingleMode.fill"),
    BEDROCK("workSingleMode.bedrock");

    private final I18n i18n;

    WorkSingleMode(String translateKey) {
        this.i18n = I18n.of(translateKey);
    }

    @Override
    public I18n getI18n() {
        return i18n;
    }
}
