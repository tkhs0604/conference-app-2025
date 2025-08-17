package io.github.droidkaigi.confsched

import androidx.compose.runtime.Composable

@Composable
context(appGraph: AppGraph)
expect fun KaigiAppUi()
