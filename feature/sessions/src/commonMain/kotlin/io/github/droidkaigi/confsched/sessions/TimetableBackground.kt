package io.github.droidkaigi.confsched.sessions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TimetableBackground() {
    Image(
        painter = painterResource(SessionsRes.drawable.background),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center,
        modifier = Modifier.fillMaxSize().alpha(0.18f),
    )
}

@Preview
@Composable
private fun TimetableBackgroundPreview() {
    TimetableBackground()
}
