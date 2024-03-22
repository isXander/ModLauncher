package dev.isxander.modlauncher

data class MinecraftVersion(
    val clientJar: DownloadableFile,
    val id: String,
    val version: String,
    val libraries: List<Library>,
    val mainClass: String,
    val assetIndex: Int,
    val gameArguments: List<Argument>,
    val jvmArguments: List<Argument>,
)
