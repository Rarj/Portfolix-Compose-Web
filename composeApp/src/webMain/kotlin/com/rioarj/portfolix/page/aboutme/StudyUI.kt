package com.rioarj.portfolix.page.aboutme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rioarj.portfolix.page.resume.education.EducationComponent
import com.rioarj.portfolix.page.resume.education.EducationHelper
import com.rioarj.portfolix.style.Space

@Composable
internal fun StudyUI(modifier: Modifier = Modifier) {
    Column(modifier, verticalArrangement = Arrangement.spacedBy(Space.md)) {
        AboutLabel("Study")
        EducationHelper.educations.forEach { EducationComponent(education = it) }
    }
}
