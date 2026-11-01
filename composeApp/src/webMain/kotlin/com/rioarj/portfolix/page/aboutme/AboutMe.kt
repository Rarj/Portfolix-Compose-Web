package com.rioarj.portfolix.page.aboutme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rioarj.portfolix.component.SectionContainer
import com.rioarj.portfolix.component.WordHighlightText
import com.rioarj.portfolix.motion.Reveal
import com.rioarj.portfolix.component.SectionHeader
import com.rioarj.portfolix.style.LocalScreenSize
import com.rioarj.portfolix.style.Space
import com.rioarj.portfolix.style.isCompact
import com.rioarj.portfolix.style.hairlineColor
import com.rioarj.portfolix.style.typeScale

@Composable
internal fun AboutMeUI(modifier: Modifier = Modifier) {
    val type = typeScale()
    SectionContainer(modifier) {
        SectionHeader(subtitle = "Simplicity is the soul of efficiency")

        // Wide, centered, larger-set paragraph.
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
            WordHighlightText(
                lead = "Hi There! ",
                body = "I’m Rio, an experienced Android Engineer with a diverse background spanning multiple industries. I’m passionate about learning and exploring new technologies to create innovative and impactful solutions." + " Alongside my technical expertise, I’m known for being a friendly team player and a natural cheerleader, always encouraging and supporting those around me." + " I thrive in environments where collaboration is key, and I love motivating my team to push boundaries and achieve meaningful, high-impact outcomes together.",
                fontSize = type.h2 * 0.9f,
                maxWidth = 920.dp,
                lineHeightFactor = 1.5f,
            )
        }

        Spacer(Modifier.height(Space.xxl))
        HorizontalDivider(color = hairlineColor)
        Spacer(Modifier.height(Space.xxl))

        if (LocalScreenSize.current.isCompact) {
            Column(verticalArrangement = Arrangement.spacedBy(Space.xl)) {
                Reveal(Modifier.fillMaxWidth()) { StudyUI(Modifier.fillMaxWidth()) }
                Reveal(Modifier.fillMaxWidth(), delayMs = 100) { SkillUI(Modifier.fillMaxWidth()) }
            }
        } else {
            Row(
                horizontalArrangement = Arrangement.spacedBy(Space.xxl),
                verticalAlignment = Alignment.Top,
            ) {
                Reveal(Modifier.weight(1f)) { StudyUI(Modifier.fillMaxWidth()) }
                Reveal(Modifier.weight(1f), delayMs = 120) { SkillUI(Modifier.fillMaxWidth()) }
            }
        }
    }
}
