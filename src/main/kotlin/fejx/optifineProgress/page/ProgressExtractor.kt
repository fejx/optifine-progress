package fejx.optifineProgress.page

interface ProgressExtractor {
	fun extract(pageLines: Sequence<String>): Int?
}
