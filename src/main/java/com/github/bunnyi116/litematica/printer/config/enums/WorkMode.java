package com.github.bunnyi116.litematica.printer.config.enums;

import com.github.bunnyi116.litematica.printer.I18n;
import com.github.bunnyi116.litematica.printer.config.ConfigOptionListEntry;

/**
 * 工作模式
 */
public enum WorkMode implements ConfigOptionListEntry<WorkMode> {
    MULTI("workMode.multi"),
    SINGLE("workMode.single");

    private final I18n i18n;

    WorkMode(String translateKey) {
        this.i18n = I18n.of(translateKey);
    }

    @Override
    public I18n getI18n() {
        return i18n;
    }
}
