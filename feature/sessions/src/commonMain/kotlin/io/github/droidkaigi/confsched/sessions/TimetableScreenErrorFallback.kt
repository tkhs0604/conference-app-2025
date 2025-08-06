package io.github.droidkaigi.confsched.sessions

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.droidkaigi.confsched.context.ScreenContext
import io.github.droidkaigi.confsched.droidkaigiui.KaigiPreviewContainer
import io.github.droidkaigi.confsched.droidkaigiui.component.DefaultErrorFallBackContent
import io.github.droidkaigi.confsched.model.sessions.TimetableUiType
import io.github.droidkaigi.confsched.sessions.components.TimetableTopAppBar
import org.jetbrains.compose.ui.tooling.preview.Preview
import soil.plant.compose.reacty.ErrorBoundaryContext

context(_: ScreenContext)
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
        DefaultErrorFallBackContent(
            errorBoundaryContext = errorBoundaryContext,
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        )
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
