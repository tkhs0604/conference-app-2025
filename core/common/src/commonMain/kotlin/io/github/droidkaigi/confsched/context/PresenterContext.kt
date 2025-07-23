package io.github.droidkaigi.confsched.context

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Context for presenter composable functions.
 */
interface PresenterContext

class DefaultPresenterContext : PresenterContext

@OptIn(ExperimentalContracts::class)
inline fun usePresenterContext(
    block: PresenterContext.() -> Unit,
) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block(DefaultPresenterContext())
}
