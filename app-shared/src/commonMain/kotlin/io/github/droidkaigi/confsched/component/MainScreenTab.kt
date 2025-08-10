package io.github.droidkaigi.confsched.component

import io.github.droidkaigi.confsched.app_shared.AppSharedRes
import io.github.droidkaigi.confsched.app_shared.about
import io.github.droidkaigi.confsched.app_shared.event_map
import io.github.droidkaigi.confsched.app_shared.favorite
import io.github.droidkaigi.confsched.app_shared.ic_about_fill
import io.github.droidkaigi.confsched.app_shared.ic_about_outline
import io.github.droidkaigi.confsched.app_shared.ic_event_map_fill
import io.github.droidkaigi.confsched.app_shared.ic_event_map_outline
import io.github.droidkaigi.confsched.app_shared.ic_favorite_fill
import io.github.droidkaigi.confsched.app_shared.ic_favorite_outline
import io.github.droidkaigi.confsched.app_shared.ic_profile_card_fill
import io.github.droidkaigi.confsched.app_shared.ic_profile_card_outline
import io.github.droidkaigi.confsched.app_shared.ic_timetable_fill
import io.github.droidkaigi.confsched.app_shared.ic_timetable_outline
import io.github.droidkaigi.confsched.app_shared.profile_card
import io.github.droidkaigi.confsched.app_shared.timetable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class MainScreenTab(
    val label: StringResource,
    val iconOn: DrawableResource,
    val iconOff: DrawableResource,
) {
    Timetable(
        label = AppSharedRes.string.timetable,
        iconOn = AppSharedRes.drawable.ic_timetable_fill,
        iconOff = AppSharedRes.drawable.ic_timetable_outline,
    ),
    EventMap(
        label = AppSharedRes.string.event_map,
        iconOn = AppSharedRes.drawable.ic_event_map_fill,
        iconOff = AppSharedRes.drawable.ic_event_map_outline,
    ),
    Favorite(
        label = AppSharedRes.string.favorite,
        iconOn = AppSharedRes.drawable.ic_favorite_fill,
        iconOff = AppSharedRes.drawable.ic_favorite_outline,
    ),
    About(
        label = AppSharedRes.string.about,
        iconOn = AppSharedRes.drawable.ic_about_fill,
        iconOff = AppSharedRes.drawable.ic_about_outline,
    ),
    Profile(
        label = AppSharedRes.string.profile_card,
        iconOn = AppSharedRes.drawable.ic_profile_card_fill,
        iconOff = AppSharedRes.drawable.ic_profile_card_outline,
    ),
}
