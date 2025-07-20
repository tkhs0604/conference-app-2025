package io.github.droidkaigi.confsched.sessions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.droidkaigi.confsched.droidkaigiui.KaigiPreviewContainer
import io.github.droidkaigi.confsched.model.sessions.TimetableUiType
import io.github.droidkaigi.confsched.sessions.components.TimetableTopAppBar
import org.jetbrains.compose.ui.tooling.preview.Preview
import soil.plant.compose.reacty.ErrorBoundaryContext

@Composable
fun TimetableScreenErrorFallback(
    errorBoundaryContext: ErrorBoundaryContext,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TimetableTopAppBar(
                timetableUiType = TimetableUiType.List,
                onSearchClick = onSearchClick,
                onUiTypeChangeClick = { /* Noop */ },
            )
        },
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        ) {
            Text(
                text = errorBoundaryContext.err.message ?: "An error occurred",
            )
            Button(onClick = { errorBoundaryContext.reset?.invoke() }) {
                Text(text = "Retry")
            }
        }
    }
}

@Preview
@Composable
private fun TimetableScreenErrorFallbackPreview() {
    KaigiPreviewContainer {
        TimetableScreenErrorFallback(
            errorBoundaryContext = ErrorBoundaryContext(Throwable("Error"), {}),
            onSearchClick = {},
            modifier = Modifier,
        )
    }
}
