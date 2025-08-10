package io.github.droidkaigi.confsched.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

enum class ProfileTheme(
    val primaryColor: Color,
    val containerColor: Color,
    val dimColor: Color,
) {
    Iguana(
        primaryColor = Color(0xFF45E761),
        containerColor = Color(0xFF45E761),
        dimColor = Color(0xFF132417),
    ),
    Hedgehog(
        primaryColor = Color(0xFFFF974B),
        containerColor = Color(0xFFFF974B),
        dimColor = Color(0xFF251C15),
    ),
    Giraffe(
        primaryColor = Color(0xFFDDD33C),
        containerColor = Color(0xFFDDD33C),
        dimColor = Color(0xFF222213)
    ),
    Flamingo(
        primaryColor = Color(0xFFFF53CF),
        containerColor = Color(0xFFFF53CF),
        dimColor = Color(0xFF271A25)
    ),
    Jellyfish(
        primaryColor = Color(0xFF44ADE7),
        containerColor = Color(0xFF44ADE7),
        dimColor = Color(0xFF121E25)
    );

    companion object {
        fun fromName(name: String): ProfileTheme {
            return entries.firstOrNull { it.name == name }
                ?: error("Unknown ProfileTheme: $name")
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
