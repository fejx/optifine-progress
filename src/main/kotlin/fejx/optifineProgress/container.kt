package fejx.optifineProgress

import fejx.optifineProgress.page.*
import fejx.optifineProgress.settings.SettingsProvider
import fejx.optifineProgress.update.*
import org.kodein.di.Kodein
import org.kodein.di.generic.*

val container = Kodein {
	bind<SettingsProvider>() with provider { SettingsProvider() }
	bind<PageDownloader>() with provider { PageDownloader() }
	bind<ProgressExtractor>() with provider { OptifineProgressExtractor() }
	bind<UpdateProvider<Int>>() with provider {
		OptifineProgressUpdateProvider()
	}
}
