package io.github.droidkaigi.confsched.droidkaigiui

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.SceneStrategy

@Composable
expect fun platformEntryDecorators(): List<NavEntryDecorator<*>>

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
            // FIXME: Seems like this is not working as expected.
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

@Composable
private fun rememberSlideDistance(): Int {
    val slideDistance: Dp = 30.dp
    val density = LocalDensity.current
    return remember(density, slideDistance) {
        with(density) { slideDistance.roundToPx() }
    }
}

private fun materialSharedAxisXIn(
    forward: Boolean,
    slideDistance: Int,
): EnterTransition = slideInHorizontally(
    animationSpec = tween(
        durationMillis = 300,
        easing = FastOutSlowInEasing,
    ),
    initialOffsetX = {
        if (forward) slideDistance else -slideDistance
    },
) + fadeIn(
    animationSpec = tween(
        durationMillis = 195,
        delayMillis = 105,
        easing = LinearOutSlowInEasing,
    ),
)

private fun materialSharedAxisXOut(
    forward: Boolean,
    slideDistance: Int,
): ExitTransition = slideOutHorizontally(
    animationSpec = tween(
        durationMillis = 300,
        easing = FastOutSlowInEasing,
    ),
    targetOffsetX = {
        if (forward) -slideDistance else slideDistance
    },
) + fadeOut(
    animationSpec = tween(
        durationMillis = 105,
        delayMillis = 0,
        easing = FastOutLinearInEasing,
    ),
)
