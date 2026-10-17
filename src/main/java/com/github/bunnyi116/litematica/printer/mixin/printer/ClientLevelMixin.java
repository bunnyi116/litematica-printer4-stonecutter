package com.github.bunnyi116.litematica.printer.mixin.printer;

import com.github.bunnyi116.litematica.printer.util.NetworkUtils;
import net.minecraft.client.multiplayer.ClientLevel;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
//? if >1.18.2
import net.minecraft.client.multiplayer.prediction.BlockStatePredictionHandler;

@Mixin(value = ClientLevel.class, priority = 990)
public class ClientLevelMixin implements NetworkUtils.SequenceExtension {
    //? if >1.18.2 {
    @Final
    @Shadow
    private BlockStatePredictionHandler blockStatePredictionHandler;

    @Override
    public int printer$getSequence() {
        try (BlockStatePredictionHandler pendingUpdateManager = this.blockStatePredictionHandler.startPredicting()) {
            return pendingUpdateManager.currentSequence();
        }
    }
    //?}
}
