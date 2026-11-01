package com.rioarj.portfolix.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import com.rioarj.portfolix.motion.LocalMotionTier
import com.rioarj.portfolix.motion.LocalScrollInfo
import com.rioarj.portfolix.motion.animates
import com.rioarj.portfolix.style.orangeColor
import kotlinx.coroutines.delay

/**
 * Types each text out, holds, erases it and loops, with a blinking caret.
 * Pauses while the hero is off screen; reduced motion shows the final text.
 */
@Composable
internal fun TypewriterText(texts: List<String>, fontSize: TextUnit) {
    val tier = LocalMotionTier.current
    val info = LocalScrollInfo.current
    val visible by remember(info) { derivedStateOf { info.scroll.value < info.viewportPx } }
    var shown by remember { mutableStateOf(if (tier.animates) "" else texts.first()) }
    var caretOn by remember { mutableStateOf(true) }

    if (tier.animates) {
        LaunchedEffect(texts, visible) {
            if (!visible) return@LaunchedEffect
            while (true) {
                for (text in texts) {
                    for (i in 1..text.length) {
                        shown = text.take(i)
                        delay(120)
                    }
                    delay(3000)
                    for (i in text.length - 1 downTo 0) {
                        shown = text.take(i)
                        delay(55)
                    }
                    delay(500)
                }
            }
        }
        LaunchedEffect(visible) {
            while (visible) {
                caretOn = !caretOn
                delay(530)
            }
        }
    }

    Text(
        text = buildAnnotatedString {
            append(shown)
            if (tier.animates) {
                withStyle(SpanStyle(color = orangeColor.copy(alpha = if (caretOn) 1f else 0f))) {
                    append(
                        "|"
                    )
                }
            }
        },
        fontSize = fontSize,
        fontWeight = FontWeight.Medium,
        color = orangeColor,
    )
}
