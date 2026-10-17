package com.github.bunnyi116.litematica.printer.util.manager;

import com.github.bunnyi116.litematica.printer.Context;
import lombok.Getter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

@Getter
@SuppressWarnings({"DuplicatedCode", "UnusedReturnValue"})
public class PlacementManager {
    private final Context context;

    public PlacementManager(Context context) {
        this.context = context;
    }

    public void placement(BlockPos pos, Direction direction) {
    }
}