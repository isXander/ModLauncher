package dev.isxander.modlauncher

import dev.isxander.modlauncher.api.LauncherPlugin
import net.fabricmc.loader.api.FabricLoader
import java.nio.file.Path

class ModLauncher(val workDir: Path) {
    val minecraftData = workDir.resolve("minecraft")

    val plugins = FabricLoader.getInstance().getEntrypoints("modlauncher", LauncherPlugin::class.java)

    suspend fun launchMinecraft(version: MinecraftVersion) {

    }
}

fun interface StartListener {
    fun onStart()
}