package io.github.droidkaigi.confsched.sessions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberTimetableState(
    timetableScrollState: TimetableScrollState = rememberTimetableScrollState(),
): TimetableState = remember {
    TimetableState(timetableScrollState)
}

data class TimetableState(
    val timetableScrollState: TimetableScrollState,
)
