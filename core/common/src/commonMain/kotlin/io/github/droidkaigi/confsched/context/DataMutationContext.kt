package io.github.droidkaigi.confsched.context

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/**
 * Mark with this context to indicate that the function is a data mutation function.
 * This can only be used in the scope of [useDataMutationContext].
 */
interface DataMutationContext

internal object DefaultDataMutationContext : DataMutationContext

@OptIn(ExperimentalContracts::class)
inline fun useDataMutationContext(
    block: DataMutationContext.() -> Unit,
) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    block(DefaultDataMutationContext)
}
