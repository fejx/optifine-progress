package fejx.optifineProgress.page

class OptifineProgressExtractor : ProgressExtractor {
	private val regex = """Update to Minecraft 1\.13: (\d{1,3})%""".toRegex()

	override fun extract(pageLines: Sequence<String>): Int? {
		pageLines.forEach {
			val result = regex.find(it) ?: return@forEach
			val group = result.groups[1] ?: return@forEach
			val groupValue = group.value
			return groupValue.toIntOrNull() ?: return@forEach
		}
		throw Exception("Could not parse progress!")
	}
}
