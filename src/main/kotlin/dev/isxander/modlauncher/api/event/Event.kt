package dev.isxander.modlauncher.api.event

interface Event<T> {
    val invoker: T

    fun register(listener: T)
}