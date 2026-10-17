package com.github.bunnyi116.litematica.printer.util.manager;

import lombok.Getter;
import net.minecraft.core.Vec3i;

@SuppressWarnings({"UnusedReturnValue", "unused"})
@Getter
public class WorkBox {
    private int minX;
    private int minY;
    private int minZ;
    private int maxX;
    private int maxY;
    private int maxZ;

    public WorkBox(Vec3i pos1, Vec3i pos2) {
        this.set(pos1, pos2);
    }

    public WorkBox(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        this.set(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public WorkBox set(Vec3i pos1, Vec3i pos2) {
        this.set(pos1.getX(), pos2.getY(), pos1.getZ(), pos2.getX(), pos2.getY(), pos2.getZ());
        return this;
    }

    public WorkBox set(int minX, int minY, int minZ, int maxX, int maxY, int maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
        return this;
    }

    public boolean contains(Vec3i pos) {
        return this.contains(pos.getX(), pos.getY(), pos.getZ());
    }

    public boolean contains(int x, int y, int z) {
        return x >= minX && x <= maxX && y >= minY && y <= maxY && z >= minZ && z <= maxZ;
    }
}
