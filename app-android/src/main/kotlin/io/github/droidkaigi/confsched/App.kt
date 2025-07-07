package io.github.droidkaigi.confsched

import android.app.Application
import android.content.Context
import dev.zacsweers.metro.createGraphFactory
import io.github.droidkaigi.confsched.data.AndroidDataGraph

class App : Application() {
    val appGraph: AppGraph by lazy {
        val dataGraph = createGraphFactory<AndroidDataGraph.Factory>().createAndroidDataGraph(applicationContext)
        createGraphFactory<AndroidAppGraph.Factory>()
            .createAndroidAppGraph(dataGraph)
    }
}

val Context.appGraph: AppGraph get() = (applicationContext as App).appGraph
