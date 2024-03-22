package dev.isxander.modlauncher.api

import dev.isxander.modlauncher.MinecraftVersion

interface LauncherPlugin {
    suspend fun provideVersions(): List<MinecraftVersion>
}