package io.github.droidkaigi.confsched.testing

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides

@ContributesTo(AppScope::class)
actual interface PlatformTestDependencies {
    @Provides
    fun provideContext(): Context {
        return ApplicationProvider.getApplicationContext<TestApplication>()
    }
}
