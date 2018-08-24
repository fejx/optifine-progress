package fejx.optifineProgress.update

import fejx.util.*
import java.time.*
import java.util.*
import kotlin.concurrent.timerTask

class UpdateTracker<T>(private val fetchUpdate: () -> T?, private val interval: Duration) {
	private var lastValue: T? = null
	private val timer = Timer("updateTracker")
	private val onUpdate = EventHandler<T>()
	private val onException = EventHandler<Exception>()

	val update = Event(onUpdate)
	val exception = Event(onException)

	fun start() {
		val task = timerTask { checkForUpdate() }
		timer.scheduleAtFixedRate(
				task,
				Date(),
				interval.toMillis()
		)
	}

	fun stop() {
		timer.cancel()
	}

	private fun checkForUpdate() {
		val updateResult = tryFetchUpdate()
		val newValue = updateResult ?: return
		if (newValue == lastValue) return
		lastValue = newValue
		onUpdate(newValue)
	}

	private fun tryFetchUpdate(): T? {
		return try {
			fetchUpdate()
		} catch (exception: Exception) {
			handleException(exception)
			null
		}
	}

	private fun handleException(exception: Exception) {
		if (onException.hasListener)
			onException(exception)
		else
			throw exception
	}
}
