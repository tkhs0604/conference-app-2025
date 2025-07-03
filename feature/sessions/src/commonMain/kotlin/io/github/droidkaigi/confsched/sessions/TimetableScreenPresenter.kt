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
import io.github.droidkaigi.confsched.model.sessions.Timetable
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import io.github.droidkaigi.confsched.model.sessions.TimetableUiType
import io.github.droidkaigi.confsched.sessions.section.TimetableGridUiState
import io.github.droidkaigi.confsched.sessions.section.TimetableListUiState
import io.github.droidkaigi.confsched.sessions.section.TimetableUiState
import io.github.takahirom.rin.rememberRetained
import kotlinx.collections.immutable.toPersistentMap
import soil.query.compose.rememberMutation

context(screenContext: TimetableScreenContext)
@Composable
fun timetableScreenPresenter(
    eventFlow: EventFlow<TimetableScreenEvent>,
    timetable: Timetable,
): TimetableScreenUiState = providePresenterDefaults {
    val favoriteTimetableItemIdMutation = rememberMutation(screenContext.favoriteTimetableItemIdMutationKey)

    var uiType by rememberRetained { mutableStateOf(TimetableUiType.Grid) }
    var selectedDay by rememberRetained { mutableStateOf(DroidKaigi2025Day.ConferenceDay1) }

    EventEffect(eventFlow) { event ->
        when (event) {
            is TimetableScreenEvent.Bookmark -> {
                favoriteTimetableItemIdMutation.mutate(TimetableItemId(event.sessionId))
            }

            is TimetableScreenEvent.SelectTab -> selectedDay = event.day

            is TimetableScreenEvent.UiTypeChange -> {
                uiType = if (uiType == TimetableUiType.Grid) {
                    TimetableUiType.List
                } else {
                    TimetableUiType.Grid
                }
            }
        }
    }

    TimetableScreenUiState(
        timetable = timetableSheet(
            sessionTimetable = timetable,
            uiType = uiType,
            selectedDay = selectedDay,
        ),
        uiType = uiType,
    )
}

private fun timetableSheet(
    sessionTimetable: Timetable,
    uiType: TimetableUiType,
    selectedDay: DroidKaigi2025Day,
): TimetableUiState {
    if (sessionTimetable.timetableItems.isEmpty()) {
        return TimetableUiState.Empty
    }

    return if (uiType == TimetableUiType.List) {
        TimetableUiState.ListTimetable(
            DroidKaigi2025Day.visibleDays().associateWith { day ->
                val sortAndGroupedTimetableItems = sessionTimetable.filtered(
                    Filters(
                        days = listOf(day),
                    ),
                ).timetableItems.groupBy {
                    TimetableListUiState.TimeSlot(
                        startTime = it.startsLocalTime,
                        endTime = it.endsLocalTime,
                    )
                }.mapValues { entries ->
                    entries.value.sortedWith(
                        compareBy({ it.day?.name.orEmpty() }, { it.startsTimeString }),
                    )
                }.toPersistentMap()
                TimetableListUiState(
                    timetableItemMap = sortAndGroupedTimetableItems,
                    timetable = sessionTimetable.dayTimetable(day),
                )
            },
            selectedDay = selectedDay,
        )
    } else {
        TimetableUiState.GridTimetable(
            timetableGridUiState = DroidKaigi2025Day.visibleDays().associateWith { day ->
                TimetableGridUiState(
                    timetable = sessionTimetable.dayTimetable(day),
                )
            },
            selectedDay = selectedDay,
        )
    }
}
