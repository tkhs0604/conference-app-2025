@file:Suppress("UNUSED")

package io.github.droidkaigi.confsched

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import app.cash.molecule.RecompositionMode
import app.cash.molecule.moleculeFlow
import io.github.droidkaigi.confsched.common.compose.EventFlow
import io.github.droidkaigi.confsched.favorites.FavoritesScreenEvent
import io.github.droidkaigi.confsched.favorites.FavoritesScreenUiState
import io.github.droidkaigi.confsched.favorites.favoritesScreenPresenter
import io.github.droidkaigi.confsched.favorites.rememberFavoritesScreenContextRetained
import io.github.droidkaigi.confsched.interop.IOSLifecycleOwner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import soil.query.SwrClientPlus
import soil.query.annotation.ExperimentalSoilQueryApi
import soil.query.compose.LocalMutationClient
import soil.query.compose.LocalQueryClient
import soil.query.compose.LocalSubscriptionClient
import soil.query.compose.LocalSwrClient
import soil.query.compose.rememberQuery
import soil.query.compose.rememberSubscription
import soil.query.core.DataModel
import soil.query.core.Reply
import soil.query.core.combine
import soil.query.core.uuid

@OptIn(ExperimentalSoilQueryApi::class)
fun favoritesScreenPresenterStateFlow(
    iosAppGraph: IosAppGraph,
    eventFlow: EventFlow<FavoritesScreenEvent>,
): Flow<FavoritesScreenUiState> {
    val iosLifecycleOwner = IOSLifecycleOwner()
    return moleculeFlow(RecompositionMode.Immediate) {
        compositionLocalProviderWithReturnValue(
            LocalLifecycleOwner provides iosLifecycleOwner,
            LocalViewModelStoreOwner provides iosLifecycleOwner,
        ) {
            swrClientProviderWithReturnValue(iosAppGraph.swrClientPlus) {
                with(iosAppGraph) {
                    with(rememberFavoritesScreenContextRetained()) {
                        soilDataBoundary(
                            rememberQuery(timetableQueryKey),
                            rememberSubscription(favoriteTimetableIdsSubscriptionKey),
                        ) { timetable, favoriteTimetableItemIds ->
                            favoritesScreenPresenter(
                                // To avoid infinite recomposition issues, we use remember to keep the event flow stable.
                                eventFlow = remember { eventFlow },
                                timetable = timetable.copy(bookmarks = favoriteTimetableItemIds),
                            )
                        }
                    }
                }
            }
        }
    }
        .filterNotNull()
        .distinctUntilChanged()
}

fun favoritesScreenEventFlow(): EventFlow<FavoritesScreenEvent> {
    return MutableSharedFlow(extraBufferCapacity = 20)
}

@Composable
private fun <T1, T2, RESULT> soilDataBoundary(
    state1: DataModel<T1>,
    state2: DataModel<T2>,
    combinedState: @Composable (T1, T2) -> RESULT,
): RESULT? {
    return when (val reply = Reply.combine(state1.reply, state2.reply, ::Pair)) {
        is Reply.Some -> combinedState(reply.value.first, reply.value.second)
        is Reply.None -> null
    }
}

@Composable
@OptIn(InternalComposeApi::class)
@Suppress("ComposeCompositionLocalUsage")
private fun <T> compositionLocalProviderWithReturnValue(
    vararg value: ProvidedValue<*>,
    content: @Composable () -> T,
): T {
    currentComposer.startProviders(value)
    val result = content()
    currentComposer.endProviders()
    return result
}

@Composable
private fun <T> swrClientProviderWithReturnValue(
    client: SwrClientPlus,
    content: @Composable () -> T,
): T {
    DisposableEffect(client) {
        val id = uuid()
        client.onMount(id)
        onDispose {
            client.onUnmount(id)
        }
    }

    return compositionLocalProviderWithReturnValue(
        LocalSwrClient provides client,
        LocalQueryClient provides client,
        LocalMutationClient provides client,
        LocalSubscriptionClient provides client,
    ) {
        content()
    }
}
