package io.github.droidkaigi.confsched.main

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class MainScreenTab(
    val label: StringResource,
    val iconOn: DrawableResource,
    val iconOff: DrawableResource,
) {
    Timetable(
        label = MainRes.string.timetable,
        iconOn = MainRes.drawable.ic_timetable_fill,
        iconOff = MainRes.drawable.ic_timetable_outline,
    ),
    EventMap(
        label = MainRes.string.event_map,
        iconOn = MainRes.drawable.ic_event_map_fill,
        iconOff = MainRes.drawable.ic_event_map_outline,
    ),
    Favorite(
        label = MainRes.string.favorite,
        iconOn = MainRes.drawable.ic_favorite_fill,
        iconOff = MainRes.drawable.ic_favorite_outline,
    ),
    About(
        label = MainRes.string.about,
        iconOn = MainRes.drawable.ic_about_fill,
        iconOff = MainRes.drawable.ic_about_outline,
    ),
    ProfileCard(
        label = MainRes.string.profile_card,
        iconOn = MainRes.drawable.ic_profile_card_fill,
        iconOff = MainRes.drawable.ic_profile_card_outline,
    ),
}
