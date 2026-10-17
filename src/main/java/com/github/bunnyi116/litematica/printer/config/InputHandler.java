package com.github.bunnyi116.litematica.printer.config;

import com.github.bunnyi116.litematica.printer.PrinterMod;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * 把本模组的热键交给 malilib 的按键系统。
 *
 * <p><b>这一步不能省。</b> malilib 不会自动发现各个模组的热键——它只会在初始化时
 * 通过 {@link IKeybindProvider} 主动“拉取”。没有注册提供者时，
 * {@link Configs} 里那些 {@code ConfigBooleanHotkeyed} / {@code ConfigHotkey}
 * 就只是一个存着按键名的字符串：按下去什么也不会发生，
 * {@code workToggle} 的 CAPS_LOCK 之类的开关自然“没有效果”。</p>
 *
 * <p>涉及的三套接口（{@code InputEventHandler} / {@code IKeybindManager} /
 * {@code IKeybindProvider}）从 malilib 0.12.1（MC 1.18.2）到 0.30.2（MC 26.3）
 * 签名完全一致，因此这里不需要任何版本分支。</p>
 */
public class InputHandler implements IKeybindProvider {
    private static final InputHandler INSTANCE = new InputHandler();

    /** 热键在“按键设置”界面里的分类名（i18n 键）。 */
    public static final String HOTKEY_CATEGORY = "litematica-printer.hotkeys.category.generic";

    public static InputHandler getInstance() {
        return INSTANCE;
    }

    @Override
    public void addKeysToMap(IKeybindManager manager) {
        for (IHotkey hotkey : getAllHotkeys()) {
            manager.addKeybindToMap(hotkey.getKeybind());
        }
    }

    @Override
    public void addHotkeys(IKeybindManager manager) {
        manager.addHotkeysForCategory(PrinterMod.MOD_NAME, HOTKEY_CATEGORY, getAllHotkeys());
    }

    /**
     * 从 {@link Configs#OPTIONS} 中挑出所有带热键的配置项。
     * {@code ConfigBooleanHotkeyed} 通过 {@code IHotkeyTogglable} 间接实现 {@code IHotkey}，
     * {@code ConfigHotkey} 直接实现，两者都会被这里收进来。
     */
    public static List<IHotkey> getAllHotkeys() {
        List<IHotkey> hotkeys = new ArrayList<>();

        for (IConfigBase config : Configs.OPTIONS) {
            if (config instanceof IHotkey hotkey) {
                IKeybind keybind = hotkey.getKeybind();
                if (keybind != null) {
                    hotkeys.add(hotkey);
                }
            }
        }

        return hotkeys;
    }
}
