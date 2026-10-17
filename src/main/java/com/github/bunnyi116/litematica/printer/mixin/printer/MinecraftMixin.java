package com.github.bunnyi116.litematica.printer.mixin.printer;

import com.github.bunnyi116.litematica.printer.PrinterMod;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Minecraft.class, priority = 990)
public class MinecraftMixin {
    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo ci) {
        PrinterMod.CONTEXT.setClientTicks(PrinterMod.CONTEXT.getClientTicks() + 1);
    }
}
