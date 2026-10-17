package com.github.bunnyi116.litematica.printer.util;

//~ easy_place_utils

import fi.dy.masa.litematica.data.DataManager;
import fi.dy.masa.litematica.schematic.placement.SchematicPlacementManager;
import fi.dy.masa.litematica.selection.AreaSelection;
import fi.dy.masa.litematica.selection.Box;
import fi.dy.masa.litematica.selection.SelectionMode;
import fi.dy.masa.litematica.util.EasyPlaceProtocol;
import fi.dy.masa.litematica.util.EasyPlaceUtils;
import fi.dy.masa.litematica.util.PlacementHandler;
import fi.dy.masa.litematica.world.SchematicWorldHandler;
import fi.dy.masa.litematica.world.WorldSchematic;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LitematicaUtils {
    @Nullable
    public static WorldSchematic schematicLevel() {
        return SchematicWorldHandler.getSchematicWorld();
    }

    @Nullable
    public static Vec3 useEasyPlaceProtocol(BlockPos pos, BlockState stateSchematic) {
        EasyPlaceProtocol protocol = PlacementHandler.getEffectiveProtocolVersion();
        Vec3 hitPos = Vec3.atLowerCornerOf(pos);
        if (protocol == EasyPlaceProtocol.V3) {
            return EasyPlaceUtils.applyPlacementProtocolV3(pos, stateSchematic, hitPos);
        } else if (protocol == EasyPlaceProtocol.V2) {
            // Carpet Accurate Block placements protocol support, plus slab support
            return EasyPlaceUtils.applyCarpetProtocolHitVec(pos, stateSchematic, hitPos);
        }
        return null;
    }

    public static boolean isPositionWithinRange(BlockPos pos) {
        return DataManager.getRenderLayerRange().isPositionWithinRange(pos);
    }

    public static boolean isSchematicBlock(BlockPos pos) {
        SchematicPlacementManager schematicPlacementManager = DataManager.getSchematicPlacementManager();
        //? if >=1.19.2 {
        List<SchematicPlacementManager.PlacementPart> allPlacementsTouchingChunk = schematicPlacementManager.getAllPlacementsTouchingChunk(pos);
        //?} else {
        /*List<SchematicPlacementManager.PlacementPart> allPlacementsTouchingChunk = schematicPlacementManager.getAllPlacementsTouchingSubChunk(new fi.dy.masa.malilib.util.SubChunkPos(pos));
        *///?}
        for (SchematicPlacementManager.PlacementPart placementPart : allPlacementsTouchingChunk) {
            var box = placementPart.getBox();
            //? if >=1.21.11 {
            int minX = box.minX();
            int minY = box.minY();
            int minZ = box.minZ();
            int maxX = box.maxX();
            int maxY = box.maxY();
            int maxZ = box.maxZ();
            //?} else {
            /*int minX = box.minX;
            int minY = box.minY;
            int minZ = box.minZ;
            int maxX = box.maxX;
            int maxY = box.maxY;
            int maxZ = box.maxZ;
            *///?}

            // box.contains(pos)
            if (pos.getX() >= minX && pos.getX() <= maxX
                    && pos.getZ() >= minZ && pos.getZ() <= maxZ
                    && pos.getY() >= minY && pos.getY() <= maxY
            ) {
                return true;
            }
        }
        return false;
    }


    public static boolean isWithinSelectionMode1Range(BlockPos pos) {
        AreaSelection selection = DataManager.getSelectionManager().getCurrentSelection();
        if (selection == null) {
            return false;
        }
        if (DataManager.getSelectionManager().getSelectionMode() == SelectionMode.NORMAL) {
            List<Box> arr = selection.getAllSubRegionBoxes();
            for (Box box : arr) {
                if (contains(box, pos)) {
                    return true;
                }
            }
            return false;
        } else {
            Box box = selection.getSubRegionBox(DataManager.getSimpleArea().getName());
            return contains(box, pos);
        }
    }


    public static boolean contains(Box box, BlockPos pos) {
        if (box == null) return false;
        BlockPos pos1 = box.getPos1();
        if (pos1 == null) return false;
        BlockPos pos2 = box.getPos2();
        if (pos2 == null) return false;
        int minX = Math.min(pos1.getX(), pos2.getX());
        int minY = Math.min(pos1.getY(), pos2.getY());
        int minZ = Math.min(pos1.getZ(), pos2.getZ());
        int maxX = Math.max(pos1.getX(), pos2.getX());
        int maxY = Math.max(pos1.getY(), pos2.getY());
        int maxZ = Math.max(pos1.getZ(), pos2.getZ());
        return pos.getX() >= minX && pos.getX() <= maxX
                && pos.getZ() >= minZ && pos.getZ() <= maxZ
                && pos.getY() >= minY && pos.getY() <= maxY;
    }
}
