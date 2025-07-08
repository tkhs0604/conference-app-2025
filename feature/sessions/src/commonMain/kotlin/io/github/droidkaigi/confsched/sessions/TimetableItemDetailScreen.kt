package io.github.droidkaigi.confsched.sessions

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.droidkaigi.confsched.model.core.Lang
import io.github.droidkaigi.confsched.sessions.components.TimetableItemDetailTopAppBar
import org.jetbrains.compose.resources.stringResource

@Composable
fun TimetableItemDetailScreen(
    uiState: TimetableItemDetailScreenUiState,
    onBackClick: () -> Unit,
    onBookmarkClick: (isBookmarked: Boolean) -> Unit,
    onLanguageSelect: (Lang) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TimetableItemDetailTopAppBar(
                onBackClick = onBackClick,
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            LanguageSwitcher(
                currentLang = uiState.currentLang,
                onLanguageSelect = onLanguageSelect
            )

            Text(
                text = uiState.timetableItem.title.getByLang(uiState.currentLang),
                style = MaterialTheme.typography.headlineSmall,
            )

            TextButton(
                onClick = {
                    onBookmarkClick(!uiState.isBookmarked)
                }
            ) { Text("Bookmark ${uiState.isBookmarked}") }
        }
    }
}

@Composable
private fun LanguageSwitcher(
    currentLang: Lang,
    onLanguageSelect: (Lang) -> Unit,
    modifier: Modifier = Modifier,
) {
    val normalizedCurrentLang = if (currentLang == Lang.MIXED) {
        Lang.ENGLISH
    } else {
        currentLang
    }
    val availableLangs = Lang.entries.filterNot { it == Lang.MIXED }
    val lastIndex = availableLangs.size - 1

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        availableLangs.forEachIndexed { index, lang ->
            val isSelected = normalizedCurrentLang == lang
            TextButton(
                onClick = { onLanguageSelect(lang) },
                contentPadding = PaddingValues(12.dp),
            ) {
                AnimatedVisibility(isSelected) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .size(12.dp),
                    )
                }
                Text(
                    text = stringResource(
                        when (lang) {
                            Lang.JAPANESE -> SessionsRes.string.japanese
                            Lang.ENGLISH,
                            Lang.MIXED -> SessionsRes.string.english
                        }
                    ),
                    style = MaterialTheme.typography.labelMedium,
                )
            }
            if (index < lastIndex) {
                VerticalDivider(
                    modifier = Modifier.height(11.dp),
                    color = MaterialTheme.colorScheme.outlineVariant,
                )
            }
        }
    }
}
