package io.github.confsched.profile

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesGraphExtension
import io.github.droidkaigi.confsched.common.scope.ProfileScope
import io.github.droidkaigi.confsched.context.ScreenContext
import io.github.droidkaigi.confsched.model.profile.ProfileMutationKey
import io.github.droidkaigi.confsched.model.profile.ProfileSubscriptionKey

@ContributesGraphExtension(ProfileScope::class)
interface ProfileScreenContext : ScreenContext {
    val profileSubscriptionKey: ProfileSubscriptionKey
    val profileMutationKey: ProfileMutationKey

    @ContributesGraphExtension.Factory(AppScope::class)
    fun interface Factory {
        fun createProfileScreenContext(): ProfileScreenContext
    }
}
