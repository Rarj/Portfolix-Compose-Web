package com.rioarj.portfolix.page.resume.education

data class EducationModel(
    val name: String,
    val degree: String,
    val date: String,
)

object EducationHelper {
    private val _educations = listOf(
        EducationModel(
            name = "Budi Luhur University",
            degree = "Master of Computer Science",
            date = "2024–current"
        ),
        EducationModel(
            name = "Syekh Yusuf of Islam University",
            degree = "Bachelor of Computer Science",
            date = "2014–2018"
        ),
    )
    val educations get() = _educations
}