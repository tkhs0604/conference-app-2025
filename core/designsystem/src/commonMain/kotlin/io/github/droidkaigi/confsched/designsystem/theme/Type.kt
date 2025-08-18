package io.github.droidkaigi.confsched.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.github.droidkaigi.confsched.designsystem.Chango_Regular
import io.github.droidkaigi.confsched.designsystem.DesignsystemRes
import org.jetbrains.compose.resources.Font

@Composable
fun AppTypography(): Typography {
    val changoFontFamily = FontFamily(
        Font(DesignsystemRes.font.Chango_Regular, FontWeight.Normal)
    )

    return Typography(
        displayLarge = TextStyle(
            fontFamily = changoFontFamily,
            fontSize = 57.sp,
            lineHeight = 64.sp,
        ),
        displayMedium = TextStyle(
            fontFamily = changoFontFamily,
            fontSize = 45.sp,
            lineHeight = 52.sp,
        ),
        displaySmall = TextStyle(
            fontFamily = changoFontFamily,
            fontSize = 36.sp,
            lineHeight = 44.sp,
        ),
        headlineLarge = TextStyle(
            fontFamily = changoFontFamily,
            fontSize = 32.sp,
            lineHeight = 40.sp,
        ),
        headlineMedium = TextStyle(
            fontFamily = changoFontFamily,
            fontSize = 28.sp,
            lineHeight = 36.sp,
        ),
        headlineSmall = TextStyle(
            fontFamily = changoFontFamily,
            fontSize = 24.sp,
            lineHeight = 32.sp,
        ),
    )
}
