package io.github.droidkaigi.confsched.sessions

import io.github.droidkaigi.confsched.model.core.Lang

sealed interface TimetableItemDetailScreenEvent {
    data class Bookmark(val isBookmarked: Boolean) : TimetableItemDetailScreenEvent
    data class LanguageSelect(val lang: Lang) : TimetableItemDetailScreenEvent
}
