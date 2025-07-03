package io.github.droidkaigi.confsched.common.compose

import androidx.compose.runtime.Composable
import io.github.droidkaigi.confsched.context.PresenterContext
import io.github.droidkaigi.confsched.context.usePresenterContext

@Composable
fun <UI_STATE> providePresenterDefaults(
    uiStateProducer: @Composable PresenterContext.() -> UI_STATE,
): UI_STATE {
    usePresenterContext {
        return uiStateProducer()
    }
}
