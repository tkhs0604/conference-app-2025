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
            fontWeight = FontWeight.W400,
            fontSize = 57.sp,
            lineHeight = 64.sp,
            letterSpacing = (-0.25).sp,
        ),
        displayMedium = TextStyle(
            fontFamily = changoFontFamily,
            fontWeight = FontWeight.W400,
            fontSize = 45.sp,
            lineHeight = 52.sp,
            letterSpacing = 0.sp,
        ),
        displaySmall = TextStyle(
            fontFamily = changoFontFamily,
            fontWeight = FontWeight.W400,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.sp,
        ),
        headlineLarge = TextStyle(
            fontFamily = changoFontFamily,
            fontWeight = FontWeight.W400,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp,
        ),
        headlineMedium = TextStyle(
            fontFamily = changoFontFamily,
            fontWeight = FontWeight.W400,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp,
        ),
        headlineSmall = TextStyle(
            fontFamily = changoFontFamily,
            fontWeight = FontWeight.W400,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp,
        ),
    )
}
