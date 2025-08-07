package io.github.droidkaigi.confsched.data.core

public fun interface DataStorePathProducer {
    public fun producePath(fileName: String): String
}
