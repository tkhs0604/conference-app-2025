package io.github.droidkaigi.confsched.droidkaigiui

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import io.github.droidkaigi.confsched.context.ScreenContext
import io.github.droidkaigi.confsched.designsystem.theme.KaigiTheme
import io.github.droidkaigi.confsched.droidkaigiui.architecture.SoilErrorContext
import io.github.droidkaigi.confsched.droidkaigiui.architecture.SoilSuspenseContext
import soil.plant.compose.reacty.ErrorBoundaryContext

@OptIn(ExperimentalCoilApi::class)
@Composable
fun KaigiPreviewContainer(
    modifier: Modifier = Modifier,
    content: @Composable context(PreviewContext) () -> Unit,
) {
    KaigiTheme {
        Surface(modifier = modifier) {
            val previewHandler = AsyncImagePreviewHandler {
                ColorImage(Color.Red.toArgb())
            }

            CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
                with(FakePreviewContext()) {
                    content()
                }
            }
        }
    }
}

interface PreviewContext : ScreenContext, SoilSuspenseContext, SoilErrorContext

private class FakePreviewContext : PreviewContext {
    override val errorBoundaryContext: ErrorBoundaryContext = ErrorBoundaryContext(
        err = Throwable("Fake error for preview"),
        reset = null,
    )
}
