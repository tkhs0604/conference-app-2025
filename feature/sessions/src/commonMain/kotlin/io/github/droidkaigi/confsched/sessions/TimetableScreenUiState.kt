package io.github.droidkaigi.confsched.sessions

import io.github.droidkaigi.confsched.model.sessions.TimetableUiType
import io.github.droidkaigi.confsched.sessions.section.TimetableUiState

data class TimetableScreenUiState(
    val timetable: TimetableUiState,
    val uiType: TimetableUiType,
)
