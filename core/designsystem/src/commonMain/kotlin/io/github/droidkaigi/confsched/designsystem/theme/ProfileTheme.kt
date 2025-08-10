package io.github.droidkaigi.confsched.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

sealed interface ProfileTheme {
    val primaryColor: Color
    val containerColor: Color
    val dimColor: Color

    data object Iguana : ProfileTheme {
        override val primaryColor = Color(0xFF45E761)
        override val containerColor = Color(0xFF45E761).copy(alpha = 0.1f)
        override val dimColor = Color(0xFF132417)
    }

    data object Hedgehog : ProfileTheme {
        override val primaryColor = Color(0xFFFF974B)
        override val containerColor = Color(0xFFFF974B).copy(alpha = 0.1f)
        override val dimColor = Color(0xFF251C15)
    }

    data object Giraffe : ProfileTheme {
        override val primaryColor = Color(0xFFDDD33C)
        override val containerColor = Color(0xFFDDD33C).copy(alpha = 0.1f)
        override val dimColor = Color(0xFF222213)
    }

    data object Flamingo : ProfileTheme {
        override val primaryColor = Color(0xFFFF53CF)
        override val containerColor = Color(0xFFFF53CF).copy(alpha = 0.1f)
        override val dimColor = Color(0xFF271A25)
    }

    data object Jellyfish : ProfileTheme {
        override val primaryColor = Color(0xFF44ADE7)
        override val containerColor = Color(0xFF44ADE7).copy(alpha = 0.1f)
        override val dimColor = Color(0xFF121E25)
    }

    companion object {
        fun fromName(name: String): ProfileTheme {
            return when (name.lowercase()) {
                "iguana" -> Iguana
                "hedgehog" -> Hedgehog
                "giraffe" -> Giraffe
                "flamingo" -> Flamingo
                "jellyfish" -> Jellyfish
                else -> throw IllegalArgumentException("Unknown ProfileTheme: $name")
            }
        }
    }
}

@Suppress("CompositionLocalAllowlist")
val LocalProfileTheme: ProvidableCompositionLocal<ProfileTheme> = staticCompositionLocalOf {
    error("No RoomTheme provided")
}

@Composable
fun ProvideProfileTheme(profileTheme: ProfileTheme, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalProfileTheme provides profileTheme) {
        content()
    }
}
