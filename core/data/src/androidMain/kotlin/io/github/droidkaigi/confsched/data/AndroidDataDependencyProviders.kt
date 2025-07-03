package io.github.droidkaigi.confsched.data

import android.content.Context
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import io.github.droidkaigi.confsched.data.core.DataStorePathProducer
import io.github.droidkaigi.confsched.data.core.defaultJson
import io.github.droidkaigi.confsched.data.core.defaultKtorConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.serialization.json.Json

@ContributesTo(AppScope::class)
public interface AndroidDataDependencyProviders {
    @Provides
    public fun provideDataStorePathProducer(context: Context): DataStorePathProducer {
        return object : DataStorePathProducer {
            override fun producePath(fileName: String): String {
                return context.cacheDir.resolve(fileName).path
            }
        }
    }

    @Provides
    public fun provideHttpClient(json: Json): HttpClient {
        return HttpClient(OkHttp) {
            defaultKtorConfig(json)
        }
    }
}
