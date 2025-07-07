package io.github.droidkaigi.confsched.data

import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import io.github.droidkaigi.confsched.data.core.DataStorePathProducer
import io.github.droidkaigi.confsched.data.core.defaultKtorConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.serialization.json.Json
import java.nio.file.Paths

@DependencyGraph(DataScope::class, isExtendable = true)
public interface JvmDataGraph : DataGraph {
    @Provides
    public fun provideDataStorePathProducer(): DataStorePathProducer {
        return object : DataStorePathProducer {
            override fun producePath(fileName: String): String {
                val configDir = Paths.get(System.getProperty("user.home"), ".config", "myapp")
                val dataStoreFile = configDir.resolve(fileName)
                dataStoreFile.toFile().parentFile.mkdirs() // Ensure the directory exists
                return dataStoreFile.toString()
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
