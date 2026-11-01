package com.rioarj.portfolix.page.resume

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rioarj.portfolix.component.SectionContainer
import com.rioarj.portfolix.component.SectionHeader
import com.rioarj.portfolix.page.resume.experience.ExperienceUI

@Composable
internal fun ResumeUI(modifier: Modifier = Modifier) {
    SectionContainer(modifier) {
        SectionHeader(subtitle = "Strive not to be a success, but rather to be of value.")
        ExperienceUI()
    }
}
