package com.rioarj.portfolix.page.resume.experience

import androidx.compose.runtime.mutableStateOf

internal class ExperienceHelper {
    private val _experiences = listOf(
        ExperienceModel(
            companyName = "PT Prodia Widyahusada Tbk.",
            title = "Sr. Android Engineer",
            descriptions = listOf(
                "Crafting new feature",
                "Monitoring crash rate on Crashlytics",
                "Mentoring Jr. Engineer",
            ),
            achievements = listOf(
                "Crafting CICD",
                "Creating new reusable component",
            )
        )
    )

    val experience = mutableStateOf(
        Experience(
            size = _experiences.size,
            experiences = _experiences,
        )
    )
}