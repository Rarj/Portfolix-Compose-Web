package com.rioarj.portfolix.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.rioarj.portfolix.motion.LocalScrollInfo
import com.rioarj.portfolix.style.amberColor
import com.rioarj.portfolix.style.orangeColor

/** 2dp gradient bar showing page progress; read in the draw phase only. */
@Composable
internal fun ScrollProgressBar(modifier: Modifier = Modifier) {
    val scroll = LocalScrollInfo.current.scroll
    Box(
        modifier
            .fillMaxWidth()
            .height(2.dp)
            .drawBehind {
                val max = scroll.maxValue
                if (max > 0) {
                    drawRect(
                        brush = Brush.horizontalGradient(listOf(orangeColor, amberColor)),
                        size = Size(size.width * (scroll.value / max.toFloat()), size.height),
                    )
                }
            }
    )
}
