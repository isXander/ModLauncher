package dev.isxander.modlauncher

data class DownloadableFile(
    val url: String,
    val sha1: String? = null,
    val size: Long? = null,
)