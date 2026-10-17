package com.github.bunnyi116.litematica.printer.mixin.printer;

import com.github.bunnyi116.litematica.printer.Context;
import com.github.bunnyi116.litematica.printer.PrinterMod;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = MultiPlayerGameMode.class, priority = 990)
public class MultiPlayerGameModeMixin {
    @Inject(at = @At("TAIL"), method = "<init>")
    private static void init(CallbackInfo ci) {
        PrinterMod.CONTEXT.reset();
        PrinterMod.CONTEXT.setGameTicks(0L);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void tickHead(CallbackInfo ci) {
        Context context = PrinterMod.CONTEXT;
        context.setGameTicks(context.getGameTicks() + 1);
    }

    @Inject(at = @At("TAIL"), method = "tick")
    private void tickTail(CallbackInfo ci) {
        Context context = PrinterMod.CONTEXT;
        if (context.isEnable()) {
            return;
        }
        context.getBreakManager().tick();
        context.getTestManager().tick();
    }

    @Inject(at = @At("HEAD"), method = "startDestroyBlock", cancellable = true)
    private void startDestroyBlock(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        Context context = PrinterMod.CONTEXT;
        if (context.isEnable()) {
            return;
        }
        if (context.getBreakManager().isWorking()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(at = @At("HEAD"), method = "continueDestroyBlock", cancellable = true)
    private void continueDestroyBlock(BlockPos pos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        Context context = PrinterMod.CONTEXT;
        if (context.isEnable()) {
            return;
        }
        if (context.getBreakManager().isWorking()) {
            cir.setReturnValue(false);
        }
    }
}
