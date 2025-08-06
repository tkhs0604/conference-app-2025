package io.github.droidkaigi.confsched.droidkaigiui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import io.github.droidkaigi.confsched.context.ScreenContext
import io.github.droidkaigi.confsched.droidkaigiui.component.DefaultErrorFallBack
import soil.plant.compose.reacty.Await
import soil.plant.compose.reacty.ErrorBoundary
import soil.plant.compose.reacty.ErrorBoundaryContext
import soil.plant.compose.reacty.Suspense
import soil.query.core.DataModel

context(_: ScreenContext)
@Composable
fun <T> SoilDataBoundary(
    state: DataModel<T>,
    modifier: Modifier = Modifier,
    errorFallback: @Composable BoxScope.(ErrorBoundaryContext) -> Unit = {
        DefaultErrorFallBack(errorBoundaryContext = it)
    },
    suspenseFallback: @Composable BoxScope.() -> Unit = DefaultSuspenseFallback,
    content: @Composable (T) -> Unit,
) {
    ErrorBoundary(
        fallback = errorFallback,
        modifier = modifier,
    ) {
        Suspense(fallback = suspenseFallback) {
            Await(state = state, content = content)
        }
    }
}

context(_: ScreenContext)
@Composable
fun <T1, T2> SoilDataBoundary(
    state1: DataModel<T1>,
    state2: DataModel<T2>,
    modifier: Modifier = Modifier,
    errorFallback: @Composable BoxScope.(ErrorBoundaryContext) -> Unit = {
        DefaultErrorFallBack(errorBoundaryContext = it)
    },
    suspenseFallback: @Composable BoxScope.() -> Unit = DefaultSuspenseFallback,
    content: @Composable (T1, T2) -> Unit,
) {
    ErrorBoundary(
        fallback = errorFallback,
        modifier = modifier,
    ) {
        Suspense(fallback = suspenseFallback) {
            Await(
                state1 = state1,
                state2 = state2,
                content = content
            )
        }
    }
}

context(_: ScreenContext)
@Composable
fun <T1, T2, T3> SoilDataBoundary(
    state1: DataModel<T1>,
    state2: DataModel<T2>,
    state3: DataModel<T3>,
    modifier: Modifier = Modifier,
    errorFallback: @Composable BoxScope.(ErrorBoundaryContext) -> Unit = {
        DefaultErrorFallBack(errorBoundaryContext = it)
    },
    suspenseFallback: @Composable BoxScope.() -> Unit = DefaultSuspenseFallback,
    content: @Composable (T1, T2, T3) -> Unit,
) {
    ErrorBoundary(
        fallback = errorFallback,
        modifier = modifier,
    ) {
        Suspense(fallback = suspenseFallback) {
            Await(
                state1 = state1,
                state2 = state2,
                state3 = state3,
                content = content
            )
        }
    }
}

const val DefaultSuspenseFallbackTestTag = "DefaultSuspenseFallbackTestTag"

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
private val DefaultSuspenseFallback: @Composable BoxScope.() -> Unit = {
    Box(
        modifier = Modifier
            .testTag(DefaultSuspenseFallbackTestTag)
            .safeDrawingPadding()
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularWavyProgressIndicator()
    }
}
