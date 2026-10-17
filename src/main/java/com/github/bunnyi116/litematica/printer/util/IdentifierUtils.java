package com.github.bunnyi116.litematica.printer.util;

import net.minecraft.resources.Identifier;

//~ !identifier
public class IdentifierUtils {
    //~ identifier

    public static Identifier of(String string) {
        //? if >1.20.6 {
        return Identifier.parse(string);
        //?} else {
        /*return new Identifier(string);
        *///?}
    }

    public static Identifier of(String namespace, String path) {
        //? if >1.20.6 {
        return Identifier.fromNamespaceAndPath(namespace, path);
        //?} else {
        /*return new Identifier(namespace, path);
        *///?}
    }
}