package fejx.optifineProgress.ui

import javafx.geometry.Pos
import javafx.scene.layout.Border
import javafx.scene.text.Font.font
import tornadofx.*

class MainView : View("Optifine Progress") {
	private val viewModel: MainViewModel by inject()

	override fun onBeforeShow() {
		super.onBeforeShow()
		viewModel.viewOpened()
	}

	override fun onUndock() {
		super.onUndock()
		viewModel.viewClosed()
	}

	override val root = vbox {
		alignment = Pos.CENTER
		setPrefSize(500.0, 200.0)
		text(viewModel.mainText) {
			font = font(40.0)
		}
		text(viewModel.errorText) {
			font = font(30.0)
		}
		hyperlink(viewModel.linkText)
		{
			border = Border.EMPTY
			font = font(30.0)
			action { viewModel.linkClicked() }
		}
	}
}

