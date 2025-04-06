package com.rioarj.portfolix.page.aboutme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.page.resume.education.EducationComponent
import com.rioarj.portfolix.page.resume.education.EducationHelper
import com.rioarj.portfolix.style.orangeColor

@Composable
internal fun RowScope.StudyUI(modifier: Modifier = Modifier) {
    this.apply {
        val educations = EducationHelper.educations
        Column(
            modifier = modifier.padding(top = 24.dp, end = 24.dp)
                .clip(RoundedCornerShape(8.dp)),
        ) {
            Text(
                text = "Study",
                color = orangeColor,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )
            repeat(educations.size) {
                EducationComponent(education = educations[it])
            }
        }
    }
}