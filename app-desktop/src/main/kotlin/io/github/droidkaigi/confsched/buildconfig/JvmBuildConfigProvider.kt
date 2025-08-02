package io.github.droidkaigi.confsched.buildconfig

import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.model.buildconfig.BuildConfigProvider

@Inject
class JvmBuildConfigProvider : BuildConfigProvider {
    override val versionName: String
        get() = "1.0.0" // TODO: dynamically fetch
}
