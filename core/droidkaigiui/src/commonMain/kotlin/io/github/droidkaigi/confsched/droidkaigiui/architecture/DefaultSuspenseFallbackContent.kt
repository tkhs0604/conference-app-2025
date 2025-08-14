package io.github.droidkaigi.confsched.droidkaigiui.architecture

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import io.github.droidkaigi.confsched.droidkaigiui.KaigiPreviewContainer
import org.jetbrains.compose.ui.tooling.preview.Preview

const val DefaultSuspenseFallbackContentTestTag = "DefaultSuspenseFallbackContentTestTag"

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
context(_: SoilSuspenseContext)
fun DefaultSuspenseFallbackContent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .testTag(DefaultSuspenseFallbackContentTestTag),
        contentAlignment = Alignment.Center,
    ) {
        CircularWavyProgressIndicator()
    }
}

@Preview
@Composable
fun DefaultSuspenseFallbackContentPreview() {
    KaigiPreviewContainer {
        DefaultSuspenseFallbackContent()
    }
}
