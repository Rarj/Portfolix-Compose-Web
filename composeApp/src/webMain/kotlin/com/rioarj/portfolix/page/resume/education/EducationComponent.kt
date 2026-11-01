package com.rioarj.portfolix.page.resume.education

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rioarj.portfolix.style.Shapes
import com.rioarj.portfolix.style.Space
import com.rioarj.portfolix.style.mutedColor
import com.rioarj.portfolix.style.orangeColor
import com.rioarj.portfolix.style.subtitleColor
import com.rioarj.portfolix.style.typeScale

@Composable
internal fun EducationComponent(
    modifier: Modifier = Modifier,
    education: EducationModel,
) {
    val type = typeScale()
    Row(modifier.height(IntrinsicSize.Min)) {
        Box(Modifier.width(3.dp).fillMaxHeight().clip(Shapes.pill).background(orangeColor))
        Column(Modifier.padding(start = Space.md)) {
            Text(
                text = education.name,
                fontSize = type.body,
                fontWeight = FontWeight.SemiBold,
                color = subtitleColor,
            )
            Text(
                text = buildString {
                    append(education.degree)
                    append(" • ")
                    append(education.date)
                },
                fontSize = type.small,
                fontWeight = FontWeight.Normal,
                color = mutedColor,
            )
        }
    }
}
