package io.github.droidkaigi.confsched

import android.annotation.SuppressLint
import android.content.Context
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides

@ContributesTo(AppScope::class)
actual interface PlatformAppDependencyProvider {
    @Provides
    fun provideContext(): Context {
        return ContextHelper.currentContext
    }
}

@SuppressLint("StaticFieldLeak")
object ContextHelper {
    lateinit var currentContext: Context
}
