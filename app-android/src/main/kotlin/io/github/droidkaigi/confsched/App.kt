package io.github.droidkaigi.confsched

import android.app.Application
import android.content.Context
import dev.zacsweers.metro.createGraph

class App : Application() {
    val appGraph: AppGraph by lazy {
        ContextHelper.currentContext = this
        createGraph<AppGraph>()
    }
}

val Context.appGraph: AppGraph get() = (applicationContext as App).appGraph
