package io.github.droidkaigi.confsched.favorites.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import io.github.droidkaigi.confsched.droidkaigiui.KaigiWindowSizeClassConstants
import io.github.droidkaigi.confsched.droidkaigiui.component.TimetableTimeSlot
import io.github.droidkaigi.confsched.droidkaigiui.session.TimetableItemCard
import io.github.droidkaigi.confsched.favorites.FavoritesScreenUiState.TimetableContentState.FavoriteList.TimeSlot
import io.github.droidkaigi.confsched.model.sessions.TimetableItem
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import kotlinx.collections.immutable.PersistentMap

@Composable
fun FavoriteTimetableList(
    modifier: Modifier = Modifier,
    timetableItemMap: PersistentMap<TimeSlot, List<TimetableItem>>,
    onTimetableItemClick: (TimetableItemId) -> Unit,
    onBookmarkClick: (TimetableItemId) -> Unit,
    lazyListState: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp),
    highlightWord: String = "",
) {
    BoxWithConstraints {
        val isWideScreen =
            maxWidth >= KaigiWindowSizeClassConstants.WindowWidthSizeClassMediumMinWidth
        val columnCount = if (isWideScreen) 2 else 1

        LazyColumn(
            state = lazyListState,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = contentPadding,
            modifier = modifier,
        ) {
            itemsIndexed(
                items = timetableItemMap.toList(),
                key = { _, (timeSlot, _) -> timeSlot.key },
            ) { index, (timeSlot, timetableItems) ->
                var timetableTimeSlotHeight by remember { mutableIntStateOf(0) }
                val timetableTimeSlotOffsetY by remember {
                    derivedStateOf {
                        val itemInfo =
                            lazyListState.layoutInfo.visibleItemsInfo.find { it.index == index }
                        // If the item is not visible, keep the TimetableTimeSlot in its original position.
                        if (itemInfo == null) return@derivedStateOf 0

                        val itemTopOffset = itemInfo.offset
                        // A positive offset means the top of the item is within the visible viewport.
                        if (itemTopOffset > 0) return@derivedStateOf 0

                        // Apply a vertical offset to TimetableTimeSlot to create a "sticky" effect while scrolling,
                        // but clamp it to ensure it doesn't overflow beyond the bottom edge of its item.
                        (-itemTopOffset).coerceAtMost(itemInfo.size - timetableTimeSlotHeight)
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    TimetableTimeSlot(
                        startTimeText = timeSlot.startTimeString,
                        endTimeText = timeSlot.endTimeString,
                        modifier = Modifier
                            .onSizeChanged { timetableTimeSlotHeight = it.height }
                            .graphicsLayer { translationY = timetableTimeSlotOffsetY.toFloat() }
                    )
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        timetableItems.windowed(columnCount, columnCount, true)
                            .forEach { windowedItems ->
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                                    modifier = Modifier.height(IntrinsicSize.Max),
                                ) {
                                    windowedItems.forEach { item ->
                                        TimetableItemCard(
                                            timetableItem = item,
                                            isBookmarked = true,
                                            highlightWord = highlightWord,
                                            onBookmarkClick = { _ , _ -> onBookmarkClick(item.id) },
                                            onTimetableItemClick = { onTimetableItemClick(item.id) },
                                            modifier = Modifier
                                                .weight(1f)
                                                .fillMaxHeight()
                                        )
                                    }
                                    if (windowedItems.size < columnCount) {
                                        repeat(columnCount - windowedItems.size) {
                                            Spacer(Modifier.weight(1f))
                                        }
                                    }
                                }
                            }
                    }
                }
            }
        }
    }
}
