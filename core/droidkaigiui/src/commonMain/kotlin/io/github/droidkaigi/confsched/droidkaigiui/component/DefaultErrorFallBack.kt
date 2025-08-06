package io.github.droidkaigi.confsched.droidkaigiui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.droidkaigi.confsched.context.ScreenContext
import io.github.droidkaigi.confsched.droidkaigiui.DroidkaigiuiRes
import io.github.droidkaigi.confsched.droidkaigiui.KaigiPreviewContainer
import io.github.droidkaigi.confsched.droidkaigiui.error_mascot
import io.github.droidkaigi.confsched.droidkaigiui.error_occurred
import io.github.droidkaigi.confsched.droidkaigiui.retry
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import soil.plant.compose.reacty.ErrorBoundaryContext

context(_: ScreenContext)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultErrorFallBack(
    errorBoundaryContext: ErrorBoundaryContext,
    modifier: Modifier = Modifier,
    showBackNavigation: Boolean = false,
    onClickBack: () -> Unit = {},
    background: @Composable (PaddingValues) -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    // Nothing to display here
                },
                navigationIcon = {
                    if (showBackNavigation) {
                        IconButton(onClick = onClickBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                )
            )
        },
        containerColor = Color.Transparent,
        modifier = modifier,
    ) { innerPadding ->
        background(innerPadding)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(35.dp, Alignment.CenterVertically),
        ) {
            Image(
                painter = painterResource(DroidkaigiuiRes.drawable.error_mascot),
                contentDescription = null,
            )
            Text(
                text = stringResource(DroidkaigiuiRes.string.error_occurred),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onSurface,
            )
            Button(onClick = { errorBoundaryContext.reset?.invoke() }) {
                Text(
                    text = stringResource(DroidkaigiuiRes.string.retry),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            }
        }
    }
}

@Preview
@Composable
private fun DefaultErrorFallBackPreview() {
    KaigiPreviewContainer {
        DefaultErrorFallBack(
            onClickBack = {},
            errorBoundaryContext = ErrorBoundaryContext(
                err = Throwable("An error occurred"),
                reset = {},
            ),
            modifier = Modifier,
        )
    }
}
