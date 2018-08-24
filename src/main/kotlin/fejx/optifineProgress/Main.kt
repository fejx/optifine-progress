package fejx.optifineProgress

import fejx.optifineProgress.ui.MainView
import javafx.application.Application
import tornadofx.*

class OptifineProgressApp : App(MainView::class)

fun main(args: Array<String>) {
	Application.launch(OptifineProgressApp::class.java, *args)
}
