package io.github.droidkaigi.confsched.favorites

import io.github.droidkaigi.confsched.droidkaigiui.session.TimeSlotItem
import io.github.droidkaigi.confsched.model.core.DroidKaigi2025Day
import io.github.droidkaigi.confsched.model.sessions.TimetableItem
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.PersistentMap

data class FavoritesScreenUiState(
    val filterState: FilterState,
    val timetableContentState: TimetableContentState
) {
    data class FilterState(
        val currentDayFilter: PersistentList<DroidKaigi2025Day>,
        val allFilterSelected: Boolean
    ) {
        val isDay1FilterSelected: Boolean
            get() = allFilterSelected.not() && currentDayFilter.contains(DroidKaigi2025Day.ConferenceDay1)
        val isDay2FilterSelected: Boolean
            get() = allFilterSelected.not() && currentDayFilter.contains(DroidKaigi2025Day.ConferenceDay2)
    }

    sealed interface TimetableContentState {
        data object Empty: TimetableContentState

        data class FavoriteList(
            val timetableItemMap: PersistentMap<TimeSlot, List<TimetableItem>>,
        ) : TimetableContentState {
            data class TimeSlot(
                val day: DroidKaigi2025Day?,
                override val startTimeString: String,
                override val endTimeString: String,
            ) : TimeSlotItem {
                override val key: String get() = "${day?.ordinal}-$startTimeString-$endTimeString"
            }
        }
    }
}
