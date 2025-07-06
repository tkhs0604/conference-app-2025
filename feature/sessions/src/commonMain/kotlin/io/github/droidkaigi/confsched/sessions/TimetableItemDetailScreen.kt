package io.github.droidkaigi.confsched.sessions

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TimetableItemDetailScreen(
    uiState: TimetableItemDetailScreenUiState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {},
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues)
        ) {
            Text("detail")
        }
    }
}
