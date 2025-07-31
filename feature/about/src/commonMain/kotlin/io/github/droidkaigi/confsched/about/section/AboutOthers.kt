package io.github.droidkaigi.confsched.about.section

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.outlined.FileCopy
import androidx.compose.material.icons.outlined.Gavel
import androidx.compose.material.icons.outlined.PrivacyTip
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.droidkaigi.confsched.about.AboutRes
import io.github.droidkaigi.confsched.about.code_of_conduct
import io.github.droidkaigi.confsched.about.components.AboutContentColumn
import io.github.droidkaigi.confsched.about.license
import io.github.droidkaigi.confsched.about.others_title
import io.github.droidkaigi.confsched.about.privacy_policy
import io.github.droidkaigi.confsched.about.settings
import io.github.droidkaigi.confsched.droidkaigiui.KaigiPreviewContainer
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

fun LazyListScope.aboutOthers(
    onCodeOfConductItemClick: () -> Unit,
    onLicenseItemClick: () -> Unit,
    onPrivacyPolicyItemClick: () -> Unit,
    onSettingsItemClick: () -> Unit,
) {
    item {
        Text(
            text = stringResource(AboutRes.string.others_title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 32.dp,
                    end = 16.dp,
                ),
        )
    }
    item {
        AboutContentColumn(
            leadingIcon = Outlined.Gavel,
            label = stringResource(AboutRes.string.code_of_conduct),
            onClickAction = onCodeOfConductItemClick,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                ),
        )
    }
    item {
        AboutContentColumn(
            leadingIcon = Outlined.FileCopy,
            label = stringResource(AboutRes.string.license),
            onClickAction = onLicenseItemClick,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                ),
        )
    }
    item {
        AboutContentColumn(
            leadingIcon = Outlined.PrivacyTip,
            label = stringResource(AboutRes.string.privacy_policy),
            onClickAction = onPrivacyPolicyItemClick,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                ),
        )
    }
    item {
        AboutContentColumn(
            leadingIcon = Outlined.Settings,
            label = stringResource(AboutRes.string.settings),
            onClickAction = onSettingsItemClick,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                ),
        )
    }
}

@Preview
@Composable
private fun AboutOthersPreview() {
    KaigiPreviewContainer {
        LazyColumn {
            aboutOthers(
                onCodeOfConductItemClick = {},
                onLicenseItemClick = {},
                onPrivacyPolicyItemClick = {},
                onSettingsItemClick = {},
            )
        }
    }
}
