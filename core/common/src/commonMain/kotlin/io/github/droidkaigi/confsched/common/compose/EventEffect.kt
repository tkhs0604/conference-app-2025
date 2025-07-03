package io.github.droidkaigi.confsched.common.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import io.github.droidkaigi.confsched.context.DataMutationContext
import io.github.droidkaigi.confsched.context.PresenterContext
import io.github.droidkaigi.confsched.context.useDataMutationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

typealias EventFlow<T> = MutableSharedFlow<T>

@Composable
fun <T> rememberEventFlow(): EventFlow<T> {
    return remember {
        MutableSharedFlow(extraBufferCapacity = 20)
    }
}

context(_: PresenterContext)
@Composable
fun <EVENT> EventEffect(
    eventFlow: EventFlow<EVENT>,
    block: suspend context (DataMutationContext) CoroutineScope.(event: EVENT) -> Unit,
) {
    SafeLaunchedEffect(eventFlow) {
        useDataMutationContext {
            supervisorScope {
                eventFlow.collect { event ->
                    launch {
                        block(event)
                    }
                }
            }
        }
    }
}
