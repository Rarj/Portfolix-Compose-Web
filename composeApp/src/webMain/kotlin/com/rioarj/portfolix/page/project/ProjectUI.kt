package com.rioarj.portfolix.page.project

import androidx.compose.animation.animateColorAsState
import com.rioarj.portfolix.motion.magnetic
import com.rioarj.portfolix.motion.hoverSpotlight
import com.rioarj.portfolix.motion.Reveal
import com.rioarj.portfolix.motion.Motion
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rioarj.portfolix.component.SectionContainer
import com.rioarj.portfolix.component.SectionHeader
import com.rioarj.portfolix.component.linkClickable
import com.rioarj.portfolix.style.LocalScreenSize
import com.rioarj.portfolix.style.Shapes
import com.rioarj.portfolix.style.Space
import com.rioarj.portfolix.style.hairlineColor
import com.rioarj.portfolix.style.isExpanded
import com.rioarj.portfolix.style.mutedColor
import com.rioarj.portfolix.style.orangeColor
import com.rioarj.portfolix.style.subtitleColor
import com.rioarj.portfolix.style.surfaceColor
import com.rioarj.portfolix.style.surfaceRaisedColor
import com.rioarj.portfolix.style.typeScale
import com.rioarj.portfolix.style.urlColor
import org.jetbrains.compose.resources.painterResource
import portfolix.composeapp.generated.resources.Res
import portfolix.composeapp.generated.resources.ic_link_arrow

private const val MoreProjectsUrl = "https://github.com/Rarj?tab=repositories"

@Composable
internal fun ProjectUI(modifier: Modifier = Modifier) {
    val projects = remember { ProjectHelper().project.value.projects }
    val type = typeScale()

    SectionContainer(modifier) {
        SectionHeader(subtitle = "Done is better than perfect")

        if (LocalScreenSize.current.isExpanded) {
            Row(
                modifier = Modifier.height(IntrinsicSize.Max),
                horizontalArrangement = Arrangement.spacedBy(Space.md),
            ) {
                projects.forEachIndexed { i, p ->
                    Reveal(Modifier.weight(1f).fillMaxHeight(), delayMs = i * 120) {
                        ProjectCard(p, Modifier.fillMaxSize())
                    }
                }
            }
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(Space.md)) {
                projects.forEach { p ->
                    Reveal(Modifier.fillMaxWidth()) { ProjectCard(p, Modifier.fillMaxWidth()) }
                }
            }
        }

        Spacer(Modifier.height(Space.xl))
        val uri = LocalUriHandler.current
        val interaction = remember { MutableInteractionSource() }
        Row(
            modifier = Modifier.magnetic()
                .linkClickable(interaction) { uri.openUri(MoreProjectsUrl) },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "More Projects",
                fontSize = type.h3,
                fontWeight = FontWeight.Medium,
                color = orangeColor,
            )
            Icon(
                modifier = Modifier.padding(start = Space.sm).size(18.dp),
                painter = painterResource(Res.drawable.ic_link_arrow),
                contentDescription = "Go to Github Repositories",
                tint = urlColor,
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ProjectCard(project: ProjectModel, modifier: Modifier = Modifier) {
    val type = typeScale()
    val uri = LocalUriHandler.current
    val interaction = remember { MutableInteractionSource() }
    val hovered by interaction.collectIsHoveredAsState()
    val lift by animateFloatAsState(if (hovered) -6f else 0f)
    val border by animateColorAsState(if (hovered) orangeColor.copy(alpha = 0.6f) else hairlineColor)
    val bg by animateColorAsState(if (hovered) surfaceRaisedColor else surfaceColor)
    val zoom by animateFloatAsState(if (hovered) 1.12f else 1f, tween(Motion.hoverMs * 2))

    Column(
        modifier = modifier
            .graphicsLayer { translationY = lift * density }
            .hoverable(interaction)
            .clip(Shapes.medium)
            .background(bg)
            .hoverSpotlight()
            .border(1.dp, border, Shapes.medium)
            .padding(Space.lg),
        verticalArrangement = Arrangement.spacedBy(Space.sm),
    ) {
        Box(
            modifier = Modifier.size(56.dp).clip(Shapes.small).background(surfaceRaisedColor),
            contentAlignment = Alignment.Center,
        ) {
            if (project.isVectorIcon) {
                Icon(
                    modifier = Modifier.size(28.dp),
                    painter = painterResource(project.projectIcon),
                    contentDescription = "Project Icon",
                    tint = orangeColor,
                )
            } else {
                Image(
                    modifier = Modifier.fillMaxSize().graphicsLayer {
                        scaleX = zoom
                        scaleY = zoom
                    },
                    painter = painterResource(project.projectIcon),
                    contentDescription = "Project Icon",
                    contentScale = ContentScale.Crop,
                )
            }
        }
        Spacer(Modifier.height(Space.xs))
        Text(
            text = project.title,
            fontSize = type.h3,
            fontWeight = FontWeight.Bold,
            color = subtitleColor,
        )
        Text(
            text = project.desc,
            fontSize = type.small,
            lineHeight = type.small * 1.6f,
            fontWeight = FontWeight.Normal,
            color = mutedColor,
        )
        if (project.links.isNotEmpty()) {
            Spacer(Modifier.weight(1f))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(Space.lg),
                verticalArrangement = Arrangement.spacedBy(Space.sm),
            ) {
                project.links.forEach { link -> ProjectLink(link) }
            }
        }
    }
}

@Composable
private fun ProjectLink(link: LinkModel) {
    val type = typeScale()
    val uri = LocalUriHandler.current
    val interaction = remember { MutableInteractionSource() }
    val hovered by interaction.collectIsHoveredAsState()
    val nudge by animateFloatAsState(if (hovered) 3f else 0f, tween(Motion.hoverMs))
    val color by animateColorAsState(if (hovered) orangeColor else subtitleColor)
    Row(
        modifier = Modifier
            .magnetic()
            .linkClickable(interaction) { uri.openUri(link.link) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = link.name,
            fontSize = type.small,
            fontWeight = FontWeight.Medium,
            color = color,
        )
        Icon(
            modifier = Modifier
                .padding(start = 4.dp)
                .size(14.dp)
                .graphicsLayer {
                    translationX = nudge * density
                    translationY = -nudge * density
                },
            painter = painterResource(Res.drawable.ic_link_arrow),
            contentDescription = "Go to ${link.name}",
            tint = urlColor,
        )
    }
}
