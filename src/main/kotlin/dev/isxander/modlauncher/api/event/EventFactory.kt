@file:JvmName("EventFactory")
package dev.isxander.modlauncher.api.event

fun <T> createEvent(invokerFactory: (List<T>) -> T): Event<T> {
    return ListBackedEvent(invokerFactory)
}

private class ListBackedEvent<T>(val invokerFactory: (List<T>) -> T) : Event<T> {
    private var handlers = mutableListOf<T>()

    override var invoker: T = invokerFactory(handlers)

    override fun register(listener: T) {
        handlers += listener
        invoker = invokerFactory(handlers)
    }
}
