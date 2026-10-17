package com.github.bunnyi116.litematica.printer.mixin.printer;

import com.github.bunnyi116.litematica.printer.PrinterMod;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

//@formatter:off

@SuppressWarnings("ALL")
@Mixin(value = ServerboundMovePlayerPacket.class, priority = 990)
public class ServerboundMovePlayerPacketMixin {
    //? if >1.21.1 {
    @ModifyVariable(method = "<init>(DDDFFZZZZ)V", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    //?} else
    //@ModifyVariable(method = "<init>(DDDFFZZZ)V", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private static float onModifyYaw(float yRot) {
        return PrinterMod.CONTEXT.getLookManager().getYaw(yRot);
    }

    //? if >1.21.1 {
    @ModifyVariable(method = "<init>(DDDFFZZZZ)V", at = @At("HEAD"), argsOnly = true, ordinal = 1)
    //?} else
    //@ModifyVariable(method = "<init>(DDDFFZZZ)V", at = @At("HEAD"), argsOnly = true, ordinal = 1)
    private static float onModifyPitch(float xRot) {
        return PrinterMod.CONTEXT.getLookManager().getPitch(xRot);
    }
}
