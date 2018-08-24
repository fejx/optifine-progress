package fejx.optifineProgress.ui

import fejx.optifineProgress.container
import fejx.optifineProgress.settings.SettingsProvider
import fejx.optifineProgress.update.UpdateProvider
import javafx.beans.property.SimpleStringProperty
import org.kodein.di.generic.instance
import tornadofx.*
import java.awt.Desktop
import java.net.URI

internal class MainViewModel : ViewModel() {
	private val updateProvider: UpdateProvider<Int> by container.instance()
	private val settingsProvider: SettingsProvider by container.instance()

	val mainText = SimpleStringProperty("Please stand by...")
	val errorText = SimpleStringProperty()
	val linkText = SimpleStringProperty(settingsProvider.downloadUrl)

	init {
		updateProvider.updateReceived += {
			mainText.set("Current progress: $it%")
			errorText.set("Last fetch successful")
		}
		updateProvider.errorOccurred += {
			errorText.set(it)
		}
	}

	fun viewOpened() {
		updateProvider.startListen()
	}

	fun viewClosed() {
		updateProvider.stopListen()
	}

	fun linkClicked() {
		Desktop.getDesktop().browse(URI(settingsProvider.downloadUrl))
	}
}
