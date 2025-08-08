package io.github.droidkaigi.confsched.sessions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ScrolledToCurrentTimeState(
    inTimetableList: Boolean = false,
    inTimetableGrid: Boolean = false,
) {
    var inTimetableList: Boolean by mutableStateOf(inTimetableList)
        private set
    var inTimetableGrid: Boolean by mutableStateOf(inTimetableGrid)
        private set

    fun scrolledInTimetableList() {
        inTimetableList = true
    }

    fun scrolledInTimetableGrid() {
        inTimetableGrid = true
    }
}
