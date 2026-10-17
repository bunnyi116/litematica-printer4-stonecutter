package com.github.bunnyi116.litematica.printer.util;

//~ mining_fatigue

import com.github.bunnyi116.litematica.printer.PrinterMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("EnhancedSwitchMigration")
public class PlayerUtils {
    public static double blockInteractionRange() {
        //? if >=1.20.5 {
        return PrinterMod.CONTEXT.getPlayerOrThrow().blockInteractionRange();
        //?} else
        //return PrinterMod.CONTEXT.getGameModeOrThrow().getPickRange();
    }

    public static boolean isWithinBlockInteractionRange(BlockPos blockPos, double additionalRange) {
        LocalPlayer player = PrinterMod.CONTEXT.getPlayerOrThrow();
        double eyePosX = player.getX();
        //? if >1.18.2 {
        double eyePosY = player.getEyeY();
        //?} else {
        /*double eyePosY = player.getY() + 1.5d;
        *///?}
        double eyePosZ = player.getZ();

        double blockPosX = blockPos.getX();
        double blockPosY = blockPos.getY();
        double blockPosZ = blockPos.getZ();
        double range = blockInteractionRange() + additionalRange;

        //? if >1.20.4 {
        double dx = Math.max(Math.max(blockPosX - eyePosX, eyePosX - (blockPosX + 1)), 0);
        double dy = Math.max(Math.max(blockPosY - eyePosY, eyePosY - (blockPosY + 1)), 0);
        double dz = Math.max(Math.max(blockPosZ - eyePosZ, eyePosZ - (blockPosZ + 1)), 0);
        return dx * dx + dy * dy + dz * dz < range * range;
        //?} else {
        /*double dx = eyePosX - blockPosX + 0.5;
        double dy = eyePosY - blockPosY + 0.5;
        double dz = eyePosZ - blockPosZ + 0.5;
        return dx * dx + dy * dy + dz * dz <= range * range;
        *///?}
    }


    public static float getDestroyProgress(LocalPlayer player, BlockState state, ItemStack itemStack) {
        float hardness = state.getBlock().defaultDestroyTime();
        if (hardness == -1.0F) {
            return 0.0F;
        } else {
            int i = player.hasCorrectToolForDrops(state) ? 30 : 100;
            return getBlockBreakingSpeed(player, state, itemStack) / hardness / (float) i;
        }
    }

    public static float getDestroyProgress(LocalPlayer player, BlockState state, boolean mainHand) {
        return getDestroyProgress(player, state, mainHand ? player.getMainHandItem() : player.getOffhandItem());
    }

    public static float getDestroyProgress(LocalPlayer player, BlockState state) {
        return getDestroyProgress(player, state, true);
    }

    public static float getBlockBreakingSpeed(LocalPlayer player, BlockState blockState, ItemStack itemStack) {
        float f = itemStack.getDestroySpeed(blockState);
        //? if >1.20.6 {
        if (f > 1.0F) {
            for (Holder<Enchantment> enchantment : itemStack.getEnchantments().keySet()) {
                Optional<ResourceKey<Enchantment>> enchantmentKey = enchantment.unwrapKey();
                if (enchantmentKey.isPresent()) {
                    if (enchantmentKey.get() == Enchantments.EFFICIENCY) {
                        int level = EnchantmentHelper.getItemEnchantmentLevel(enchantment, itemStack);
                        if (level > 0 && !itemStack.isEmpty()) {
                            f += (float) (level * level + 1);
                        }
                    }
                }
            }
        }
        //?} else {
        /*if (f > 1.0F) {
            int level = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.EFFICIENCY, itemStack);
            if (level > 0 && !itemStack.isEmpty()) {
                f += (float)(level * level + 1);
            }
        }
        *///?}
        if (MobEffectUtil.hasDigSpeed(player)) {
            f *= 1.0F + (float) (MobEffectUtil.getDigSpeedAmplification(player) + 1) * 0.2F;
        }
        if (player.hasEffect(MobEffects.MINING_FATIGUE)) {
            float g;
            switch (Objects.requireNonNull(player.getEffect(MobEffects.MINING_FATIGUE)).getAmplifier()) {
                case 0:
                    g = 0.3F;
                    break;
                case 1:
                    g = 0.09F;
                    break;
                case 2:
                    g = 0.0027F;
                    break;
                default:
                    g = 8.1E-4F;
                    break;
            }
            f *= g;
        }
        //? if >1.20.6 {
        f *= (float) player.getAttributeValue(Attributes.BLOCK_BREAK_SPEED);
        if (player.isEyeInFluid(FluidTags.WATER)) {
            AttributeInstance submergedMiningSpeed = player.getAttribute(Attributes.SUBMERGED_MINING_SPEED);
            if (submergedMiningSpeed != null) {
                f *= (float) submergedMiningSpeed.getValue();
            }
        }
        //?} else {
        /*if (player.isEyeInFluid(FluidTags.WATER) && !EnchantmentHelper.hasAquaAffinity(player)) {
            f /= 5.0F;
        }
        *///?}
        if (!onGround()) {
            f /= 5.0F;
        }
        return f;
    }

    public static boolean onGround() {
        return onGround(PrinterMod.CONTEXT.getPlayerOrThrow());
    }

    public static boolean onGround(Entity entity) {
        //? if >=1.20 {
        return entity.onGround();
        //?} else {
        /*return entity.isOnGround();
        *///?}
    }
}
