package io.github.droidkaigi.confsched.sessions

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object SearchNavKey : NavKey

context(screenContext: SearchScreenContext)
@Composable
fun SearchScreenRoot() {
}
