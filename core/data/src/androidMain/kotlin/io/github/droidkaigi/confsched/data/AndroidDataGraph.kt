package io.github.droidkaigi.confsched.data

import android.content.Context
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import io.github.droidkaigi.confsched.data.core.DataStorePathProducer
import io.github.droidkaigi.confsched.data.core.defaultKtorConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.serialization.json.Json

@DependencyGraph(DataScope::class, isExtendable = true)
public interface AndroidDataGraph : DataGraph {
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

    @DependencyGraph.Factory
    public fun interface Factory {
        public fun createAndroidDataGraph(
            @Provides applicationContext: Context,
        ): AndroidDataGraph
    }
}
