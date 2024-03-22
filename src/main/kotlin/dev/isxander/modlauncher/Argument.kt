package dev.isxander.modlauncher

interface Argument {
    val resolved: String?

    fun resolve(context: LaunchContext): String?
}