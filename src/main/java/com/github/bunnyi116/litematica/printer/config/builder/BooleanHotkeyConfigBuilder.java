package com.github.bunnyi116.litematica.printer.config.builder;

import com.github.bunnyi116.litematica.printer.I18n;
import com.github.bunnyi116.litematica.printer.util.MessageUtils;
import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.hotkeys.IHotkeyCallback;
import fi.dy.masa.malilib.hotkeys.KeybindSettings;
import fi.dy.masa.malilib.util.StringUtils;
import net.minecraft.network.chat.MutableComponent;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public class BooleanHotkeyConfigBuilder extends BaseConfigBuilder<ConfigBooleanHotkeyed, BooleanHotkeyConfigBuilder> {
    private boolean defaultValue = false;
    private String defaultHotkey = "";
    private KeybindSettings keybindSettings = KeybindSettings.DEFAULT;
    private @Nullable IHotkeyCallback keybindCallback;

    public BooleanHotkeyConfigBuilder(I18n i18n) {
        super(i18n);
    }

    public BooleanHotkeyConfigBuilder(String translateKey) {
        this(I18n.of(translateKey));
    }

    public BooleanHotkeyConfigBuilder defaultValue(boolean value) {
        this.defaultValue = value;
        return this;
    }

    public BooleanHotkeyConfigBuilder defaultHotkey(String hotkey) {
        this.defaultHotkey = hotkey;
        return this;
    }

    public BooleanHotkeyConfigBuilder keybindCallback(@Nullable IHotkeyCallback keybindCallback) {
        this.keybindCallback = keybindCallback;
        return this;
    }

    public BooleanHotkeyConfigBuilder keybindSettings(KeybindSettings settings) {
        this.keybindSettings = settings;
        return this;
    }

    @Override
    public ConfigBooleanHotkeyed build() {
        ConfigBooleanHotkeyed config = new ConfigBooleanHotkeyed(
                i18n.getNameKey(),
                defaultValue,
                defaultHotkey,
                keybindSettings,
                descKey,
                StringUtils.splitCamelCase(i18n.getNameKey())
        );
        if (keybindCallback == null) {
            keybindCallback = (action, key) -> onKeyAction(config);
        }
        config.getKeybind().setCallback(keybindCallback);
        return buildExtension(config);
    }

    private boolean onKeyAction(ConfigBooleanHotkeyed config) {
        config.toggleBooleanValue();
        boolean newValue = config.getBooleanValue();
        String pre = newValue ? GuiBase.TXT_GREEN : GuiBase.TXT_RED;
        I18n statusI18n = newValue ? I18n.MESSAGE_VALUE_ON : I18n.MESSAGE_VALUE_OFF;
        // 选项名用 getConfigGuiDisplayName()（会走本模组的 I18n 注入，跟随当前语言），
        // 而不是 getPrettyName()——后者是 nameKey 拆驼峰得到的英文原名，永远不会被翻译。
        MutableComponent message = I18n.MESSAGE_TOGGLED.getName(
                config.getConfigGuiDisplayName(),
                pre + statusI18n.getName().getString() + GuiBase.TXT_RST
        );
        MessageUtils.setOverlayMessage(message);
        return true;
    }
}