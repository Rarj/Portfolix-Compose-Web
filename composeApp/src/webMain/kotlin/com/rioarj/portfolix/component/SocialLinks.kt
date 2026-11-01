package com.rioarj.portfolix.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BasicTooltipBox
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberBasicTooltipState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.motion.Motion
import com.rioarj.portfolix.motion.magnetic
import com.rioarj.portfolix.page.home.HomeHelper
import com.rioarj.portfolix.style.Shapes
import com.rioarj.portfolix.style.Space
import com.rioarj.portfolix.style.orangeColor
import com.rioarj.portfolix.style.subtitleColor
import com.rioarj.portfolix.style.surfaceRaisedColor
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun SocialLinks(modifier: Modifier = Modifier) {
    val uri = LocalUriHandler.current
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(Space.sm),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        HomeHelper.portfolioPlatform.forEach { model ->
            BasicTooltipBox(
                positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                tooltip = {
                    Text(
                        modifier = Modifier
                            .clip(Shapes.small)
                            .background(surfaceRaisedColor)
                            .padding(horizontal = 10.dp, vertical = 6.dp),
                        text = model.name,
                        color = subtitleColor,
                        fontSize = 12.sp,
                    )
                },
                state = rememberBasicTooltipState(isPersistent = false),
            ) {
                val interaction = remember { MutableInteractionSource() }
                val hovered by interaction.collectIsHoveredAsState()
                val tint by animateColorAsState(if (hovered) orangeColor else subtitleColor)
                val bg by animateColorAsState(if (hovered) surfaceRaisedColor else Color.Transparent)
                val scale by animateFloatAsState(if (hovered) 1.12f else 1f, tween(Motion.hoverMs))
                Box(
                    modifier = Modifier
                        .magnetic()
                        .graphicsLayer {
                            scaleX = scale
                            scaleY = scale
                        }
                        .size(44.dp)
                        .clip(CircleShape)
                        .background(bg)
                        .linkClickable(interaction) { uri.openUri(model.url) },
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        modifier = Modifier.size(22.dp),
                        painter = painterResource(model.icon),
                        contentDescription = model.name,
                        tint = tint,
                    )
                }
            }
        }
    }
}
