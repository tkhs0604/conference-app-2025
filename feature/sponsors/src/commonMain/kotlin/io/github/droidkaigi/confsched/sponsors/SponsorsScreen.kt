package io.github.droidkaigi.confsched.sponsors

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.droidkaigi.confsched.droidkaigiui.KaigiPreviewContainer
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SponsorsScreen(
    onBackClick: () -> Unit,
    modifier: Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(stringResource(SponsorsRes.string.sponsors_title))},
                scrollBehavior = scrollBehavior,
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        Column(modifier=Modifier.padding(innerPadding)) {
            //Platinum
            Text(stringResource(SponsorsRes.string.platinum_sponsors_title))
            LazyColumn { }
            //Gold
            Text(stringResource(SponsorsRes.string.gold_sponsors_title))
            LazyColumn { }
            //Supporters
            Text(stringResource(SponsorsRes.string.supporters_title))
            LazyColumn { }
        }
    }
}

@Preview
@Composable
private fun SponsorScreenPreview() {
    KaigiPreviewContainer {
        SponsorsScreen(
//            contributors = Contributor.fakes(),
//            onContributorItemClick = {},
//            onBackClick = {},
            modifier = Modifier,
        )
    }
}
