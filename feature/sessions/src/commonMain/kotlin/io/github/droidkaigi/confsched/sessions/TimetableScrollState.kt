package io.github.droidkaigi.confsched.sessions

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.animateDecay
import androidx.compose.animation.core.exponentialDecay
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.pointer.util.VelocityTracker
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun rememberTimetableScrollState(): TimetableScrollState = rememberSaveable(
    saver = TimetableScrollState.Saver
) {
    TimetableScrollState()
}

class TimetableScrollState(
    initialScrollX: Float = 0f,
    initialScrollY: Float = 0f,
) {
    private val velocityTracker = VelocityTracker()
    var componentPositionInRoot = Offset.Zero
    private var cancelFling = false

    private val _scrollX = Animatable(initialScrollX)
    val scrollX: Float
        get() = _scrollX.value
    private val _scrollY = Animatable(initialScrollY)
    val scrollY: Float
        get() = _scrollY.value
    val maxX: Float
        get() = _scrollX.lowerBound ?: 0f
    val maxY: Float
        get() = _scrollY.lowerBound ?: 0f

    suspend fun scroll(
        scrollX: Float,
        scrollY: Float,
        timeMillis: Long,
        position: Offset,
    ) {
        cancelFling = true
        if (scrollX.isNaN().not() && scrollY.isNaN().not()) {
            coroutineScope {
                val positionInRoot = position + componentPositionInRoot
                velocityTracker.addPosition(timeMillis = timeMillis, position = positionInRoot)
                launch {
                    _scrollX.snapTo(scrollX)
                }
                launch {
                    _scrollY.snapTo(scrollY)
                }
            }
        }
    }

    suspend fun flingIfPossible(nestedScrollDispatcher: NestedScrollDispatcher) = coroutineScope {
        cancelFling = false
        val velocity = velocityTracker.calculateVelocity()
        launch {
            _scrollX.animateDecay(
                velocity.x / 2f,
                exponentialDecay(),
            )
        }

        var lastValue = 0f
        AnimationState(
            initialValue = 0f,
            initialVelocity = velocity.y,
        ).animateDecay(
            exponentialDecay(),
        ) {
            launch {
                val delta = Offset(0f, value - lastValue)
                lastValue = value
                val preConsumed = nestedScrollDispatcher.dispatchPreScroll(
                    available = delta,
                    source = NestedScrollSource.SideEffect,
                )

                val weAvailable = delta - preConsumed
                val previousY = _scrollY.value
                _scrollY.snapTo(_scrollY.value + weAvailable.y)
                val weConsumed = Offset(0f, _scrollY.value - previousY)

                nestedScrollDispatcher.dispatchPostScroll(
                    consumed = preConsumed + weConsumed,
                    available = weAvailable - weConsumed,
                    source = NestedScrollSource.SideEffect,
                )

                if (cancelFling) {
                    this@animateDecay.cancelAnimation()
                }
            }
        }
    }

    suspend fun flingYIfPossible() = coroutineScope {
        val velocity = velocityTracker.calculateVelocity()
        launch {
            _scrollY.animateDecay(
                velocity.y / 2f,
                exponentialDecay(),
            )
        }
    }

    suspend fun flingXIfPossible() = coroutineScope {
        val velocity = velocityTracker.calculateVelocity()
        launch {
            _scrollX.animateDecay(
                velocity.x / 2f,
                exponentialDecay(),
            )
        }
    }

    fun updateBounds(maxX: Float, maxY: Float) {
        _scrollX.updateBounds(maxX, 0f)
        _scrollY.updateBounds(maxY, 0f)
    }

    fun resetTracking() {
        velocityTracker.resetTracking()
    }

    companion object Companion {
        val Saver: Saver<TimetableScrollState, *> = listSaver(
            save = {
                listOf(
                    it.scrollX,
                    it.scrollY,
                )
            },
            restore = {
                TimetableScrollState(
                    initialScrollX = it[0],
                    initialScrollY = it[1],
                )
            },
        )
    }
}
