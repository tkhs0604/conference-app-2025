package io.github.confsched.profile.edit

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.droidkaigi.confsched.profile.ProfileRes
import io.github.droidkaigi.confsched.profile.profile_card_title
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEditScreen(
    uiState: ProfileEditScreenUiState,
    onCreateClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(ProfileRes.string.profile_card_title))
                }
            )
        },
        modifier = modifier
    ) { contentPadding ->
        val scrollState = rememberScrollableState { delta ->
            delta
        }
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .scrollable(
                    state = scrollState,
                    orientation = Orientation.Horizontal,
                )
        ) {
            Text("Profile Edit Screen")
            Text("$uiState")
            Button(
                onClick = onCreateClick,
            ) {
                Text("Create")
            }
        }
    }
}
