package com.github.bunnyi116.litematica.printer.mixin.malilib;

import com.github.bunnyi116.litematica.printer.mixin_extension.ConfigExtension;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.options.ConfigBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

// 对masa配置基类进行扩展, 进行I18n实现
@Mixin(value = ConfigBase.class, remap = false)
public abstract class ConfigBaseMixin implements ConfigExtension {
    @Unique
    private String printer$translateNameKey;
    @Unique
    private String printer$translateCommentKey;
    @Unique
    private BooleanSupplier printer$visible;
    @Unique
    private final CopyOnWriteArrayList<Consumer<IConfigBase>> printer$callbacks = new CopyOnWriteArrayList<>();

    @Override
    public String printer$getTranslateNameKey() {
        return printer$translateNameKey;
    }

    @Override
    public void printer$setTranslateNameKey(String translateKey) {
        this.printer$translateNameKey = translateKey;
    }

    @Override
    public String printer$getTranslateCommentKey() {
        return printer$translateCommentKey;
    }

    @Override
    public void printer$setTranslateCommentKey(String translateKey) {
        this.printer$translateCommentKey = translateKey;
    }

    @Override
    public BooleanSupplier printer$getVisible() {
        return printer$visible;
    }

    @Override
    public void printer$setVisible(BooleanSupplier visible) {
        this.printer$visible = visible;
    }

    @Override
    public CopyOnWriteArrayList<Consumer<IConfigBase>> printer$getValueChangeCallbacks() {
        return printer$callbacks;
    }
}
