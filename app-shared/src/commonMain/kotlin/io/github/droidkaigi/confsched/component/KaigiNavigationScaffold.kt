package io.github.droidkaigi.confsched.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.rememberHazeState
import io.github.droidkaigi.confsched.droidkaigiui.KaigiPreviewContainer
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun KaigiNavigationScaffold(
    currentTab: MainScreenTab?,
    hazeState: HazeState,
    onTabSelected: (MainScreenTab) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        bottomBar = {
            AnimatedVisibility(currentTab != null) {
                GlassLikeBottomNavigationBar(
                    currentTab = currentTab ?: MainScreenTab.Timetable,
                    hazeState = hazeState,
                    onTabSelected = onTabSelected,
                    modifier = Modifier
                        .padding(
                            start = 55.dp,
                            end = 55.dp,
                            top = 16.dp,
                            bottom = 32.dp,
                        )
                        .safeDrawingPadding()
                )
            }
        },
        content = content,
        modifier = modifier,
    )
}

@Preview
@Composable
private fun KaigiNavigationScaffoldPreview() {
    KaigiPreviewContainer {
        KaigiNavigationScaffold(
            currentTab = MainScreenTab.Timetable,
            onTabSelected = {},
            hazeState = rememberHazeState(),
        ) {
            Text("Content goes here")
        }
    }
}
