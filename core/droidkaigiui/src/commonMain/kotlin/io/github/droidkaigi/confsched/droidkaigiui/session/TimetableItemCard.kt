package io.github.droidkaigi.confsched.droidkaigiui.session

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import io.github.droidkaigi.confsched.designsystem.theme.KaigiTheme
import io.github.droidkaigi.confsched.designsystem.theme.LocalRoomTheme
import io.github.droidkaigi.confsched.designsystem.theme.ProvideRoomTheme
import io.github.droidkaigi.confsched.droidkaigiui.DroidkaigiuiRes
import io.github.droidkaigi.confsched.droidkaigiui.bookmarked
import io.github.droidkaigi.confsched.droidkaigiui.not_bookmarked
import io.github.droidkaigi.confsched.model.sessions.TimetableItem
import io.github.droidkaigi.confsched.model.sessions.TimetableSpeaker
import io.github.droidkaigi.confsched.model.sessions.fake
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TimetableItemCard(
    timetableItem: TimetableItem,
    isBookmarked: Boolean,
    highlightWord: String,
    onBookmarkClick: (TimetableItem, Boolean) -> Unit,
    onTimetableItemClick: (TimetableItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    val haptic = LocalHapticFeedback.current

    ProvideRoomTheme(timetableItem.room.roomTheme) {
        Surface(onClick = { onTimetableItemClick(timetableItem) }) {
            // Magic number to top-align TimetableItemTagRow with FavoriteButton.
            val rippleTopPadding = 7.dp
            val contentPadding = 16.dp
            Row(
                verticalAlignment = Alignment.Top,
                modifier = modifier
                    .background(Color.Transparent)
                    .fillMaxWidth()
                    .border(1.dp, MaterialTheme.colorScheme.outlineVariant, RoundedCornerShape(16.dp))
                    .padding(top = (contentPadding - rippleTopPadding), bottom = contentPadding, start = contentPadding)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = rippleTopPadding)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        TimetableItemRoomTag(
                            icon = timetableItem.room.icon,
                            text = timetableItem.room.name.currentLangTitle,
                            color = LocalRoomTheme.current.primaryColor,
                            modifier = Modifier.background(LocalRoomTheme.current.containerColor),
                        )
                        timetableItem.language.labels.forEach { label ->
                            TimetableItemLangTag(label)
                        }
                    }
                    TimetableItemTitle(timetableItem.title.currentLangTitle, highlightWord)
                    timetableItem.speakers.forEach { speaker ->
                        TimetableItemSpeaker(
                            speaker = speaker,
                            modifier = Modifier
                                .height(36.dp)
                                .padding(top = 4.dp)
                        )
                    }
                }

                // remove top padding
                CompositionLocalProvider(LocalMinimumInteractiveComponentSize provides 0.dp) {
                    FavoriteButton(
                        isBookmarked = isBookmarked,
                        onClick = {
                            if (!isBookmarked) {
                                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            }
                            onBookmarkClick(timetableItem, true)
                        },
                    )
                }
            }
        }
    }
}


@Composable
private fun TimetableItemTitle(
    title: String,
    highlightWord: String,
) {
    val highlightBackgroundColor = MaterialTheme.colorScheme.surfaceTint.copy(alpha = 0.14f)
    val highlightedTitle = remember(
        title,
        highlightBackgroundColor,
        highlightWord,
    ) {
        buildAnnotatedString {
            append(title)

            if (highlightWord.isEmpty()) return@buildAnnotatedString

            val highlightRanges = mutableListOf<IntRange>()
            var startIndex = 0
            while (true) {
                startIndex = title.indexOf(
                    string = highlightWord,
                    startIndex = startIndex,
                    ignoreCase = true,
                )
                if (startIndex == -1) {
                    break
                } else {
                    highlightRanges += IntRange(
                        startIndex,
                        startIndex + highlightWord.length,
                    )
                    startIndex += highlightWord.length
                }
            }

            val highlightStyle = SpanStyle(
                textDecoration = TextDecoration.Underline,
                background = highlightBackgroundColor,
            )

            highlightRanges.forEach { range ->
                addStyle(
                    style = highlightStyle,
                    start = range.first,
                    end = range.last,
                )
            }
        }
    }

    Text(highlightedTitle)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FavoriteButton(
    isBookmarked: Boolean,
    onClick: () -> Unit,
) {
    TextButton(onClick) {
        if (isBookmarked) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = stringResource(DroidkaigiuiRes.string.bookmarked),
                tint = MaterialTheme.colorScheme.primaryFixed,
            )
        } else {
            Icon(
                Icons.Outlined.FavoriteBorder,
                contentDescription = stringResource(DroidkaigiuiRes.string.not_bookmarked),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}


@Composable
fun TimetableItemSpeaker(
    speaker: TimetableSpeaker,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        // TODO: Replace AsyncImage
        Box(
            //  painter = painter,
            //  contentDescription = null,
            modifier = Modifier
                .width(32.dp)
                .height(32.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = CircleShape,
                ),
        )
        Text(
            text = speaker.name,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .align(Alignment.CenterVertically),
        )
    }
}

@Preview
@Composable
fun TimetableItemCardPreview() {
    KaigiTheme {
        Surface {
            TimetableItemCard(
                timetableItem = TimetableItem.Session.fake(),
                isBookmarked = false,
                highlightWord = "",
                onBookmarkClick = { item, isBookmarked -> },
                onTimetableItemClick = {},
            )
        }
    }
}

@Preview
@Composable
fun TimetableItemTitlePreview() {
    KaigiTheme {
        Surface {
            TimetableItemTitle(
                title = "This is a sample title",
                highlightWord = "sample",
            )
        }
    }
}

@Preview
@Composable
fun TimetableItemTitlePreview_NoHighlight() {
    KaigiTheme {
        Surface {
            TimetableItemTitle(
                title = "This is a sample title",
                highlightWord = "example",
            )
        }
    }
}
