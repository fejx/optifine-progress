package fejx.optifineProgress.page

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

internal class OptifineProgressExtractorTest {
	private val progressExtractor = OptifineProgressExtractor()

	@Test
	fun testEmptySequence() {
		assertThrows<Exception> {
			progressExtractor.extract(emptySequence())
		}
	}

	@Test
	fun irrelevantSequence() {
		val sequence = sequenceOf("some irrelevant", "text")
		assertThrows<Exception> {
			progressExtractor.extract(sequence)
		}
	}

	@Test
	fun oneDigitSequence() {
		val sequence = sequenceOf("some text", "<b>Update to Minecraft 1.13: 3%</b>")
		assertEquals(3, progressExtractor.extract(sequence))
	}

	@Test
	fun twoDigitSequence() {
		val sequence = sequenceOf("some text", "<b>Update to Minecraft 1.13: 40%</b>")
		assertEquals(40, progressExtractor.extract(sequence))
	}

	@Test
	fun threeDigitSequence() {
		val sequence = sequenceOf("some text", "<b>Update to Minecraft 1.13: 100%</b>")
		assertEquals(100, progressExtractor.extract(sequence))
	}

	@Test
	fun realSequence() {
		assertEquals(26, progressExtractor.extract(realSequence))
	}
}

val realSequence = sequenceOf(
		"""<!DOCTYPE html>""",
		"""<html>""",
		"""  <head>""",
		"""    <title>OptiFine</title>""",
		"""    <meta name="keywords" content="optifine, minecraft, fps, lag, antialiasing, hd textures" />""",
		"""    <style type="text/css">""",
		"""      body {font-family:arial,helvetica,sans-serif;""",
		"""            background-image:url('images/snow64x.gif');}""",
		"""      A {color: rgb(100,128,255); text-decoration: none; }""",
		"""      .tableRoot {margin: 0 auto;""",
		"""                  width: 800px;""",
		"""                  background: white;""",
		"""                  border: 1px solid rgb(128,128,128);}""",
		"""    </style>""",
		"""""",
		"""  </head>""",
		"""  <body>""",
		"""        <table class="tableRoot">""",
		"""      <tr>""",
		"""        <td class="title">""",
		"""          <!-- Title -->""",
		"""          <a href="home">OptiFine</a>""",
		"""        </td>""",
		"""      </tr>""",
		"""      <tr>""",
		"""        <td class="tableNav">""",
		"""          <!-- Navigation -->""",
		"""          <table class="tableNav">""",
		"""          <tr>""",
		"""            <td>""",
		"""            <a href="home">Home</a>""",
		"""            </td>""",
		"""            </tr>""",
		"""          </table>""",
		"""        </td>""",
		"""      </tr>""",
		"""      <tr>""",
		"""        <td class="content">""",
		"""          <!-- Content -->""",
		"""""",
		"""    <p>OptiFine is a Minecraft optimization mod.</p>""",
		"""    <p>It allows Minecraft to run faster and look better with full support for HD textures and many configuration""",
		"""    <p>The official OptiFine description is on the <a href="http://www.minecraftforum.net/topic/249637-">Minecraft""",
		"""Forums</a>.</p>""",
		"""    <p>Resources: <a href="https://github.com/sp614x/optifine/tree/master/OptiFineDoc/assets/minecraft/optifine/lang""",
		""" target="_blank">translation</a>""",
		"""      <a href="https://github.com/sp614x/optifine/tree/master/OptiFineDoc/doc" target="_blank">documentation</a>,""",
		"""      <a href="https://github.com/sp614x/optifine/issues" target="_blank">issue tracker</a>.</p>""",
		"""    <p><b>Update to Minecraft 1.13: 26%</b> (merging OptiFine changes)</p>""",
		"""""",
		"""              <!-- End of content -->""",
		"""        </td>""",
		"""      </tr>""",
		"""      <tr>""",
		"""      </tr>""",
		"""      </table>""",
		"""""",
		"""  </body>""",
		"""</html"""
)
