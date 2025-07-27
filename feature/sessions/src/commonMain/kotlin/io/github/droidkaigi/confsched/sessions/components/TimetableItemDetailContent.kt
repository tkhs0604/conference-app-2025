package io.github.droidkaigi.confsched.sessions.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import io.github.droidkaigi.confsched.designsystem.component.ClickableLinkText
import io.github.droidkaigi.confsched.designsystem.theme.LocalRoomTheme
import io.github.droidkaigi.confsched.designsystem.theme.ProvideRoomTheme
import io.github.droidkaigi.confsched.droidkaigiui.KaigiPreviewContainer
import io.github.droidkaigi.confsched.droidkaigiui.session.roomTheme
import io.github.droidkaigi.confsched.model.core.Lang
import io.github.droidkaigi.confsched.model.sessions.TimetableAsset
import io.github.droidkaigi.confsched.model.sessions.TimetableItem
import io.github.droidkaigi.confsched.model.sessions.fake
import io.github.droidkaigi.confsched.sessions.SessionsRes
import io.github.droidkaigi.confsched.sessions.archive
import io.github.droidkaigi.confsched.sessions.read_more
import io.github.droidkaigi.confsched.sessions.slide
import io.github.droidkaigi.confsched.sessions.target_audience
import io.github.droidkaigi.confsched.sessions.video
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TimetableItemDetailContent(
    timetableItem: TimetableItem,
    currentLang: Lang,
    modifier: Modifier = Modifier,
    onLinkClick: (url: String) -> Unit,
) {
    Column(modifier = modifier) {
        DescriptionSection(
            description = when (timetableItem) {
                is TimetableItem.Session -> timetableItem.description.getByLang(currentLang)
                is TimetableItem.Special -> timetableItem.description.currentLangTitle
            },
            onLinkClick = onLinkClick,
        )
        TargetAudienceSection(targetAudience = timetableItem.targetAudience)
        if (timetableItem.asset.isAvailable) {
            ArchiveSection(
                timetableAsset = timetableItem.asset,
                onViewSlideClick = onLinkClick,
                onWatchVideoClick = onLinkClick,
            )
        }
    }
}

@Composable
private fun DescriptionSection(
    description: String,
    onLinkClick: (url: String) -> Unit,
) {
    var isExpand by rememberSaveable { mutableStateOf(false) }
    var isOverFlow by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(8.dp)) {
        SelectionContainer {
            ClickableLinkText(
                content = description,
                regex = "(https)(://[\\w/:%#$&?()~.=+\\-]+)".toRegex(),
                onLinkClick = onLinkClick,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = if (isExpand) Int.MAX_VALUE else 7,
                overflow = if (isExpand) TextOverflow.Clip else TextOverflow.Ellipsis,
                onOverflow = {
                    isOverFlow = it
                },
            )
        }
        Spacer(Modifier.height(16.dp))
        AnimatedVisibility(
            visible = isExpand.not() && isOverFlow,
            enter = EnterTransition.None,
            exit = fadeOut(),
        ) {
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LocalRoomTheme.current.dimColor
                ),
                border = null,
                onClick = { isExpand = true },
            ) {
                Text(
                    text = stringResource(SessionsRes.string.read_more),
                    style = MaterialTheme.typography.labelLarge,
                    color = LocalRoomTheme.current.primaryColor,
                )
            }
        }
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
private fun TargetAudienceSection(
    targetAudience: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(SessionsRes.string.target_audience),
            style = MaterialTheme.typography.titleLarge,
            color = LocalRoomTheme.current.primaryColor,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = targetAudience,
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
private fun ArchiveSection(
    timetableAsset: TimetableAsset,
    modifier: Modifier = Modifier,
    onViewSlideClick: (url: String) -> Unit,
    onWatchVideoClick: (url: String) -> Unit,
) {
    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(SessionsRes.string.archive),
            style = MaterialTheme.typography.titleLarge,
            color = LocalRoomTheme.current.primaryColor,
        )
        Spacer(Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            timetableAsset.slideUrl?.let { slideUrl ->
                Button(
                    modifier = Modifier
                        .weight(1f),
                    onClick = { onViewSlideClick(slideUrl) },
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = LocalRoomTheme.current.primaryColor,
                    ),
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Description,
                        contentDescription = stringResource(SessionsRes.string.slide),
                    )
                    Text(
                        text = stringResource(SessionsRes.string.slide),
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }
            timetableAsset.videoUrl?.let { videoUrl ->
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    modifier = Modifier
                        .weight(1f),
                    onClick = { onWatchVideoClick(videoUrl) },
                    colors = ButtonDefaults.buttonColors().copy(
                        containerColor = LocalRoomTheme.current.primaryColor,
                    ),
                ) {
                    Icon(
                        imageVector = Icons.Outlined.PlayCircle,
                        contentDescription = stringResource(SessionsRes.string.video),
                    )
                    Text(
                        text = stringResource(SessionsRes.string.video),
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun TimetableItemDetailContentPreview() {
    val session = TimetableItem.Session.fake()
    KaigiPreviewContainer {
        ProvideRoomTheme(session.room.roomTheme) {
            TimetableItemDetailContent(
                timetableItem = session,
                currentLang = Lang.JAPANESE,
                onLinkClick = {},
            )
        }
    }
}

@Composable
@Preview
fun TimetableItemDetailContentWithEnglishPreview() {
    val session = TimetableItem.Session.fake()
    KaigiPreviewContainer {
        ProvideRoomTheme(session.room.roomTheme) {
            TimetableItemDetailContent(
                timetableItem = session,
                currentLang = Lang.ENGLISH,
                onLinkClick = {},
            )
        }
    }
}

@Composable
@Preview
fun TimetableItemDetailContentWithMixedPreview() {
    val session = TimetableItem.Session.fake()
    KaigiPreviewContainer {
        ProvideRoomTheme(session.room.roomTheme) {
            TimetableItemDetailContent(
                timetableItem = session,
                currentLang = Lang.MIXED,
                onLinkClick = {},
            )
        }
    }
}
