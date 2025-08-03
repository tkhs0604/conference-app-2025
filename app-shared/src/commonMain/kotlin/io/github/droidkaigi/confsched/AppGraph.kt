package io.github.droidkaigi.confsched

import dev.zacsweers.metro.Provides
import io.github.droidkaigi.confsched.about.AboutScreenContext
import io.github.droidkaigi.confsched.buildconfig.DefaultBuildConfigProvider
import io.github.droidkaigi.confsched.contributors.ContributorsScreenContext
import io.github.droidkaigi.confsched.favorites.FavoritesScreenContext
import io.github.droidkaigi.confsched.model.buildconfig.BuildConfigProvider
import io.github.droidkaigi.confsched.sessions.SearchScreenContext
import io.github.droidkaigi.confsched.sessions.TimetableItemDetailScreenContext
import io.github.droidkaigi.confsched.sessions.TimetableScreenContext

interface AppGraph :
    TimetableScreenContext.Factory,
    TimetableItemDetailScreenContext.Factory,
    SearchScreenContext.Factory,
    ContributorsScreenContext.Factory,
    FavoritesScreenContext.Factory,
    AboutScreenContext.Factory {

    @Provides
    fun provideBuildConfigProvider(): BuildConfigProvider {
        return DefaultBuildConfigProvider()
    }
}
