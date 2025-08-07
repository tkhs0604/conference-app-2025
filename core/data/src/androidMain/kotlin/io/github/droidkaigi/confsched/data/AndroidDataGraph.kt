package io.github.droidkaigi.confsched.data

import android.content.Context
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import io.github.droidkaigi.confsched.data.core.DataStorePathProducer
import io.github.droidkaigi.confsched.data.core.defaultKtorConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.serialization.json.Json

@ContributesTo(DataScope::class)
public interface AndroidDataGraph {
    @Provides
    public fun provideDataStorePathProducer(context: Context): DataStorePathProducer {
        return DataStorePathProducer { fileName ->
            context.cacheDir.resolve(fileName).path
        }
    }

    @Provides
    public fun provideHttpClient(json: Json): HttpClient {
        return HttpClient(OkHttp) {
            defaultKtorConfig(json)
        }
    }
}
