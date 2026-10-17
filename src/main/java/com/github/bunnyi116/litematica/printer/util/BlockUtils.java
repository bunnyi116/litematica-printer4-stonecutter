package com.github.bunnyi116.litematica.printer.util;

//~ built_in_registries

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class BlockUtils {
    public static boolean isReplaceable(BlockState blockState) {
        //? if >1.19.2 {
        return blockState.canBeReplaced();
        //?} else {
        /*return blockState.getMaterial().isReplaceable();
        *///?}
    }

    public static @NotNull Block getBlock(Identifier blockId) {
        //? if >1.21.1 {
        return BuiltInRegistries.BLOCK.getValue(blockId);
        //?} else {
        /*return BuiltInRegistries.BLOCK.get(blockId);
        *///?}
    }

    public static String getBlockName(Block block) {
        return block.getName().getString();
    }

    public static Identifier getKey(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    public static String getKeyString(Block block) {
        return getKey(block).toString();
    }

    public static boolean canSupportCenter(LevelReader levelReader, BlockPos blockPos, Direction direction) {
        return Block.canSupportCenter(levelReader, blockPos, direction);
    }
}