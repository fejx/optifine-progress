package fejx.optifineProgress.page

import java.io.BufferedReader
import java.net.URL

class PageDownloader {
	fun downloadPage(url: String): Sequence<String> {
		val reader = getBufferedReader(url)
		return reader.lineSequence()
	}

	private fun getBufferedReader(url: String): BufferedReader {
		val page = URL(url)
		val stream = page.openStream()
		return stream.bufferedReader()
	}
}
