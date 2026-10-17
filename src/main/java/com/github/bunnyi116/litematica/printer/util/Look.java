package com.github.bunnyi116.litematica.printer.util;

import lombok.Data;
import net.minecraft.core.Direction;

@SuppressWarnings("ClassCanBeRecord")
@Data
public class Look {
    public final float yaw;
    public final float pitch;

    public Look(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Look(Direction lookDirection) {
        this(DirectionUtils.getRequiredYaw(lookDirection), DirectionUtils.getRequiredPitch(lookDirection));
    }

    public Look(Direction lookDirectionYaw, Direction lookDirectionPitch) {
        this(DirectionUtils.getRequiredYaw(lookDirectionYaw), DirectionUtils.getRequiredPitch(lookDirectionPitch));
    }

    public Look(int rotation) {
        this(DirectionUtils.rotationToPlayerYaw(rotation), 0);
    }
}
