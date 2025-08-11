package io.github.droidkaigi.confsched.droidkaigiui.extension

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only

fun WindowInsets.excludeTop(): WindowInsets {
    return this.only(WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom)
}
