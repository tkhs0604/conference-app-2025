package io.github.droidkaigi.confsched.sessions

import io.github.droidkaigi.confsched.model.sessions.TimetableItem
import io.github.droidkaigi.confsched.sessions.components.SearchFilters
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.PersistentMap
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf

data class SearchScreenUiState(
    val searchQuery: String = "",
    val filteredSessions: ImmutableList<TimetableItem> = persistentListOf(),
    val groupedSessions: PersistentMap<SearchTimeSlot, List<TimetableItem>> = persistentMapOf(),
    val availableFilters: SearchFilters = SearchFilters(
        selectedDay = null,
        selectedCategory = null,
        selectedSessionType = null,
        selectedLanguage = null,
        availableDays = emptyList(),
        availableCategories = emptyList(),
        availableSessionTypes = emptyList(),
        availableLanguages = emptyList()
    ),
    val isLoading: Boolean = false,
    val isNotFound: Boolean = false,
    val hasSearchCriteria: Boolean = false
) {
    val showEmptyState: Boolean
        get() = isNotFound && !isLoading && hasSearchCriteria
    
    val showInitialState: Boolean
        get() = !hasSearchCriteria && !isLoading
}
