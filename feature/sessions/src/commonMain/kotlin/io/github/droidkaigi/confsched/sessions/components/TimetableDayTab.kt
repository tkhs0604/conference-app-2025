package io.github.droidkaigi.confsched.sessions.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.droidkaigi.confsched.droidkaigiui.KaigiPreviewContainer
import io.github.droidkaigi.confsched.model.core.DroidKaigi2025Day
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TimetableDayTab(
    selectedDay: DroidKaigi2025Day,
    onDaySelected: (day: DroidKaigi2025Day) -> Unit,
    modifier: Modifier = Modifier,
) {
    // TODO Refactor
    val density = LocalDensity.current
    val tabWidth = with(density) {
        // calculate width from sp
        // NOTE: 6 is a magic number here
        ((16.sp * 6) * fontScale).toDp()
    }
    val selectedTabIndex = selectedDay.tabIndex()
    val selectedColor = Color(0xFF4AFF82)
    val indicatorWidths = remember {
        val stateList = mutableStateListOf<Dp>()
        stateList.addAll(DroidKaigi2025Day.visibleDays().map { 24.dp }) // default indicator width
        stateList
    }
    val paddingAroundTab =
        (tabWidth - indicatorWidths.reduce { acc, width -> acc + width }) / (indicatorWidths.size * 2)
    val columnHorizontalPadding = 0.dp.coerceAtLeast(20.dp - paddingAroundTab)

    Column(
        modifier = modifier.padding(horizontal = columnHorizontalPadding, vertical = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {
        PrimaryTabRow(
            modifier = Modifier.width(tabWidth),
            selectedTabIndex = selectedTabIndex,
            indicator = @Composable {
                TabRowDefaults.PrimaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(selectedTabIndex),
                    width = indicatorWidths[selectedTabIndex],
                    height = 2.dp,
                    color = selectedColor,
                    shape = RectangleShape,
                )
            },
            tabs = {
                DroidKaigi2025Day.visibleDays().forEachIndexed { index, conferenceDay ->
                    val isSelected = conferenceDay == selectedDay
                    Tab(
                        modifier = Modifier
//                            .testTag(TimetableTabTestTag.plus(conferenceDay.ordinal))
                            .requiredHeightIn(min = 26.dp)
                            .let { if (isSelected) it.padding(bottom = 2.dp) else it },
                        selected = isSelected,
                        onClick = {
                            onDaySelected(conferenceDay)
                        },
                        selectedContentColor = selectedColor,
                        unselectedContentColor = Color.White,
                    ) {
                        FloorText(
                            text = conferenceDay.monthAndDay(),
                            isSelected = isSelected,
                            onTextLayout = { indicatorWidths[index] = with(density) { it.size.width.toDp() } },
                        )
                    }
                }
            },
            divider = { /* Divider is not needed */ },
        )
    }
}

@Composable
private fun FloorText(
    text: String,
    isSelected: Boolean,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontSize = 16.sp,
        lineHeight = 23.8.sp,
        color = if (isSelected) {
            Color(0xFF4AFF82)
        } else {
            Color.White
        },
        onTextLayout = onTextLayout,
    )
}

@Preview
@Composable
private fun TimetableDayTabPreview() {
    KaigiPreviewContainer {
        Surface {
            TimetableDayTab(
                selectedDay = DroidKaigi2025Day.ConferenceDay1,
                onDaySelected = {},
            )
        }
    }
}
