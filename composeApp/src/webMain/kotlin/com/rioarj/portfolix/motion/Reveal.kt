package com.rioarj.portfolix.motion

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

/**
 * Plays once, the first time the content nears the bottom of the viewport.
 * [mask] = slide up from behind a clip edge (editorial line reveal); otherwise fade and rise.
 * Animates in the graphics layer only, so scrolling never recomposes it.
 */
@Composable
internal fun Reveal(
    modifier: Modifier = Modifier,
    delayMs: Int = 0,
    distance: Dp = 28.dp,
    mask: Boolean = false,
    content: @Composable () -> Unit,
) {
    val info = LocalScrollInfo.current
    val tier = LocalMotionTier.current
    var top by remember { mutableIntStateOf(Int.MAX_VALUE) }
    var shown by remember { mutableStateOf(!tier.animates) }

    if (tier.animates) {
        LaunchedEffect(info, delayMs) {
            snapshotFlow { top < info.scroll.value + info.viewportPx * 0.94f }.first { it }
            if (delayMs > 0) delay(delayMs.toLong())
            shown = true
        }
    }

    val progress by animateFloatAsState(
        targetValue = if (shown) 1f else 0f,
        animationSpec = if (tier.animates) tween(
            Motion.revealMs,
            easing = Motion.standard
        ) else snap(),
        label = "reveal",
    )
    val distancePx = with(LocalDensity.current) { distance.toPx() }

    Box(
        modifier = modifier
            .onGloballyPositioned { top = (it.positionInRoot().y + info.scroll.value).toInt() }
            .then(if (mask) Modifier.clipToBounds() else Modifier),
        propagateMinConstraints = true,
    ) {
        Box(
            modifier = Modifier.graphicsLayer {
                if (mask) {
                    translationY = (1f - progress) * size.height
                } else {
                    alpha = progress
                    translationY = (1f - progress) * distancePx
                }
            },
            propagateMinConstraints = true,
        ) { content() }
    }
}
