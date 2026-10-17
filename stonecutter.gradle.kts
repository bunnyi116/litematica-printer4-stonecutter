@file:Suppress("InvalidScriptIdentifier")

plugins {
    id("dev.kikugie.stonecutter")
}

stonecutter active "26.3"

// See https://stonecutter.kikugie.dev/wiki/config/params
stonecutter parameters {
    swaps["mod_id"] = "\"${property("mod.id")}\";"
    swaps["mod_name"] = "\"${property("mod.name")}\";"
    swaps["mod_version"] = "\"${property("mod.version")}\";"
    swaps["game_version"] = "\"${node.metadata.version}\";"
    constants["release"] = property("mod.id") != "template"
    dependencies["fapi"] = node.project.property("deps.fabric_api") as String

    replacements {
        string(current.parsed >= "1.19.3", "built_in_registries") {
            replace("net.minecraft.core.Registry", "net.minecraft.core.registries.BuiltInRegistries")
            replace("Registry", "BuiltInRegistries")
        }
        string(current.parsed >= "1.20.5") {
            replace("BLOCK_EFFICIENCY", "EFFICIENCY")
        }
        string(current.parsed >= "1.21.5", "mining_fatigue") {
            replace("DIG_SLOWDOWN", "MINING_FATIGUE")
        }
        string(current.parsed >= "1.21.11", "!identifier") {
            replace("ResourceLocation", "Identifier")
        }
        string(current.parsed >= "26.1") {
            replace("classTweaker v2 named", "classTweaker v2 official")
        }
        string(current.parsed >= "26.1") {
            replace("net.minecraft.client.gui.GuiGraphics", "net.minecraft.client.gui.GuiGraphicsExtractor")
            replace("GuiGraphics", "GuiGraphicsExtractor")
        }
        string(current.parsed >= "26.2", "easy_place_utils") {
            replace("WorldUtils", "EasyPlaceUtils")
        }
    }
}
