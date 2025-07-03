package io.github.droidkaigi.confsched.data.core

public interface DataStorePathProducer {
    public fun producePath(fileName: String): String
}
