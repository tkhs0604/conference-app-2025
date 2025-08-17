package io.github.droidkaigi.confsched.sessions

import io.github.droidkaigi.confsched.sessions.components.SearchFilter

sealed interface SearchScreenEvent {
    data class Search(val query: String) : SearchScreenEvent
    data class ToggleFilter(val filter: SearchFilter) : SearchScreenEvent
    data class Bookmark(val sessionId: String) : SearchScreenEvent
    data object ClearFilters : SearchScreenEvent
}