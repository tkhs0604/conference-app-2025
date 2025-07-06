package io.github.droidkaigi.confsched.data

import de.jensklingenberg.ktorfit.Ktorfit
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Named
import dev.zacsweers.metro.Provides
import io.github.droidkaigi.confsched.data.core.defaultJson
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json

@ContributesTo(AppScope::class)
public interface DataDependencyProviders {
    @Named("apiBaseUrl")
    public val apiBaseUrl: String

    @Named("apiBaseUrl")
    @Provides
    public fun provideApiBaseUrl(): String {
        return "TODO: Provide API base URL in a proper way"
    }

    @Provides
    public fun provideKtorfit(
        httpClient: HttpClient,
        @Named("apiBaseUrl") apiBaseUrl: String,
    ): Ktorfit {
        return Ktorfit.Builder()
            .httpClient(httpClient)
            .baseUrl(apiBaseUrl)
            .build()
    }

    @Provides
    public fun provideJson(): Json {
        return defaultJson()
    }
}
