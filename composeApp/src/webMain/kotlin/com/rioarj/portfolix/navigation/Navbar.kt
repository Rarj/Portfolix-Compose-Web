package com.rioarj.portfolix.navigation

import androidx.compose.animation.AnimatedVisibility
import com.rioarj.portfolix.motion.animates
import com.rioarj.portfolix.motion.LocalMotionTier
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.draw.drawBehind
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.component.linkClickable
import com.rioarj.portfolix.style.LocalScreenSize
import com.rioarj.portfolix.style.MaxContentWidth
import com.rioarj.portfolix.style.NavHeight
import com.rioarj.portfolix.style.Shapes
import com.rioarj.portfolix.style.backgroundColor
import com.rioarj.portfolix.style.gutter
import com.rioarj.portfolix.style.hairlineColor
import com.rioarj.portfolix.style.isCompact
import com.rioarj.portfolix.style.mutedColor
import com.rioarj.portfolix.style.orangeColor
import com.rioarj.portfolix.style.subtitleColor
import org.jetbrains.compose.resources.painterResource
import portfolix.composeapp.generated.resources.Res
import portfolix.composeapp.generated.resources.ic_close
import portfolix.composeapp.generated.resources.ic_menu

internal val NavItems = listOf("Home", "About Me", "Working Experience", "Projects")

/** Sticky top bar. [selected] is driven by scroll position; [onSelect] scrolls to a section. */
@Composable
internal fun Navbar(
    selected: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val size = LocalScreenSize.current
    var menuOpen by remember { mutableStateOf(false) }

    Column(modifier.fillMaxWidth().background(backgroundColor.copy(alpha = 0.9f))) {
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Row(
                modifier = Modifier
                    .widthIn(max = MaxContentWidth)
                    .fillMaxWidth()
                    .height(NavHeight)
                    .padding(horizontal = size.gutter),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Wordmark(onClick = { menuOpen = false; onSelect(0) })
                if (size.isCompact) {
                    IconButton(onClick = { menuOpen = !menuOpen }) {
                        Icon(
                            painter = painterResource(if (menuOpen) Res.drawable.ic_close else Res.drawable.ic_menu),
                            contentDescription = if (menuOpen) "Close menu" else "Open menu",
                            tint = subtitleColor,
                        )
                    }
                } else {
                    NavLinks(selected, onSelect)
                }
            }
        }

        AnimatedVisibility(
            visible = size.isCompact && menuOpen,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically(),
        ) {
            Column(
                Modifier.fillMaxWidth().padding(horizontal = size.gutter).padding(bottom = 12.dp)
            ) {
                NavItems.forEachIndexed { index, name ->
                    val active = index == selected
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(Shapes.small)
                            .linkClickable(remember { MutableInteractionSource() }) {
                                menuOpen = false
                                onSelect(index)
                            }
                            .padding(vertical = 14.dp),
                        text = name,
                        fontSize = 18.sp,
                        fontWeight = if (active) FontWeight.Bold else FontWeight.Medium,
                        color = if (active) orangeColor else mutedColor,
                    )
                }
            }
        }
        Box(Modifier.fillMaxWidth().height(1.dp).background(hairlineColor))
    }
}

@Composable
private fun Wordmark(onClick: () -> Unit) {
    Text(
        modifier = Modifier.linkClickable(remember { MutableInteractionSource() }, onClick),
        text = buildAnnotatedString {
            withStyle(SpanStyle(color = orangeColor)) { append("[") }
            append("PortfoliX")
            withStyle(SpanStyle(color = orangeColor)) { append("]") }
        },
        fontSize = 22.sp,
        fontWeight = FontWeight.ExtraBold,
        color = subtitleColor,
    )
}

@Composable
private fun NavLinks(selected: Int, onSelect: (Int) -> Unit) {
    val tier = LocalMotionTier.current
    // x / width of each link inside the row, used to slide a single pill between them.
    val bounds = remember { mutableStateMapOf<Int, Pair<Int, Int>>() }
    val target = bounds[selected]
    val spec: AnimationSpec<Float> =
        if (tier.animates) spring(
            dampingRatio = 0.8f,
            stiffness = Spring.StiffnessMediumLow
        ) else snap()
    val pillX by animateFloatAsState(target?.first?.toFloat() ?: 0f, spec, label = "pillX")
    val pillW by animateFloatAsState(target?.second?.toFloat() ?: 0f, spec, label = "pillW")

    Row(
        modifier = Modifier.drawBehind {
            if (pillW > 0f) {
                drawRoundRect(
                    color = orangeColor.copy(alpha = 0.14f),
                    topLeft = Offset(pillX, 0f),
                    size = Size(pillW, size.height),
                    cornerRadius = CornerRadius(size.height / 2f),
                )
            }
        },
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NavItems.forEachIndexed { index, name ->
            val color by animateColorAsState(if (index == selected) orangeColor else mutedColor)
            Text(
                modifier = Modifier
                    .onGloballyPositioned {
                        bounds[index] = it.positionInParent().x.toInt() to it.size.width
                    }
                    .clip(Shapes.pill)
                    .linkClickable(remember { MutableInteractionSource() }) { onSelect(index) }
                    .padding(horizontal = 16.dp, vertical = 9.dp),
                text = name,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = color,
            )
        }
    }
}
