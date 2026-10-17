package com.github.bunnyi116.litematica.printer.mixin.malilib;

import com.github.bunnyi116.litematica.printer.mixin_extension.ConfigExtension;
import com.github.bunnyi116.litematica.printer.util.StringUtils;
import fi.dy.masa.malilib.config.IConfigBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// 对masa配置基类进行扩展, 进行I18n实现
@Mixin(value = IConfigBase.class, remap = false)
public interface IConfigBaseMixin {
    @Inject(method = "getConfigGuiDisplayName", at = @At("HEAD"), cancellable = true, remap = false)
    default void litematica_printer$getConfigGuiDisplayName(CallbackInfoReturnable<String> cir) {
        if (this instanceof ConfigExtension extension) {
            if (extension.printer$getTranslateNameKey() != null) {
                String translateKey = extension.printer$getTranslateNameKey();
                cir.setReturnValue(StringUtils.translatable(translateKey).getString());
            }
        }
    }
}

