package io.github.droidkaigi.confsched.sessions.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TimetableItemDetailBottomAppBar(
    isBookmarked: Boolean,
    onBookmarkClick: (isBookmarked: Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    BottomAppBar(
        modifier = modifier,
        actions = {},
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onBookmarkClick(!isBookmarked) },
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
            ) {
                Icon(
                    imageVector = if (isBookmarked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = null
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
    )
}

@Preview
@Composable
fun TimetableItemDetailBottomAppBarPreview() {
    TimetableItemDetailBottomAppBar(
        isBookmarked = false,
        onBookmarkClick = {},
    )
}
