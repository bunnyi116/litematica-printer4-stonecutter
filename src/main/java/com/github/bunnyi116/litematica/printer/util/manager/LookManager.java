package com.github.bunnyi116.litematica.printer.util.manager;

import com.github.bunnyi116.litematica.printer.Context;
import com.github.bunnyi116.litematica.printer.util.DirectionUtils;
import lombok.Getter;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Direction;

@Getter
public class LookManager {
    private final Context context;
    private float yaw;
    private float pitch;
    private boolean modifyYaw;
    private boolean modifyPitch;

    public LookManager(Context context) {
        this.context = context;
    }

    public float getYaw(float yaw) {
        return this.modifyYaw ? this.yaw : yaw;
    }

    public float getPitch(float pitch) {
        return this.modifyPitch ? this.pitch : pitch;
    }

    public boolean isLookDirection(Direction direction) {
        LocalPlayer player = context.getPlayerOrThrow();
        float yaw = getYaw(player.getYRot());
        float pitch = getPitch(player.getXRot());
        return DirectionUtils.orderedByNearest(yaw, pitch)[0] == direction;
    }

    public LookManager setYaw(float yaw) {
        this.yaw = yaw;
        this.modifyYaw = true;
        return this;
    }

    public LookManager setPitch(float pitch) {
        this.pitch = pitch;
        this.modifyPitch = true;
        return this;
    }

    public LookManager setYawPitch(float yaw, float pitch) {
        return this.setYaw(yaw).setPitch(pitch);
    }

    public LookManager reset() {
        this.modifyYaw = false;
        this.modifyPitch = false;
        this.yaw = 0;
        this.pitch = 0;
        return this;
    }
}
