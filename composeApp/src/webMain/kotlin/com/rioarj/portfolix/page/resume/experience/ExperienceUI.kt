package com.rioarj.portfolix.page.resume.experience

import androidx.compose.animation.AnimatedVisibility
import com.rioarj.portfolix.style.amberColor
import com.rioarj.portfolix.motion.hoverSpotlight
import com.rioarj.portfolix.motion.animates
import com.rioarj.portfolix.motion.Reveal
import com.rioarj.portfolix.motion.LocalScrollInfo
import com.rioarj.portfolix.motion.LocalMotionTier
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.draw.drawBehind
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.fadeOut
import androidx.compose.animation.fadeIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rioarj.portfolix.style.LocalScreenSize
import com.rioarj.portfolix.style.Shapes
import com.rioarj.portfolix.style.Space
import com.rioarj.portfolix.style.hairlineColor
import com.rioarj.portfolix.style.isCompact
import com.rioarj.portfolix.style.mutedColor
import com.rioarj.portfolix.style.orangeColor
import com.rioarj.portfolix.style.subtitleColor
import com.rioarj.portfolix.style.surfaceColor
import com.rioarj.portfolix.style.typeScale
import com.rioarj.portfolix.style.urlColor
import org.jetbrains.compose.resources.painterResource
import portfolix.composeapp.generated.resources.Res
import portfolix.composeapp.generated.resources.ic_chevron_down
import portfolix.composeapp.generated.resources.ic_link

private val railIdle = Color(0xFF3A3F5C)

/** Vertical timeline; each entry is a card that expands to show its responsibilities and achievements. */
@Composable
internal fun ExperienceUI(modifier: Modifier = Modifier) {
    val experiences = remember { ExperienceHelper().experience.value.experiences }
    Column(modifier) {
        experiences.forEachIndexed { index, experience ->
            TimelineItem(
                experience = experience,
                initiallyExpanded = index == 0,
                isLast = index == experiences.lastIndex,
            )
        }
    }
}

@Composable
private fun TimelineItem(
    experience: ExperienceModel,
    initiallyExpanded: Boolean,
    isLast: Boolean,
) {
    val info = LocalScrollInfo.current
    val animates = LocalMotionTier.current.animates
    var top by remember { mutableIntStateOf(0) }
    var height by remember { mutableIntStateOf(1) }

    // 0..1: how far the viewport's reading line has travelled through this entry.
    fun fill(): Float =
        if (!animates) 1f else ((info.scroll.value + info.viewportPx * 0.45f - top) / height).coerceIn(
            0f,
            1f
        )

    Row(
        Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .onGloballyPositioned {
                top = (it.positionInRoot().y + info.scroll.value).toInt()
                height = it.size.height.coerceAtLeast(1)
            },
    ) {
        Column(
            modifier = Modifier.width(16.dp).fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(26.dp))
            Box(
                Modifier
                    .size(10.dp)
                    .drawBehind { drawCircle(if (fill() > 0f) orangeColor else railIdle) }
            )
            if (!isLast) {
                Box(
                    Modifier
                        .padding(top = Space.sm)
                        .width(2.dp)
                        .weight(1f)
                        .background(hairlineColor)
                        .drawBehind {
                            drawRect(
                                brush = Brush.verticalGradient(listOf(orangeColor, amberColor)),
                                size = Size(size.width, size.height * fill()),
                            )
                        }
                )
            }
        }
        Spacer(Modifier.width(Space.md))
        Reveal(Modifier.weight(1f).padding(bottom = Space.md)) {
            ExperienceCard(
                experience = experience,
                initiallyExpanded = initiallyExpanded,
            )
        }
    }
}

@Composable
private fun ExperienceCard(
    experience: ExperienceModel,
    initiallyExpanded: Boolean,
    modifier: Modifier = Modifier,
) {
    val type = typeScale()
    val pad = if (LocalScreenSize.current.isCompact) Space.md else Space.lg
    var expanded by remember { mutableStateOf(initiallyExpanded) }
    val chevron by animateFloatAsState(if (expanded) 180f else 0f)
    val uri = LocalUriHandler.current

    Column(
        modifier = modifier
            .clip(Shapes.medium)
            .background(surfaceColor)
            .hoverSpotlight()
            .border(1.dp, hairlineColor, Shapes.medium),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .pointerHoverIcon(PointerIcon.Hand)
                .clickable { expanded = !expanded }
                .padding(horizontal = pad, vertical = Space.md),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = experience.companyName,
                    fontSize = type.h3,
                    fontWeight = FontWeight.Bold,
                    color = subtitleColor,
                )
                Spacer(Modifier.height(Space.xs))
                Text(
                    text = buildString {
                        append(experience.title)
                        append(" • ")
                        append(experience.startDate)
                        append("–")
                        append(experience.endDate)
                        append(" • ")
                        append(experience.yearsOfExperience)
                    },
                    fontSize = type.small,
                    fontWeight = FontWeight.Normal,
                    color = mutedColor,
                )
            }
            if (experience.appLinks.isNotEmpty()) {
                IconButton(
                    modifier = Modifier.size(40.dp).pointerHoverIcon(PointerIcon.Hand),
                    onClick = { uri.openUri(experience.appLinks.first()) },
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(Res.drawable.ic_link),
                        contentDescription = "Link Icon to Play Store",
                        tint = urlColor,
                    )
                }
            }
            Icon(
                modifier = Modifier.padding(start = Space.xs).size(24.dp).rotate(chevron),
                painter = painterResource(Res.drawable.ic_chevron_down),
                contentDescription = if (expanded) "Collapse" else "Expand",
                tint = mutedColor,
            )
        }

        AnimatedVisibility(
            visible = expanded,
            enter = expandVertically(
                spring(
                    dampingRatio = 0.85f,
                    stiffness = Spring.StiffnessMediumLow
                )
            ) + fadeIn(),
            exit = shrinkVertically(spring(stiffness = Spring.StiffnessMedium)) + fadeOut(),
        ) {
            Column(Modifier.padding(start = pad, end = pad, bottom = pad)) {
                HorizontalDivider(color = hairlineColor)
                Spacer(Modifier.height(Space.md))
                DetailBlock("Responsibilities", experience.descriptions)
                if (experience.achievements.isNotEmpty()) {
                    Spacer(Modifier.height(Space.md))
                    DetailBlock("Achievements", experience.achievements)
                }
            }
        }
    }
}

@Composable
private fun DetailBlock(label: String, items: List<String>) {
    val type = typeScale()
    Text(
        text = label,
        color = orangeColor,
        fontSize = type.small,
        fontWeight = FontWeight.Bold,
    )
    Spacer(Modifier.height(Space.sm))
    items.forEachIndexed { index, item ->
        Reveal(delayMs = index * 30, distance = 10.dp) {
            Text(
                modifier = Modifier.padding(bottom = 6.dp),
                text = item,
                color = subtitleColor.copy(alpha = 0.88f),
                fontSize = type.small,
                lineHeight = type.small * 1.6f,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}
