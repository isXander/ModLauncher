package dev.isxander.modlauncher.loader

import net.fabricmc.api.EnvType
import net.fabricmc.loader.impl.launch.knot.Knot
import net.fabricmc.loader.impl.util.SystemProperties

fun main(args: Array<String>) {
    System.setProperty(SystemProperties.SKIP_MC_PROVIDER, "true")
    System.setProperty(SystemProperties.MODS_FOLDER, "plugins")

    Knot.launch(args, EnvType.CLIENT)
}