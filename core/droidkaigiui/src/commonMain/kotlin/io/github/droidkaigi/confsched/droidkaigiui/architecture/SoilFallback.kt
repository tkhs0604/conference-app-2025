@file:Suppress("UNUSED")

package io.github.droidkaigi.confsched.droidkaigiui.architecture

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

sealed interface SoilFallback {
    val suspenseFallback: @Composable context(SoilSuspenseContext) BoxScope.() -> Unit
    val errorFallback: @Composable context(SoilErrorContext) BoxScope.() -> Unit
}

object SoilFallbackDefaults {
    fun empty(): SoilFallback = Empty

    fun appBar(
        title: String,
        onBackClick: (() -> Unit)? = null,
        appBarSize: AppBarSize = AppBarSize.Default,
    ): SoilFallback = AppBar(
        title = title,
        onBackClick = onBackClick,
        size = appBarSize,
    )

    fun simple(): SoilFallback = Simple

    fun custom(
        suspenseFallback: @Composable context(SoilSuspenseContext) BoxScope.() -> Unit,
        errorFallback: @Composable context(SoilErrorContext) BoxScope.() -> Unit,
    ): SoilFallback = Custom(
        suspenseFallback = suspenseFallback,
        errorFallback = errorFallback,
    )
}

private object Empty : SoilFallback {
    override val suspenseFallback: @Composable context(SoilSuspenseContext) BoxScope.() -> Unit = {}
    override val errorFallback: @Composable context(SoilErrorContext) BoxScope.() -> Unit = {}
}

private object Simple : SoilFallback {
    override val suspenseFallback: @Composable context(SoilSuspenseContext) BoxScope.() -> Unit
        get() = { DefaultSuspenseFallbackContent() }
    override val errorFallback: @Composable context(SoilErrorContext) BoxScope.() -> Unit
        get() = { DefaultErrorFallbackContent() }
}

private class AppBar(
    val title: String,
    val onBackClick: (() -> Unit)? = null,
    val size: AppBarSize = AppBarSize.Default,
) : SoilFallback {
    override val suspenseFallback: @Composable context(SoilSuspenseContext) BoxScope.() -> Unit = {
        AppBarFallbackScaffold(
            title = title,
            onBackClick = onBackClick,
            appBarSize = size,
        ) { innerPadding ->
            DefaultSuspenseFallbackContent(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }

    override val errorFallback: @Composable context(SoilErrorContext) BoxScope.() -> Unit = {
        AppBarFallbackScaffold(
            title = title,
            onBackClick = onBackClick,
            appBarSize = size,
        ) { innerPadding ->
            DefaultErrorFallbackContent(
                modifier = Modifier.padding(innerPadding),
            )
        }
    }
}

private class Custom(
    override val suspenseFallback: @Composable context(SoilSuspenseContext) BoxScope.() -> Unit,
    override val errorFallback: @Composable context(SoilErrorContext) BoxScope.() -> Unit,
) : SoilFallback
