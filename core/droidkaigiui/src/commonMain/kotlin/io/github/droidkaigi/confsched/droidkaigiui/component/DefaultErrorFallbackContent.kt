package io.github.droidkaigi.confsched.droidkaigiui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import io.github.droidkaigi.confsched.context.ScreenContext
import io.github.droidkaigi.confsched.droidkaigiui.DroidkaigiuiRes
import io.github.droidkaigi.confsched.droidkaigiui.KaigiPreviewContainer
import io.github.droidkaigi.confsched.droidkaigiui.error_mascot
import io.github.droidkaigi.confsched.droidkaigiui.error_occurred
import io.github.droidkaigi.confsched.droidkaigiui.retry
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import soil.plant.compose.reacty.ErrorBoundaryContext

const val DefaultErrorFallbackContentRetryTestTag = "DefaultErrorFallbackContentRetry"

context(_: ScreenContext)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultErrorFallbackContent(
    errorBoundaryContext: ErrorBoundaryContext,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(35.dp, Alignment.CenterVertically),
    ) {
        Image(
            painter = painterResource(DroidkaigiuiRes.drawable.error_mascot),
            contentDescription = null,
        )
        Text(
            text = stringResource(DroidkaigiuiRes.string.error_occurred),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Button(
            onClick = { errorBoundaryContext.reset?.invoke() },
            modifier = Modifier.testTag(DefaultErrorFallbackContentRetryTestTag),
        ) {
            Text(
                text = stringResource(DroidkaigiuiRes.string.retry),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Preview
@Composable
private fun DefaultErrorFallbackContentPreview() {
    KaigiPreviewContainer {
        DefaultErrorFallbackContent(
            errorBoundaryContext = ErrorBoundaryContext(
                err = Throwable("An error occurred"),
                reset = {},
            ),
        )
    }
}
