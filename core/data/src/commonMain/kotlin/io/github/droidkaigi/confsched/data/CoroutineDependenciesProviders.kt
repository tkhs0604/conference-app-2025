package io.github.droidkaigi.confsched.data

import dev.zacsweers.metro.ContributesTo
import io.github.droidkaigi.confsched.data.annotations.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@ContributesTo(DataScope::class)
public interface CoroutineDependenciesProviders {
    @IoDispatcher
    public fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
