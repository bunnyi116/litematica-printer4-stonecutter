package com.github.bunnyi116.litematica.printer.util;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;

//? if >=1.20
import net.minecraft.client.gui.GuiGraphicsExtractor;

//? if <=1.19.4 {
/*import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiComponent;
*///?}

//? if >=1.21.2 <1.21.6
/*import net.minecraft.client.renderer.RenderType;*/

//? if >=1.21.6
import net.minecraft.client.renderer.RenderPipelines;

import java.awt.*;

/**
 * 封装了原版 GUI 渲染的底层工具类，兼容多版本：
 * 1.18.x / 1.19.x / 1.20.x / 1.21.1 / 1.21.3+
 */
public class Render2DUtils {
    public static final Minecraft client = Minecraft.getInstance();
    private static PoseStack poseStack;

    //? if >=1.20
    private static GuiGraphicsExtractor guiGraphics;

    // ==================== 初始化 ====================

    public static void initMatrix(PoseStack poseStack) {
        Render2DUtils.poseStack = poseStack;
    }

    //? if >=1.20 {
    public static void initGuiGraphicsExtractor(GuiGraphicsExtractor guiGraphics) {
        Render2DUtils.guiGraphics = guiGraphics;
    }
    //?}

    public static void ensureInitialized() {
        //? if >=1.20 {
        if (guiGraphics == null) {
            throw new NullPointerException("GuiGraphicsExtractor is null! Call initGuiGraphicsExtractor first.");
        }
        //?} else {
        /*if (poseStack == null) {
            throw new NullPointerException("PoseStack is null!Call initMatrix first.");
        }
        *///?}
    }

    // ==================== 文字与矩形 ====================

    public static void text(Font font, String text, int x, int y, int color, boolean withShadow) {
        //? if >=26.1 {
        guiGraphics.text(font, text, x, y, color, withShadow);
        //?} else if >=1.20 {
        /*guiGraphics.drawString(font, text, x, y, color, withShadow);*/
        //?} else {
        /*if (withShadow) {
           font.drawShadow(poseStack, text, x, y, color);
        } else {
           font.draw(poseStack, text, x, y, color);
        }
        *///?}
    }

    public static void text(String text, int x, int y, Color color, boolean withShadow) {
        text(text, x, y, color, withShadow, false);
    }

    public static void text(String text, int x, int y, Color color, boolean withShadow, boolean centered) {
        if (centered) {
            x -= client.font.width(text) / 2;
        }
        ensureInitialized();
        text(client.font, text, x, y, color.getRGB(), withShadow);
    }

    public static void fill(int x1, int y1, int x2, int y2, Color color) {
        ensureInitialized();
        //? if >=1.20 {
        guiGraphics.fill(x1, y1, x2, y2, color.getRGB());
        //?} else {
        /*GuiComponent.fill(poseStack, x1, y1, x2, y2, color.getRGB());
        *///?}
    }

    // ==================== 纹理绘制 ====================

    /**
     * 绘制完整纹理（纹理文件整体宽高与绘制区域一致）
     */
    public static void drawTexture(Identifier texture, int x, int y, int width, int height) {
        drawTexture(texture, x, y, 0, 0, width, height, width, height);
    }

    /**
     * 绘制纹理的指定区域（支持 UV 裁剪）
     */
    public static void drawTexture(Identifier texture, int x, int y,
                                   int u, int v, int regionWidth, int regionHeight,
                                   int textureWidth, int textureHeight) {
        ensureInitialized();

        //? if >=1.21.6 {
        guiGraphics.blit(
                RenderPipelines.GUI_TEXTURED,
                texture,
                x, y,
                (float) u, (float) v,
                regionWidth, regionHeight,
                regionWidth, regionHeight,
                textureWidth, textureHeight
        );
        //?} else if >=1.21.2 {
        /*guiGraphics.blit(
                 RenderType:: guiTextured,
                 texture,
                 x, y,
                 (float) u, (float) v,
                 regionWidth, regionHeight,
                 regionWidth, regionHeight,
                 textureWidth, textureHeight
        );
        *///?} else if >=1.20 {
        /*guiGraphics.blit(texture, x, y, (float) u, (float) v, regionWidth, regionHeight, textureWidth, textureHeight);*/
        //?} else {
        /*RenderSystem.setShaderTexture(0, texture);
        GuiComponent.blit(poseStack, x, y, u, v, regionWidth, regionHeight, textureWidth, textureHeight);
        *///?}
    }

    // ==================== 物品绘制 ====================

    /**
     * 绘制物品图标（不依赖玩家实体，纯图标渲染）
     */
    public static void drawItem(ItemStack stack, int x, int y) {
        ensureInitialized();
        //? if >=26.1 {
        guiGraphics.fakeItem(stack, x, y);
        //?} else if >=1.20 {
        /*guiGraphics.renderFakeItem(stack, x, y);*/
        //?} else if >=1.19.4 {
        /*client.getItemRenderer().renderGuiItem(poseStack, stack, x, y);*/
        //?} else {
        /*client.getItemRenderer().renderGuiItem(stack, x, y);
        *///?}
    }

    /**
     * 绘制物品图标及其装饰（数量、耐久条等）
     */
    public static void drawItemWithDecorations(ItemStack stack, int x, int y) {
        drawItem(stack, x, y);
        //? if >=26.1 {
        guiGraphics.itemDecorations(client.font, stack, x, y);
        //?} else if >=1.20 {
        /*guiGraphics.renderItemDecorations(client.font, stack, x, y);*/
        //?} else if >=1.19.4 {
        /*client.getItemRenderer().renderGuiItemDecorations(poseStack, client.font, stack, x, y);*/
        //?} else {
        /*client.getItemRenderer().renderGuiItemDecorations(client.font, stack, x, y);
        *///?}
    }

    // ==================== 组合绘制 ====================

    /**
     * 物品图标 + 右侧文字
     */
    public static void drawItemWithText(ItemStack stack, int x, int y, String text, Color color, boolean shadow) {
        drawItem(stack, x, y);
        text(text, x + 20, y + 5, color, shadow);
    }

    /**
     * 纹理图标 + 右侧文字（可指定图标尺寸）
     */
    public static void drawIconWithText(Identifier texture, int x, int y, int iconWidth, int iconHeight, String text, Color color, boolean shadow) {
        drawTexture(texture, x, y, iconWidth, iconHeight);
        int textY = y + (iconHeight - client.font.lineHeight) / 2;
        text(text, x + iconWidth + 4, textY, color, shadow);
    }
}