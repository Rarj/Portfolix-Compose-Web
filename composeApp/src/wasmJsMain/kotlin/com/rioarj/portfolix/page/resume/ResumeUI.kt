package com.rioarj.portfolix.page.resume

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rioarj.portfolix.component.HeaderPageUI
import com.rioarj.portfolix.page.resume.experience.ExperienceUI

@Composable
internal fun ResumeUI(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.width(1080.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderPageUI(
            title = "Working Experience",
            subtitle = "Strive not to be a success, but rather to be of value.",
        )
        ExperienceUI()
    }
}