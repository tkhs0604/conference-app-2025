package io.github.droidkaigi.confsched.droidkaigiui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastMaxOfOrDefault
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.roundToInt

private enum class CollapsingHeaderLayoutSlot {
    CollapsingContent,
    Content,
}

sealed class CollapsingHeaderState {
    abstract val nestedScrollConnection: NestedScrollConnection
    var collapsingOffsetY: Float by mutableFloatStateOf(0f)
        protected set
    var headerContentHeightPx by mutableFloatStateOf(0f)
        internal set

    class EnterAlways internal constructor() : CollapsingHeaderState() {

        override val nestedScrollConnection: NestedScrollConnection = object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y

                val newOffset = (collapsingOffsetY + delta).coerceIn(
                    minimumValue = -headerContentHeightPx,
                    maximumValue = 0f,
                )

                val consumed = newOffset - collapsingOffsetY
                collapsingOffsetY = newOffset

                return Offset(0f, consumed)
            }
        }
    }
}

@Composable
fun rememberCollapsingHeaderEnterAlwaysState(): CollapsingHeaderState.EnterAlways {
    return remember { CollapsingHeaderState.EnterAlways() }
}

@Composable
fun CollapsingHeaderLayout(
    state: CollapsingHeaderState,
    headerContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable (collapsingHeaderContentPadding: PaddingValues) -> Unit,
) {
    SubcomposeLayout(
        modifier = modifier.nestedScroll(state.nestedScrollConnection),
    ) { constraints ->
        val headerContentMeasurables = subcompose(CollapsingHeaderLayoutSlot.CollapsingContent) {
            headerContent()
        }

        val headerContentPlaceables = headerContentMeasurables.map { it.measure(constraints) }

        val headerContentHeightPx = headerContentPlaceables.fastMaxOfOrDefault(0) { it.measuredHeight }
        state.headerContentHeightPx = headerContentHeightPx.toFloat()

        val collapsingHeaderContentPadding = PaddingValues(top = (headerContentHeightPx + state.collapsingOffsetY).coerceAtLeast(0f).toDp())

        val contentMeasurables = subcompose(CollapsingHeaderLayoutSlot.Content) {
            content(collapsingHeaderContentPadding)
        }

        val contentPlaceables = contentMeasurables.map { it.measure(constraints) }

        layout(
            constraints.maxWidth,
            constraints.maxHeight,
        ) {
            headerContentPlaceables.forEach { placeable ->
                placeable.place(0, state.collapsingOffsetY.roundToInt())
            }

            contentPlaceables.forEach { placeable ->
                placeable.place(0, 0)
            }
        }
    }
}

@Preview
@Composable
private fun CollapsingHeaderLayoutPreview() {
    CollapsingHeaderLayout(
        state = rememberCollapsingHeaderEnterAlwaysState(),
        headerContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color.Blue),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "Collapsing Header")
            }
        },
    ) { collapsingHeaderContentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(collapsingHeaderContentPadding),
        ) {
            items(100) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .background(Color.Green),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(text = "Item $index")
                }
            }
        }
    }
}
