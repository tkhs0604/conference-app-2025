package io.github.droidkaigi.confsched.sessions

import io.github.droidkaigi.confsched.model.core.Lang
import io.github.droidkaigi.confsched.model.sessions.TimetableItem

data class TimetableItemDetailScreenUiState(
    val timetableItem: TimetableItem,
    val isBookmarked: Boolean,
    val currentLang: Lang,
)
