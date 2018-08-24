package fejx.optifineProgress.update

import fejx.util.Event

interface UpdateProvider<T> {
	fun startListen()
	fun stopListen()
	val updateReceived: Event<T>
	val errorOccurred: Event<String>
}
