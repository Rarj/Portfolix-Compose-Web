package com.rioarj.portfolix.component

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.motion.Reveal
import androidx.compose.ui.unit.dp
import com.rioarj.portfolix.style.MaxContentWidth
import com.rioarj.portfolix.style.LocalScreenSize
import com.rioarj.portfolix.style.NavHeight
import com.rioarj.portfolix.style.Shapes
import com.rioarj.portfolix.style.Space
import com.rioarj.portfolix.style.gutter
import com.rioarj.portfolix.style.orangeColor
import com.rioarj.portfolix.style.subtitleColor
import com.rioarj.portfolix.style.typeScale

/** Centered, max-width column with responsive gutters; leaves room for the sticky nav. */
@Composable
internal fun SectionContainer(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    val size = LocalScreenSize.current
    Box(modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
        Column(
            modifier = Modifier
                .widthIn(max = MaxContentWidth)
                .fillMaxWidth()
                .padding(horizontal = size.gutter)
                .padding(top = NavHeight + Space.xl, bottom = Space.section),
            content = content,
        )
    }
}

/** Accent bar plus the section subtitle, revealed word by word. */
@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun SectionHeader(subtitle: String) {
    val type = typeScale()
    val wordGap = with(LocalDensity.current) { (type.section * 0.28f).toDp() }
    Column {
        Reveal(distance = 12.dp) {
            Box(Modifier.width(40.dp).height(4.dp).clip(Shapes.pill).background(orangeColor))
        }
        Spacer(Modifier.height(Space.md))
        FlowRow(horizontalArrangement = Arrangement.spacedBy(wordGap)) {
            subtitle.split(" ").forEachIndexed { index, word ->
                Reveal(delayMs = index * 50, mask = true) {
                    Text(
                        text = word,
                        color = subtitleColor,
                        fontSize = type.section,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = (-0.5).sp,
                        lineHeight = type.section * 1.2f,
                    )
                }
            }
        }
        Spacer(Modifier.height(Space.xl))
    }
}

/** Hand cursor + hover tracking + click without ripple. */
internal fun Modifier.linkClickable(
    interaction: MutableInteractionSource,
    onClick: () -> Unit,
): Modifier = this
    .hoverable(interaction)
    .pointerHoverIcon(PointerIcon.Hand)
    .clickable(interactionSource = interaction, indication = null, onClick = onClick)
