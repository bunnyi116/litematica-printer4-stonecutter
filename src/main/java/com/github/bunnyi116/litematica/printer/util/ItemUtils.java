package com.github.bunnyi116.litematica.printer.util;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;

public class ItemUtils {
    public static Component getNameFromItem(Item item) {
        //? if >=26.1 {
        return item.getName(item.getDefaultInstance());
        //?} else if >1.21.1 {
        /*return item.getName();*/
        //?} else {
        /*return item.getDescription();
        *///?}
    }
}
