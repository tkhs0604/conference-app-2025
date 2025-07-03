package io.github.droidkaigi.confsched.sessions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import io.github.droidkaigi.confsched.model.sessions.TimetableUiType
import io.github.droidkaigi.confsched.sessions.components.TimetableTopAppBar
import io.github.droidkaigi.confsched.sessions.section.TimetableList
import io.github.droidkaigi.confsched.sessions.section.TimetableUiState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TimetableScreen(
    uiState: TimetableScreenUiState,
    onSearchClick: () -> Unit,
    onTimetableItemClick: (TimetableItemId) -> Unit,
    onBookmarkClick: (sessionId: String, isBookmarked: Boolean) -> Unit,
    onTimetableUiChangeClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TimetableTopAppBar(
                timetableUiType = uiState.uiType,
                onSearchClick = onSearchClick,
                onUiTypeChangeClick = onTimetableUiChangeClick,
            )
        },
        modifier = modifier.fillMaxSize(),
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            PrimaryTabRow(
                selectedTabIndex = 0,
            ) {
                Tab(
                    onClick = {},
                    selected = true,
                ) {
                    Text("Hoge")
                }
            }
            when (uiState.timetable) {
                is TimetableUiState.Empty -> Text("Empty")
                is TimetableUiState.GridTimetable -> Text("Grid")
                is TimetableUiState.ListTimetable -> TimetableList(uiState.timetable)
            }
        }
    }
}

@Preview
@Composable
private fun TimetableScreenPreview() {
    TimetableScreen(
        uiState = TimetableScreenUiState(
            timetable = TimetableUiState.Empty,
            uiType = TimetableUiType.List,
        ),
        onBookmarkClick = { _, _ -> },
        onSearchClick = {},
        onTimetableItemClick = {},
        onTimetableUiChangeClick = {},
    )
}
