package io.github.droidkaigi.confsched.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

sealed interface RoomTheme {
    val primaryColor: Color
    val containerColor: Color
    val dimColor: Color

    data object Jellyfish : RoomTheme {
        override val primaryColor = Color(0xFF45E761)
        override val containerColor = Color(0xFF45E761).copy(alpha = 0.1f)
        override val dimColor = Color(0xFF132417)
    }

    data object Koala : RoomTheme {
        override val primaryColor = Color(0xFFFF974B)
        override val containerColor = Color(0xFFFF974B).copy(alpha = 0.1f)
        override val dimColor = Color(0xFF251C15)
    }

    data object Ladybug : RoomTheme {
        override val primaryColor = Color(0xFFDDD33C)
        override val containerColor = Color(0xFFDDD33C).copy(alpha = 0.1f)
        override val dimColor = Color(0xFF222213)
    }

    data object Meerkat : RoomTheme {
        override val primaryColor = Color(0xFFFF53CF)
        override val containerColor = Color(0xFFFF53CF).copy(alpha = 0.1f)
        override val dimColor = Color(0xFF271A25)
    }

    data object Narwhal : RoomTheme {
        override val primaryColor = Color(0xFF44ADE7)
        override val containerColor = Color(0xFF44ADE7).copy(alpha = 0.1f)
        override val dimColor = Color(0xFF121E25)
    }
}

@Suppress("CompositionLocalAllowlist")
val LocalRoomTheme: ProvidableCompositionLocal<RoomTheme> = staticCompositionLocalOf {
    error("No RoomTheme provided")
}

@Composable
fun ProvideRoomTheme(roomTheme: RoomTheme, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalRoomTheme provides roomTheme) {
        content()
    }
}
