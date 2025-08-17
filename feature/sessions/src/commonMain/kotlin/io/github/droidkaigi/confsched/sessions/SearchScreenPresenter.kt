package io.github.droidkaigi.confsched.sessions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import io.github.droidkaigi.confsched.common.compose.EventEffect
import io.github.droidkaigi.confsched.common.compose.EventFlow
import io.github.droidkaigi.confsched.common.compose.providePresenterDefaults
import io.github.droidkaigi.confsched.model.core.DroidKaigi2025Day
import io.github.droidkaigi.confsched.model.core.Filters
import io.github.droidkaigi.confsched.model.core.Lang
import io.github.droidkaigi.confsched.model.sessions.Timetable
import io.github.droidkaigi.confsched.model.sessions.TimetableCategory
import io.github.droidkaigi.confsched.model.sessions.TimetableSessionType
import io.github.droidkaigi.confsched.sessions.components.SearchFilter
import io.github.droidkaigi.confsched.sessions.components.SearchFilters
import io.github.takahirom.rin.rememberRetained
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.collections.immutable.toPersistentMap
import kotlinx.datetime.LocalTime
import io.github.droidkaigi.confsched.droidkaigiui.session.TimeSlotItem

data class SearchTimeSlot(
    val startTime: LocalTime,
    val endTime: LocalTime,
    override val startTimeString: String,
    override val endTimeString: String,
) : TimeSlotItem {
    override val key: String = "$startTimeString-$endTimeString"
}

@Composable
context(screenContext: SearchScreenContext)
fun searchScreenPresenter(
    eventFlow: EventFlow<SearchScreenEvent>,
    timetable: Timetable,
): SearchScreenUiState = providePresenterDefaults {
    var searchQuery by rememberRetained { mutableStateOf("") }
    var selectedDay by rememberRetained { mutableStateOf<DroidKaigi2025Day?>(null) }
    var selectedCategory by rememberRetained { mutableStateOf<TimetableCategory?>(null) }
    var selectedSessionType by rememberRetained { mutableStateOf<TimetableSessionType?>(null) }
    var selectedLanguage by rememberRetained { mutableStateOf<Lang?>(null) }

    EventEffect(eventFlow) { event ->
        when (event) {
            is SearchScreenEvent.Search -> searchQuery = event.query
            is SearchScreenEvent.ToggleFilter -> {
                when (val filter = event.filter) {
                    is SearchFilter.Day -> {
                        selectedDay = if (selectedDay == filter.day) null else filter.day
                    }
                    is SearchFilter.Category -> {
                        selectedCategory = if (selectedCategory == filter.category) null else filter.category
                    }
                    is SearchFilter.SessionType -> {
                        selectedSessionType = if (selectedSessionType == filter.sessionType) null else filter.sessionType
                    }
                    is SearchFilter.Language -> {
                        selectedLanguage = if (selectedLanguage == filter.language) null else filter.language
                    }
                }
            }
            is SearchScreenEvent.Bookmark -> {
                // TODO: Implement bookmark mutation
            }
            SearchScreenEvent.ClearFilters -> {
                selectedDay = null
                selectedCategory = null
                selectedSessionType = null
                selectedLanguage = null
            }
        }
    }

    val hasSearchCriteria = searchQuery.isNotEmpty() || 
        selectedDay != null || 
        selectedCategory != null || 
        selectedSessionType != null || 
        selectedLanguage != null

    val filteredTimetable = if (hasSearchCriteria) {
        timetable.filtered(
            Filters(
                searchWord = searchQuery,
                days = selectedDay?.let { listOf(it) } ?: emptyList(),
                categories = selectedCategory?.let { listOf(it) } ?: emptyList(),
                sessionTypes = selectedSessionType?.let { listOf(it) } ?: emptyList(),
                languages = selectedLanguage?.let { listOf(it) } ?: emptyList(),
            )
        )
    } else {
        timetable.copy(timetableItems = persistentListOf())
    }

    val groupedSessions = filteredTimetable.timetableItems
        .groupBy { session ->
            SearchTimeSlot(
                startTime = session.startsLocalTime,
                endTime = session.endsLocalTime,
                startTimeString = session.startsTimeString,
                endTimeString = session.endsTimeString
            )
        }
        .mapValues { entries ->
            entries.value.sortedWith(
                compareBy({ it.day?.name.orEmpty() }, { it.startsTimeString })
            )
        }
        .toPersistentMap()

    SearchScreenUiState(
        searchQuery = searchQuery,
        filteredSessions = filteredTimetable.timetableItems.toPersistentList(),
        groupedSessions = groupedSessions,
        availableFilters = SearchFilters(
            selectedDay = selectedDay,
            selectedCategory = selectedCategory,
            selectedSessionType = selectedSessionType,
            selectedLanguage = selectedLanguage,
            availableDays = DroidKaigi2025Day.visibleDays(),
            availableCategories = timetable.timetableItems.mapNotNull { it.category }.distinct(),
            availableSessionTypes = timetable.timetableItems.map { it.sessionType }.distinct(),
            availableLanguages = listOf(Lang.JAPANESE, Lang.ENGLISH)
        ),
        isNotFound = hasSearchCriteria && filteredTimetable.timetableItems.isEmpty(),
        hasSearchCriteria = hasSearchCriteria,
    )
}
