package com.github.bunnyi116.litematica.printer.mixin_extension;

import com.github.bunnyi116.litematica.printer.PrinterMod;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.options.ConfigBase;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

public interface ConfigExtension {
    @Nullable String printer$getTranslateNameKey();

    void printer$setTranslateNameKey(@Nullable String translateKey);

    @Nullable String printer$getTranslateCommentKey();

    void printer$setTranslateCommentKey(@Nullable String translateKey);

    BooleanSupplier printer$TRUE = () -> true;
    BooleanSupplier printer$FALSE = () -> false;

    @Nullable BooleanSupplier printer$getVisible();

    void printer$setVisible(@Nullable BooleanSupplier visible);

    default void printer$setVisible(boolean visible) {
        this.printer$setVisible(visible ? printer$TRUE : printer$FALSE);
    }

    /**
     * 求值当前可见性。没有设置条件时恒为 true。
     * 条件抛异常时按 true 处理，避免一个写错的条件让整个配置界面无法使用。
     */
    default boolean printer$isVisible() {
        BooleanSupplier supplier = this.printer$getVisible();
        if (supplier == null) {
            return true;
        }
        try {
            return supplier.getAsBoolean();
        } catch (Exception e) {
            PrinterMod.LOGGER.error("Config visibility condition failed for {}", this, e);
            return true;
        }
    }

    static BooleanSupplier printer$getVisible(ConfigExtension configExtension) {
        return configExtension.printer$getVisible();
    }

    static <T extends IConfigBase> void printer$setVisible(ConfigBase<T> config, BooleanSupplier visible) {
        if (config instanceof ConfigExtension configExtension) {
            configExtension.printer$setVisible(visible);
        }
    }

    static <T extends IConfigBase> void printer$setVisible(ConfigBase<T> config, boolean visible) {
        if (config instanceof ConfigExtension configExtension) {
            configExtension.printer$setVisible(visible);
        }
    }

    CopyOnWriteArrayList<Consumer<IConfigBase>> printer$getValueChangeCallbacks();

    default void printer$addValueChangeListener(Consumer<IConfigBase> callback) {
        if (callback != null) {
            printer$getValueChangeCallbacks().add(callback);
        }
    }

    default void printer$removeValueChangeListener(Consumer<IConfigBase> callback) {
        if (callback != null) {
            printer$getValueChangeCallbacks().remove(callback);
        }
    }

    default void printer$clearValueChangeCallbacks() {
        printer$getValueChangeCallbacks().clear();
    }

    default void printer$triggerValueChangeCallbacks() {
        IConfigBase self = (IConfigBase) this;
        for (Consumer<IConfigBase> callback : printer$getValueChangeCallbacks()) {
            try {
                callback.accept(self);
            } catch (Exception e) {
                PrinterMod.LOGGER.error("Config value callback error", e);
            }
        }
    }
}
