package io.github.droidkaigi.confsched.sessions

import io.github.droidkaigi.confsched.model.sessions.TimetableItem

// TODO: optimise
data class TimetableItemDetailScreenUiState(
    val timetableItem: TimetableItem,
    val isBookmarked: Boolean,
)
