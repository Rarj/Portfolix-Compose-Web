package com.rioarj.portfolix.component

import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.rioarj.portfolix.motion.LocalMotionTier
import com.rioarj.portfolix.motion.LocalScrollInfo
import com.rioarj.portfolix.motion.isFull
import com.rioarj.portfolix.style.orangeColor
import com.rioarj.portfolix.style.subtitleColor

/**
 * Paragraph whose words brighten as it scrolls through the viewport (Full tier).
 * Other tiers show everything lit. Recomposes once per word step, not per frame.
 */
@Composable
internal fun WordHighlightText(
    lead: String,
    body: String,
    fontSize: TextUnit,
    modifier: Modifier = Modifier,
    maxWidth: Dp = 760.dp,
    textAlign: TextAlign = TextAlign.Start,
    lineHeightFactor: Float = 1.75f,
) {
    val info = LocalScrollInfo.current
    val tier = LocalMotionTier.current
    val words = remember(body) { body.split(" ") }
    var top by remember { mutableIntStateOf(Int.MAX_VALUE) }
    var height by remember { mutableIntStateOf(1) }

    val lit by remember(info, tier, words) {
        derivedStateOf {
            if (!tier.isFull) {
                words.size
            } else {
                val start = top - info.viewportPx * 0.85f
                val span = height + info.viewportPx * 0.25f
                (((info.scroll.value - start) / span) * words.size).toInt().coerceIn(0, words.size)
            }
        }
    }

    Text(
        modifier = modifier
            .widthIn(max = maxWidth)
            .onGloballyPositioned {
                top = (it.positionInRoot().y + info.scroll.value).toInt()
                height = it.size.height.coerceAtLeast(1)
            },
        text = buildAnnotatedString {
            withStyle(SpanStyle(color = orangeColor, fontWeight = FontWeight.SemiBold)) {
                append(
                    lead
                )
            }
            words.forEachIndexed { index, word ->
                val color = if (index < lit) subtitleColor else subtitleColor.copy(alpha = 0.3f)
                withStyle(SpanStyle(color = color)) { append(if (index == words.lastIndex) word else "$word ") }
            }
        },
        color = subtitleColor,
        fontSize = fontSize,
        fontWeight = FontWeight.Normal,
        lineHeight = fontSize * lineHeightFactor,
        textAlign = textAlign,
    )
}
