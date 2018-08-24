package fejx.optifineProgress.update

import fejx.optifineProgress.container
import fejx.optifineProgress.page.*
import fejx.optifineProgress.settings.SettingsProvider
import fejx.util.*
import org.kodein.di.generic.instance

class OptifineProgressUpdateProvider : UpdateProvider<Int> {
	private val settingsProvider: SettingsProvider by container.instance()
	private val pageDownloader: PageDownloader by container.instance()
	private val progressExtractor: ProgressExtractor by container.instance()

	private val updateTracker = UpdateTracker({ fetchProgress() }, settingsProvider.updateInterval)

	private val onUpdateReceived = EventHandler<Int>()
	override val updateReceived = Event(onUpdateReceived)
	private val onErrorOccurred = EventHandler<String>()
	override val errorOccurred = Event(onErrorOccurred)

	init {
		updateTracker.update += {
			onUpdateReceived(it)
		}
		updateTracker.exception += { exception ->
			if (onErrorOccurred.hasListener) {
				val message = "$exception: ${exception.message}"
				onErrorOccurred(message)
			} else
				throw exception
		}
	}

	override fun startListen() {
		updateTracker.start()
	}

	override fun stopListen() {
		updateTracker.stop()
	}

	private fun fetchProgress(): Int? {
		val pageLines = pageDownloader.downloadPage(settingsProvider.downloadUrl)
		return progressExtractor.extract(pageLines)
	}
}
