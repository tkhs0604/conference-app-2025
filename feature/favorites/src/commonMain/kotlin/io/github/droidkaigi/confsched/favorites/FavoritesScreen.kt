package io.github.droidkaigi.confsched.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.droidkaigi.confsched.favorites.components.FavoriteFilters
import io.github.droidkaigi.confsched.model.core.DroidKaigi2025Day
import io.github.droidkaigi.confsched.model.sessions.TimetableItem
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import io.github.droidkaigi.confsched.model.sessions.fake
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.persistentMapOf
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    uiState: FavoritesScreenUiState,
    modifier: Modifier = Modifier,
    onBookmarkClick: (TimetableItemId) -> Unit,
    onAllFilterChipClick: () -> Unit,
    onDay1FilterChipClick: () -> Unit,
    onDay2FilterChipClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(FavoritesRes.string.favorite))
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            FavoriteFilters(
                allFilterSelected = uiState.filterState.allFilterSelected,
                day1FilterSelected = uiState.filterState.isDay1FilterSelected,
                day2FilterSelected = uiState.filterState.isDay2FilterSelected,
                onAllFilterChipClick = onAllFilterChipClick,
                onDay1FilterChipClick = onDay1FilterChipClick,
                onDay2FilterChipClick = onDay2FilterChipClick,
                modifier = Modifier.fillMaxWidth()
            )
            when (uiState.timetableContentState) {
                FavoritesScreenUiState.TimetableContentState.Empty -> Text("Empty Favorites")
                is FavoritesScreenUiState.TimetableContentState.FavoriteList -> {
                    LazyColumn() {
                        itemsIndexed(
                            items = uiState.timetableContentState.timetableItemMap.toList(),
                            key = { _, item -> item.first.key }
                        ) { index, (timeSlot, timetableItems) ->
                            Text(
                                text = "${timeSlot.startTimeString} - ${timeSlot.endTimeString}",
                            )
                            timetableItems.forEach { timetableItem ->
                                Text(timetableItem.title.jaTitle)
                                IconButton(
                                    onClick = { onBookmarkClick(timetableItem.id) },
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Favorite,
                                        contentDescription = null
                                    )
                                }
                            }
                            if (index < uiState.timetableContentState.timetableItemMap.size - 1) {
                                HorizontalDivider()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun FavoritesScreenPreview() {
    FavoritesScreen(
        uiState = FavoritesScreenUiState(
            filterState = FavoritesScreenUiState.FilterState(
                allFilterSelected = false,
                currentDayFilter = persistentListOf(DroidKaigi2025Day.ConferenceDay1, DroidKaigi2025Day.ConferenceDay2)
            ),
            timetableContentState = FavoritesScreenUiState.TimetableContentState.FavoriteList(
                timetableItemMap = persistentMapOf(
                    FavoritesScreenUiState.TimetableContentState.FavoriteList.TimeSlot(
                        day = DroidKaigi2025Day.ConferenceDay1,
                        startTimeString = "10:00",
                        endTimeString = "11:00",
                    ) to listOf(
                        TimetableItem.Session.fake(),
                    )
                )
            )
        ),
        onBookmarkClick = {},
        onAllFilterChipClick = {},
        onDay1FilterChipClick = {},
        onDay2FilterChipClick = {},
    )
}
