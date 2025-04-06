package com.rioarj.portfolix.page.resume

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rioarj.portfolix.component.HeaderPageUI
import com.rioarj.portfolix.page.resume.experience.ExperienceUI
import com.rioarj.portfolix.style.backgroundColor

@Composable
internal fun ResumeUI(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderPageUI(
            title = "Working Experience",
            subtitle = "Strive not to be a success, but rather to be of value.",
        )
        ExperienceUI()
    }
}