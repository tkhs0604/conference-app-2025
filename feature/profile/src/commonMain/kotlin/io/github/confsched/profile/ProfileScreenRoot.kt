package io.github.confsched.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import io.github.confsched.profile.card.ProfileCardScreenRoot
import io.github.confsched.profile.edit.ProfileEditScreenRoot
import io.github.droidkaigi.confsched.droidkaigiui.SoilDataBoundary
import soil.query.compose.rememberSubscription

context(screenContext: ProfileScreenContext)
@Composable
fun ProfileScreenRoot() {
    SoilDataBoundary(
        state = rememberSubscription(screenContext.profileSubscriptionKey),
    ) { profile ->
        var isEditMode by remember { mutableStateOf(false) }
        when {
            profile != null && !isEditMode -> {
                ProfileCardScreenRoot(
                    profile = profile,
                    onEditClick = { isEditMode = true },
                )
            }

            else -> {
                ProfileEditScreenRoot(
                    profile = profile,
                    onProfileCreate = { isEditMode = false }
                )
            }
        }
    }
}
