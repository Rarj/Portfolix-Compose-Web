package com.rioarj.portfolix.page.resume.experience

import androidx.compose.runtime.mutableStateOf

internal class ExperienceHelper {
    private val _experiences = listOf(
        ExperienceModel(
            companyName = "PT. KB Finansia Multi Finance - Kreditplus",
            title = "Sr. Android Engineer",
            startDate = "2023-02",
            endDate = "2024-06",
            descriptions = listOf(
                "• build and maintenance features depend on PRD",
                "• write a scalable and maintanable code with Kotlin and other stack such as Koin and Jetpack libraries",
                "• crafting Design System with Compose",
                "• code reviewing other member’s code before merge into master branch",
                "• working closely with cross functional team such as iOS, Backend, QA, UI/UX and Product team",
                "• involved with Chapter Lead to interview candidate especially in Android team",
            ),
            achievements = listOf(
                "• create a workflow (engineer productivity) to save a hundred step and hours to build, sign and upload an AAB to PlayConsole through Github Action using Fastlane to simplify monthly release cycle",
                "• become a coordinator on NWG line of bizniz to ensure resources such as technical discussions, API documentations, UI, PRD and timeline is align before going to development phase",
                "• backing up a Chapter Lead for 3months to helping 4 other members such as breakdown and estimate the task, pairing, negotiate to product team if necessary",
            ),
            appLinks = listOf(
                "https://play.google.com/store/apps/details?id=com.kreditplus.kpm.android",
            ),
        ),
        ExperienceModel(
            companyName = "PT Dynamo Media Network - kumparan.com",
            title = "Android Engineer",
            startDate = "2021-09",
            endDate = "2022-12",
            descriptions = listOf(
                "• build UI Kit from scratch to boost team productivity while development and maintenance phase",
                "• build features depend on PRD and create TRD for specific case",
                "• write a clean code with Kotlin and other stack such as Hilt and Jetpack libraries",
                "• code reviewing other member’s code before merge into master branch",
                "• preparing binary for biweekly release cycle if any updates from apps",
                "• do research and pair programming if necessary",
                "• working closely with cross functional team such as iOS, WWW, Backend, QA and Product team",
            ),
            achievements = listOf(
                "• release rewrite apps and reduce bugs on the production and increase crash-free into 98%",
                "• become mentor of Kumparan Academy Internship program",
            ),
            appLinks = listOf(
                "https://play.google.com/store/apps/details?id=com.kreditplus.kpm.android",
            ),
        ),
        ExperienceModel(
            companyName = "PT Bank Mandiri (Persero) Tbk",
            title = "Android Engineer",
            startDate = "2021-05",
            endDate = "2021-08",
            descriptions = listOf(
                "• develop RMT-Sales Tools with JAVA and Kotlin",
                "• collaborate with cross functional team such as System Analyst, QA, PM and Backend",
                "• fixing bugs and develop features",
            ),
            achievements = emptyList(),
            appLinks = emptyList(),
        ),
        ExperienceModel(
            companyName = "PT Cipta Piranti Sejahtera - accurate.id",
            title = "Android Engineer",
            startDate = "2019-07",
            endDate = "2021-05",
            descriptions = listOf(
                "• develop POS features",
                "• accurate Lite revamp: v2",
                "• collaborate with design team to build design system. Include custom component UI, write documentation and also maintenance library who use in apps development process so that both developer and designer can reused it. It will speedup development process",
                "• create code convention and write docs with developer team so that we develop apps with same code style",
                "• translate design into efficient code and make it pixel perfect",
            ),
            achievements = emptyList(),
            appLinks = listOf(
                "https://play.google.com/store/apps/details?id=com.cpssoft.mobile.alpha",
                "https://play.google.com/store/apps/details?id=com.cpssoft.mobile.titan",
            ),
        ),
        ExperienceModel(
            companyName = "PT Solusi Teknologi Niaga - qasir.id",
            title = "Jr. Android Engineer",
            startDate = "2018-11",
            endDate = "2019-06",
            descriptions = listOf(
                "• Develop POS features and some features of Mitra apps with other development team",
                "• Translate design into good quality code and pixel perfect",
            ),
            achievements = emptyList(),
            appLinks = listOf(
                "https://play.google.com/store/apps/details?id=com.innovecto.etalastic",
            ),
        ),
    )

    val experience = mutableStateOf(
        Experience(
            size = _experiences.size,
            experiences = _experiences,
        )
    )
}