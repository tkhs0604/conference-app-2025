package io.github.droidkaigi.confsched.sessions

import io.github.droidkaigi.confsched.model.core.DroidKaigi2025Day

sealed interface TimetableScreenEvent {
    data class Bookmark(val sessionId: String, val isBookmarked: Boolean) : TimetableScreenEvent
    data object UiTypeChange : TimetableScreenEvent
    data class SelectTab(val day: DroidKaigi2025Day) : TimetableScreenEvent
}
