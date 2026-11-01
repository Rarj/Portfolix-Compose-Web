package com.rioarj.portfolix.page.aboutme

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.style.Shapes
import com.rioarj.portfolix.style.Space
import com.rioarj.portfolix.style.hairlineColor
import com.rioarj.portfolix.style.orangeColor
import com.rioarj.portfolix.style.subtitleColor
import com.rioarj.portfolix.style.surfaceColor

internal val skillList = listOf(
    "Kotlin",
    "Jetpack Compose & XML",
    "Android Jetpack",
    "Coroutines & Flow",
    "MVVM",
    "MVP",
    "MVI",
    "Hilt & Dagger 2",
    "Koin",
    "Modularization",
    "ReactiveX",
    "Firebase",
    "RestAPI",
    "GraphQL",
    "Retrofit 2",
    "OkHTTP",
    "Fastlane",
    "JAVA",
    "Ruby",
    "Python",
    "ReactJS",
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun SkillUI(modifier: Modifier = Modifier) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(Space.md)) {
        AboutLabel("Skills")
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(Space.sm),
            verticalArrangement = Arrangement.spacedBy(Space.sm),
        ) {
            skillList.forEach { SkillChip(it) }
        }
    }
}

@Composable
private fun SkillChip(name: String) {
    val interaction = remember { MutableInteractionSource() }
    val hovered by interaction.collectIsHoveredAsState()
    val border by animateColorAsState(if (hovered) orangeColor else hairlineColor)
    val fill by animateColorAsState(if (hovered) orangeColor.copy(alpha = 0.14f) else surfaceColor)
    Text(
        modifier = Modifier
            .hoverable(interaction)
            .clip(Shapes.pill)
            .background(fill)
            .border(1.dp, border, Shapes.pill)
            .padding(horizontal = 14.dp, vertical = 8.dp),
        text = name,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        color = subtitleColor,
    )
}
