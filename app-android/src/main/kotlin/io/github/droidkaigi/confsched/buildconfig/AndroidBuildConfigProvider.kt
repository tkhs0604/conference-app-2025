package io.github.droidkaigi.confsched.buildconfig

import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.BuildConfig
import io.github.droidkaigi.confsched.model.buildconfig.BuildConfigProvider

@Inject
class AndroidBuildConfigProvider : BuildConfigProvider {
    override val versionName: String
        get() = BuildConfig.VERSION_NAME
}
