package com.github.bunnyi116.litematica.printer.config;

import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;

/**
 * 无操作热键回调。
 *
 * <p>用于 {@link com.github.bunnyi116.litematica.printer.config.builder.HotkeyConfigBuilder}
 * 中「没有绑定任何配置项」的纯热键：此时按下热键不产生任何动作，
 * 但仍需要一个合法回调交给 malilib。</p>
 *
 * <p>方法签名与 {@code fi.dy.masa.malilib.hotkeys.IHotkeyCallback} 一致，
 * 因此可以直接用 {@code HotkeysCallback::onKeyAction} 作为方法引用。</p>
 */
public final class HotkeysCallback {

    private HotkeysCallback() {
    }

    public static boolean onKeyAction(KeyAction action, IKeybind key) {
        return true;
    }
}
