package io.github.droidkaigi.confsched.component

import androidx.compose.animation.ContentTransform
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.SceneStrategy
import io.github.droidkaigi.confsched.navigation.materialSharedAxisXIn
import io.github.droidkaigi.confsched.navigation.materialSharedAxisXOut
import io.github.droidkaigi.confsched.navigation.platformEntryDecorators
import io.github.droidkaigi.confsched.navigation.rememberSlideDistance

@Composable
fun <T : Any> NavDisplayWithSharedAxisX(
    backStack: List<T>,
    onBack: (Int) -> Unit,
    modifier: Modifier = Modifier,
    sceneStrategy: SceneStrategy<T>,
    contentAlignment: Alignment = Alignment.Center,
    entryProvider: (key: T) -> NavEntry<T>,
) {
    val slideDistance = rememberSlideDistance()

    NavDisplay(
        backStack = backStack,
        sceneStrategy = sceneStrategy,
        onBack = onBack,
        contentAlignment = contentAlignment,
        transitionSpec = {
            ContentTransform(
                targetContentEnter = materialSharedAxisXIn(forward = true, slideDistance),
                initialContentExit = materialSharedAxisXOut(forward = true, slideDistance),
            )
        },
        popTransitionSpec = {
            ContentTransform(
                targetContentEnter = materialSharedAxisXIn(forward = false, slideDistance),
                initialContentExit = materialSharedAxisXOut(forward = false, slideDistance),
            )
        },
        predictivePopTransitionSpec = {
            ContentTransform(
                targetContentEnter = materialSharedAxisXIn(forward = false, slideDistance),
                initialContentExit = materialSharedAxisXOut(forward = false, slideDistance),
            )
        },
        entryDecorators = platformEntryDecorators(),
        entryProvider = entryProvider,
        modifier = modifier,
    )
}
