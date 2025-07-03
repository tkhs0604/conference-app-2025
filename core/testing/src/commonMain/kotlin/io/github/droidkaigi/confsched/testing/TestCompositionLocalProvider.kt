package io.github.droidkaigi.confsched.testing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import soil.query.SwrCachePlus
import soil.query.SwrCacheScope
import soil.query.compose.SwrClientProvider

@Composable
fun TestCompositionLocalProvider(content: @Composable () -> Unit) {
    SwrClientProvider(SwrCachePlus(SwrCacheScope())) {
        CompositionLocalProvider(
            LocalLifecycleOwner provides FakeLocalLifecycleOwner(),
            LocalViewModelStoreOwner provides FakeViewModelStoreOwner(),
            content = content,
        )
    }
}

private class FakeLocalLifecycleOwner : LifecycleOwner {
    override val lifecycle: Lifecycle = LifecycleRegistry(this).apply {
        currentState = Lifecycle.State.RESUMED
    }
}

private class FakeViewModelStoreOwner : ViewModelStoreOwner {
    override val viewModelStore: ViewModelStore = ViewModelStore()
}
