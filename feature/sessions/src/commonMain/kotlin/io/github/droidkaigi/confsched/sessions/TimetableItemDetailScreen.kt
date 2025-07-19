package io.github.droidkaigi.confsched.sessions

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.droidkaigi.confsched.model.core.Lang
import io.github.droidkaigi.confsched.model.sessions.TimetableItem
import io.github.droidkaigi.confsched.model.sessions.fake
import io.github.droidkaigi.confsched.sessions.components.TimetableItemDetailFloatingActionButtonMenu
import io.github.droidkaigi.confsched.sessions.components.TimetableItemDetailHeadline
import io.github.droidkaigi.confsched.sessions.components.TimetableItemDetailTopAppBar
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TimetableItemDetailScreen(
    uiState: TimetableItemDetailScreenUiState,
    onBackClick: () -> Unit,
    onBookmarkClick: (isBookmarked: Boolean) -> Unit,
    onAddCalendarClick: (TimetableItem) -> Unit,
    onShareClick: (TimetableItem) -> Unit,
    onLanguageSelect: (Lang) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TimetableItemDetailTopAppBar(
                onBackClick = onBackClick,
            )
        },
        floatingActionButton = {
            TimetableItemDetailFloatingActionButtonMenu(
                isBookmarked = uiState.isBookmarked,
                onBookmarkClick = onBookmarkClick,
                onAddCalendarClick = { onAddCalendarClick(uiState.timetableItem) },
                onShareClick = { onShareClick(uiState.timetableItem) }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            item {
                TimetableItemDetailHeadline(
                    currentLang = uiState.currentLang,
                    timetableItem = uiState.timetableItem,
                    isLangSelectable = uiState.isLangSelectable,
                    onLanguageSelect = onLanguageSelect,
                )
            }
        }
    }
}

@Preview
@Composable
private fun TimetableItemDetailScreenPreview() {
    TimetableItemDetailScreen(
        uiState = TimetableItemDetailScreenUiState(
            timetableItem = TimetableItem.Session.fake(),
            isBookmarked = false,
            currentLang = Lang.JAPANESE,
            isLangSelectable = true,
        ),
        onBackClick = {},
        onBookmarkClick = {},
        onAddCalendarClick = {},
        onShareClick = {},
        onLanguageSelect = {},
    )
}
