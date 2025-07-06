package io.github.droidkaigi.confsched.sessions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TimetableItemDetailScreen(
    uiState: TimetableItemDetailScreenUiState,
    onBookmarkClick: (isBookmarked: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {},
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Text(uiState.timetableItem.title.currentLangTitle)

            TextButton(
                onClick = {
                    onBookmarkClick(!uiState.isBookmarked)
                }
            ) { Text("Bookmark ${uiState.isBookmarked}") }
        }
    }
}
