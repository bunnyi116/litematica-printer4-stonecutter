package com.github.bunnyi116.litematica.printer.util;

import com.github.bunnyi116.litematica.printer.PrinterMod;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;

public class NetworkUtils {
    public static void sendPacket(Packet<?> packet) {
        ClientPacketListener connection = PrinterMod.CONTEXT.getConnectionOrThrow();
        if (connection != null) {
            connection.send(packet);
        }
    }

    public static void sendPacket(PredictiveAction packetCreator) {
        if (PrinterMod.CONTEXT.getLevelOrThrow() instanceof SequenceExtension sequenceExtension) {
            int currentSequence = sequenceExtension.printer$getSequence();
            Packet<ServerGamePacketListener> packet = packetCreator.predict(currentSequence);
            NetworkUtils.sendPacket(packet);
        }
    }

    public static void sendLookPacket(LocalPlayer playerEntity, float lookYaw, float lookPitch) {
        playerEntity.connection.send(new ServerboundMovePlayerPacket.Rot(
                lookYaw,
                lookPitch,
                PlayerUtils.onGround(playerEntity)
                //? if >1.21.1
                , playerEntity.horizontalCollision
        ));
    }

    public static void sendLookPacket(LocalPlayer playerEntity, Look look) {
        sendLookPacket(playerEntity, look.getYaw(), look.getPitch());
    }

    public interface SequenceExtension {
        default int printer$getSequence() {
            return 0;
        }
    }
}
