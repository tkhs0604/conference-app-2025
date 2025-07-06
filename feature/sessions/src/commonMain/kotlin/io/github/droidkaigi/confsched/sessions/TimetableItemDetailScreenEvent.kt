package io.github.droidkaigi.confsched.sessions

sealed interface TimetableItemDetailScreenEvent {
    data class Bookmark(val isBookmarked: Boolean) : TimetableItemDetailScreenEvent
}
