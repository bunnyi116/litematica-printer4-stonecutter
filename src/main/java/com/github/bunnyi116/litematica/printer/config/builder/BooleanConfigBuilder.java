package com.github.bunnyi116.litematica.printer.config.builder;

import com.github.bunnyi116.litematica.printer.I18n;
import fi.dy.masa.malilib.config.options.ConfigBoolean;

public class BooleanConfigBuilder extends BaseConfigBuilder<ConfigBoolean, BooleanConfigBuilder> {
    private boolean defaultValue = false;

    public BooleanConfigBuilder(I18n i18n) {
        super(i18n);
    }

    public BooleanConfigBuilder(String translateKey) {
        this(I18n.of(translateKey));
    }

    public BooleanConfigBuilder defaultValue(boolean value) {
        this.defaultValue = value;
        return this;
    }

    @Override
    public ConfigBoolean build() {
        ConfigBoolean config = new ConfigBoolean(i18n.getNameKey(), defaultValue, descKey);
        return buildExtension(config);
    }
}