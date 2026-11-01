package com.rioarj.portfolix.page.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.component.Marquee
import com.rioarj.portfolix.component.SocialLinks
import com.rioarj.portfolix.component.TypewriterText
import com.rioarj.portfolix.motion.LocalMotionTier
import com.rioarj.portfolix.motion.LocalScrollInfo
import com.rioarj.portfolix.motion.Reveal
import com.rioarj.portfolix.motion.isFull
import com.rioarj.portfolix.page.aboutme.skillList
import com.rioarj.portfolix.style.LocalScreenSize
import com.rioarj.portfolix.style.MaxContentWidth
import com.rioarj.portfolix.style.NavHeight
import com.rioarj.portfolix.style.ScreenSize
import com.rioarj.portfolix.style.Space
import com.rioarj.portfolix.style.gutter
import com.rioarj.portfolix.style.isCompact
import com.rioarj.portfolix.style.mutedColor
import com.rioarj.portfolix.style.orangeColor
import com.rioarj.portfolix.style.subtitleColor
import com.rioarj.portfolix.style.typeScale
import org.jetbrains.compose.resources.painterResource
import portfolix.composeapp.generated.resources.Res
import portfolix.composeapp.generated.resources.profile

private val MarqueeHeight = 150.dp

@Composable
internal fun HomeUI(minHeight: Dp, modifier: Modifier = Modifier) {
    val size = LocalScreenSize.current
    val tier = LocalMotionTier.current
    val info = LocalScrollInfo.current
    val showMarquee = tier.isFull && !size.isCompact
    val avatar = when (size) {
        ScreenSize.Expanded -> 300.dp
        ScreenSize.Medium -> 240.dp
        ScreenSize.Compact -> 170.dp
    }

    Column(modifier.fillMaxWidth()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(
                    min = (if (showMarquee) minHeight - MarqueeHeight else minHeight).coerceAtLeast(
                        0.dp
                    )
                )
                .drawBehind {
                    // Static glow; drifts slowly with scroll on the Full tier.
                    val drift = if (tier.isFull) info.scroll.value * 0.3f else 0f
                    drawRect(
                        brush = Brush.radialGradient(
                            colors = listOf(orangeColor.copy(alpha = 0.18f), Color.Transparent),
                            center = Offset(
                                this.size.width * 0.68f,
                                this.size.height * 0.4f + drift
                            ),
                            radius = this.size.minDimension * 0.8f,
                        )
                    )
                },
            contentAlignment = Alignment.Center,
        ) {
            Box(
                Modifier
                    .widthIn(max = MaxContentWidth)
                    .fillMaxWidth()
                    .padding(horizontal = size.gutter)
                    .padding(top = NavHeight + Space.md, bottom = Space.xl),
            ) {
                if (size.isCompact) {
                    Column {
                        Avatar(avatar)
                        Spacer(Modifier.height(Space.lg))
                        HeroText()
                    }
                } else {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(Space.xl),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(Modifier.weight(1f)) { HeroText() }
                        Avatar(avatar)
                    }
                }
            }
        }

        if (showMarquee) {
            Column(Modifier.height(MarqueeHeight)) {
                Marquee(skillList, subtitleColor.copy(alpha = 0.5f), reverse = false)
                Marquee(skillList, orangeColor.copy(alpha = 0.6f), reverse = true)
            }
        }
    }
}

@Composable
private fun HeroText() {
    val type = typeScale()
    Column(horizontalAlignment = Alignment.Start) {
        Reveal(delayMs = 100, mask = true) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Hello, I'm ",
                    color = subtitleColor,
                    fontSize = type.heroName,
                    fontWeight = FontWeight.Medium,
                )
                TypewriterText(listOf("Rio Arjuna"), fontSize = type.heroName)
            }
        }
        Spacer(Modifier.height(Space.sm))
        Reveal(delayMs = 220, mask = true) {
            Text(
                text = buildAnnotatedString {
                    append("Sr. Android ")
                    withStyle(SpanStyle(color = orangeColor)) { append("Engineer") }
                },
                color = subtitleColor,
                fontSize = type.hero,
                fontWeight = FontWeight.ExtraBold,
                lineHeight = type.hero * 1.05f,
                letterSpacing = (-2).sp,
            )
        }
        Spacer(Modifier.height(Space.md))
        Reveal(delayMs = 380, mask = true) {
            Text(
                modifier = Modifier.widthIn(max = 520.dp),
                text = "Innovation through collaboration, impact through technology",
                color = mutedColor,
                fontSize = type.tagline,
                lineHeight = type.tagline * 1.6f,
                fontWeight = FontWeight.Normal,
            )
        }
        Spacer(Modifier.height(Space.lg))
        Reveal(delayMs = 520) { SocialLinks() }
    }
}

@Composable
private fun Avatar(size: Dp) {
    val tier = LocalMotionTier.current
    val info = LocalScrollInfo.current
    Reveal(delayMs = 250, distance = 40.dp) {
        Box(
            modifier = Modifier
                .size(size + 12.dp)
                .graphicsLayer { if (tier.isFull) translationY = info.scroll.value * 0.12f }
                .border(
                    width = 2.dp,
                    brush = Brush.linearGradient(
                        listOf(
                            orangeColor,
                            orangeColor.copy(alpha = 0.05f)
                        )
                    ),
                    shape = CircleShape,
                )
                .padding(6.dp),
        ) {
            Image(
                modifier = Modifier.fillMaxSize().clip(CircleShape),
                painter = painterResource(Res.drawable.profile),
                contentDescription = "Image Profile",
                contentScale = ContentScale.Crop,
            )
        }
    }
}
