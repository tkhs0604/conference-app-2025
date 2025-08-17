package io.github.droidkaigi.confsched.buildconfig

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import io.github.droidkaigi.BuildKonfig
import io.github.droidkaigi.confsched.model.buildconfig.BuildConfigProvider

@ContributesBinding(AppScope::class)
@Inject
class DefaultBuildConfigProvider : BuildConfigProvider {
    override val versionName: String
        get() = BuildKonfig.versionName
}
