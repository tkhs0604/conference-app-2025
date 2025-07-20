package io.github.droidkaigi.confsched.testing.di

import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import io.github.droidkaigi.confsched.data.CoroutineDependenciesProviders
import io.github.droidkaigi.confsched.data.DataScope
import io.github.droidkaigi.confsched.data.annotations.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher

@ContributesTo(DataScope::class, replaces = [CoroutineDependenciesProviders::class])
interface TestCoroutineDependenciesProviders {
    @IoDispatcher
    @Provides
    fun provideIoDispatcher(
        testDispatcher: TestDispatcher
    ): CoroutineDispatcher = testDispatcher

    @SingleIn(DataScope::class)
    @Provides
    fun provideTestDispatcher(): TestDispatcher = StandardTestDispatcher()
}
