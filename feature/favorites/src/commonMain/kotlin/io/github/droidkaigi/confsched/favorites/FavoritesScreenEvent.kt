package io.github.droidkaigi.confsched.favorites

import io.github.droidkaigi.confsched.model.sessions.TimetableItemId

sealed interface FavoritesScreenEvent {
    data class Bookmark(val id: TimetableItemId) : FavoritesScreenEvent

    data object FilterAllDays : FavoritesScreenEvent
    data object FilterDay1 : FavoritesScreenEvent
    data object FilterDay2 : FavoritesScreenEvent
}
