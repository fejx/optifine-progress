package fejx.util

class Event<T>(private val eventHandler: EventHandler<T>) {
	operator fun plusAssign(handler: (T) -> Unit) {
		eventHandler.listener.add(handler)
	}

	operator fun minusAssign(handler: (T) -> Unit) {
		eventHandler.listener.remove(handler)
	}
}

class EventHandler<T> {
	val listener = arrayListOf<((T) -> Unit)>()
	val hasListener: Boolean
		get() = listener.size > 0
	operator fun invoke(value: T) = listener.forEach { it(value) }
}
