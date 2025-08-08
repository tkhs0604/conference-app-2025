package io.github.droidkaigi.confsched.contributors

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import io.github.droidkaigi.confsched.contributors.component.ContributorItem
import io.github.droidkaigi.confsched.contributors.component.ContributorsCounter
import io.github.droidkaigi.confsched.droidkaigiui.KaigiPreviewContainer
import io.github.droidkaigi.confsched.droidkaigiui.component.AnimatedMediumTopAppBar
import io.github.droidkaigi.confsched.model.contributors.Contributor
import io.github.droidkaigi.confsched.model.contributors.fakes
import kotlinx.collections.immutable.PersistentList
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContributorsScreen(
    contributors: PersistentList<Contributor>,
    onContributorItemClick: (profileUrl: String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        topBar = {
            AnimatedMediumTopAppBar(
                title = stringResource(ContributorsRes.string.contributor_title),
                onBackClick = onBackClick,
                scrollBehavior = scrollBehavior,
                navIconContentDescription = "Back",
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        ) {
            item {
                ContributorsCounter(totalContributors = contributors.size)
            }
            items(
                items = contributors,
                key = { it.id },
            ) {
                ContributorItem(
                    contributor = it,
                    onClick = onContributorItemClick,
                )
            }
        }
    }
}

@Preview
@Composable
private fun ContributorsScreenPreview() {
    KaigiPreviewContainer {
        ContributorsScreen(
            contributors = Contributor.fakes(),
            onContributorItemClick = {},
            onBackClick = {},
            modifier = Modifier,
        )
    }
}
