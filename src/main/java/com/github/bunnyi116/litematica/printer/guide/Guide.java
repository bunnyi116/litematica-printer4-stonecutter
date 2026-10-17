package com.github.bunnyi116.litematica.printer.guide;

import com.github.bunnyi116.litematica.printer.util.BlockStateUtils;
import fi.dy.masa.litematica.world.WorldSchematic;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public abstract class Guide extends BlockStateUtils {
    protected final SchematicBlockContext context;
    public final Minecraft client;
    public final ClientLevel level;
    public final WorldSchematic schematic;
    public final BlockPos blockPos;
    public final BlockState currentState;
    public final BlockState requiredState;
    protected final Block currentBlock;
    protected final Block requiredBlock;

    public Guide(SchematicBlockContext context) {
        this.context = context;
        this.client = context.client;
        this.level = context.level;
        this.schematic = context.schematic;
        this.blockPos = context.blockPos;
        this.currentBlock = context.currentState.getBlock();
        this.requiredBlock = context.requiredState.getBlock();
        this.currentState = context.currentState;
        this.requiredState = context.requiredState;
    }

    protected boolean canExecute() {
        return true;
    }

    public final GuideResult buildAction(BlockMatchResult state) {
        GuideResult result = onBuildAction(state);
        if (result != null) {
            if (!result.isEmpty() || result.isSkip()) {
                return result;
            }
        }
        switch (state) {
            case MISSING -> {
                return onBuildActionMissingBlock(state);
            }
            case ERROR_BLOCK -> {
                return onBuildActionErrorBlock(state);
            }
            case ERROR_STATE -> {
                return onBuildActionErrorState(state);
            }
            case CORRECT -> {
                return onBuildActionCorrect(state);
            }
        }
        return GuideResult.EMPTY;
    }

    protected GuideResult onBuildAction(BlockMatchResult state) {
        return GuideResult.EMPTY;
    }

    protected GuideResult onBuildActionMissingBlock(BlockMatchResult state) {
        return GuideResult.EMPTY;
    }

    protected GuideResult onBuildActionErrorBlock(BlockMatchResult state) {
        return GuideResult.EMPTY;
    }

    protected GuideResult onBuildActionErrorState(BlockMatchResult state) {
        return GuideResult.EMPTY;
    }

    protected GuideResult onBuildActionCorrect(BlockMatchResult state) {
        return GuideResult.EMPTY;
    }

}