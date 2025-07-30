package io.github.droidkaigi.confsched.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import io.github.droidkaigi.confsched.droidkaigiui.navigation.NavDisplayWithSharedAxisX
import io.github.droidkaigi.confsched.droidkaigiui.navigation.sceneStrategy

@Composable
fun MainScreenRoot(
    currentTab: MainScreenTab?,
    backStack: List<NavKey>,
    onBack: (Int) -> Unit,
    onTabSelected: (MainScreenTab) -> Unit,
    entryProvider: (key: NavKey) -> NavEntry<NavKey>,
) {
    val hazeState = rememberHazeState()

    @Suppress("UnusedMaterial3ScaffoldPaddingParameter")
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
        }
    ) { _ ->
        NavDisplayWithSharedAxisX(
            backStack = backStack,
            onBack = onBack,
            sceneStrategy = sceneStrategy(),
            entryProvider = entryProvider,
            modifier = Modifier.hazeSource(hazeState)
        )
    }
}
