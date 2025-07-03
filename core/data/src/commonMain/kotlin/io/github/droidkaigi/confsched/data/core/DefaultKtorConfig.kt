package io.github.droidkaigi.confsched.data.core

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

public fun HttpClientConfig<*>.defaultKtorConfig(
    ktorJsonSettings: Json,
) {
    install(ContentNegotiation) {
        json(ktorJsonSettings)
    }
}

public fun defaultJson(): Json {
    return Json {
        encodeDefaults = true
        isLenient = true
        allowSpecialFloatingPointValues = true
        allowStructuredMapKeys = true
        prettyPrint = false
        useArrayPolymorphism = false
        ignoreUnknownKeys = true
    }
}
