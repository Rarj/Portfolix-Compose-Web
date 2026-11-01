package com.rioarj.portfolix.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.motion.LocalScrollInfo
import com.rioarj.portfolix.style.orangeColor

/**
 * Slow endless strip of outlined words. Runs only while on screen and not hovered,
 * and moves in the graphics layer (no recomposition per frame).
 */
@Composable
internal fun Marquee(
    items: List<String>,
    color: Color,
    reverse: Boolean = false,
    durationMs: Int = 90_000,
    modifier: Modifier = Modifier,
) {
    val info = LocalScrollInfo.current
    var top by remember { mutableIntStateOf(Int.MAX_VALUE) }
    var height by remember { mutableIntStateOf(0) }
    var unit by remember { mutableIntStateOf(1) }
    val interaction = remember { MutableInteractionSource() }
    val hovered by interaction.collectIsHoveredAsState()
    val inView by remember(info) {
        derivedStateOf { top < info.scroll.value + info.viewportPx && top + height > info.scroll.value }
    }
    val progress = remember { Animatable(0f) }
    val active = inView && !hovered

    LaunchedEffect(active) {
        if (!active) return@LaunchedEffect
        while (true) {
            val remaining = 1f - progress.value
            progress.animateTo(
                1f,
                tween((remaining * durationMs).toInt().coerceAtLeast(1), easing = LinearEasing)
            )
            progress.snapTo(0f)
        }
    }

    val style = remember(color) {
        TextStyle(
            fontSize = 56.sp,
            fontWeight = FontWeight.ExtraBold,
            color = color,
            drawStyle = Stroke(width = 2f),
        )
    }

    Box(
        modifier
            .fillMaxWidth()
            .clipToBounds()
            .hoverable(interaction)
            .onGloballyPositioned {
                top = (it.positionInRoot().y + info.scroll.value).toInt()
                height = it.size.height
            },
    ) {
        Row(
            Modifier
                .wrapContentWidth(align = Alignment.Start, unbounded = true)
                .graphicsLayer {
                    val p = if (reverse) 1f - progress.value else progress.value
                    translationX = -p * unit
                },
        ) {
            repeat(2) { copy ->
                Row(if (copy == 0) Modifier.onSizeChanged {
                    unit = it.width.coerceAtLeast(1)
                } else Modifier) {
                    items.forEach { item ->
                        Text(
                            text = item,
                            modifier = Modifier.padding(horizontal = 20.dp),
                            style = style
                        )
                        Text(text = "•", style = style.copy(color = orangeColor, drawStyle = null))
                    }
                }
            }
        }
    }
}
