package com.rioarj.portfolix.motion

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rioarj.portfolix.style.orangeColor
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/** Smoothed mouse position in root coordinates. Only a real mouse sets [active]. */
internal class PointerState {
    val position = Animatable(Offset.Zero, Offset.VectorConverter)
    var active by mutableStateOf(false)
}

internal val LocalPointer = staticCompositionLocalOf { PointerState() }

/** Install once at the root. Never consumes events. */
internal fun Modifier.trackPointer(state: PointerState): Modifier = pointerInput(state) {
    coroutineScope {
        val scope = this
        awaitPointerEventScope {
            while (true) {
                val event = awaitPointerEvent(PointerEventPass.Initial)
                val change = event.changes.firstOrNull() ?: continue
                if (change.type != PointerType.Mouse) {
                    state.active = false
                    continue
                }
                when (event.type) {
                    PointerEventType.Exit -> state.active = false
                    PointerEventType.Move, PointerEventType.Enter -> {
                        if (!state.active) {
                            state.active = true
                            scope.launch { state.position.snapTo(change.position) }
                        } else {
                            scope.launch {
                                state.position.animateTo(
                                    change.position,
                                    spring(stiffness = Spring.StiffnessLow)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

/** Pulls the element slightly toward the mouse, springs back on leave. Full tier, mouse only. */
@Composable
internal fun Modifier.magnetic(strength: Float = 0.25f): Modifier {
    if (!LocalMotionTier.current.isFull) return this
    val offset = remember { Animatable(Offset.Zero, Offset.VectorConverter) }
    return this
        .pointerInput(Unit) {
            coroutineScope {
                val scope = this
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent(PointerEventPass.Initial)
                        val change = event.changes.firstOrNull() ?: continue
                        if (change.type != PointerType.Mouse) continue
                        val target = when (event.type) {
                            PointerEventType.Exit -> Offset.Zero
                            PointerEventType.Move, PointerEventType.Enter ->
                                (change.position - Offset(
                                    size.width / 2f,
                                    size.height / 2f
                                )) * strength

                            else -> continue
                        }
                        scope.launch {
                            offset.animateTo(
                                target,
                                spring(dampingRatio = 0.55f, stiffness = Spring.StiffnessMedium)
                            )
                        }
                    }
                }
            }
        }
        .graphicsLayer {
            translationX = offset.value.x
            translationY = offset.value.y
        }
}

/**
 * Soft glow that follows the mouse inside this element. Place AFTER `background` so it draws
 * over the fill but under the content.
 */
@Composable
internal fun Modifier.hoverSpotlight(
    radius: Dp = 240.dp,
    color: Color = orangeColor,
    maxAlpha: Float = 0.16f,
): Modifier {
    if (!LocalMotionTier.current.isFull) return this
    var position by remember { mutableStateOf(Offset.Zero) }
    var inside by remember { mutableStateOf(false) }
    val fade by animateFloatAsState(
        if (inside) 1f else 0f,
        tween(Motion.hoverMs),
        label = "spotlight"
    )
    return this
        .pointerInput(Unit) {
            awaitPointerEventScope {
                while (true) {
                    val event = awaitPointerEvent(PointerEventPass.Initial)
                    val change = event.changes.firstOrNull() ?: continue
                    if (change.type != PointerType.Mouse) continue
                    when (event.type) {
                        PointerEventType.Exit -> inside = false
                        PointerEventType.Move, PointerEventType.Enter -> {
                            position = change.position
                            inside = true
                        }
                    }
                }
            }
        }
        .drawBehind {
            if (fade > 0f) {
                drawRect(
                    Brush.radialGradient(
                        colors = listOf(color.copy(alpha = maxAlpha * fade), Color.Transparent),
                        center = position,
                        radius = radius.toPx(),
                    )
                )
            }
        }
}
