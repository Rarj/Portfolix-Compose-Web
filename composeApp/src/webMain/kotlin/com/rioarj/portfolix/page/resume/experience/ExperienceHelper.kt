package com.rioarj.portfolix.page.resume.experience

import androidx.compose.runtime.mutableStateOf

internal class ExperienceHelper {
    private val _experiences = listOf(
        ExperienceModel(
            companyName = "PT Prodia Widyahusada Tbk - U by Prodia",
            title = "Sr. Android Engineer",
            startDate = "Oct 2024",
            endDate = "Mar 2025",
            yearsOfExperience = "6mos",
            descriptions = listOf(
                "• crafting Chronic Disease Management feature depend on PRD and TSD",
                "• write a scalable and maintainable code with Kotlin and other stack such as Hilt and Jetpack libraries",
                "• crafting the UI using Jetpack Compose",
                "• code reviewing other member’s code before merge into master branch",
                "• working closely with cross functional team such as iOS, Backend, QA, UI/UX and Product team",
                "• do pairing to develop, sync and solve conflict between tasks with Android Lead to keep uptodate with latest code",
            ),
            achievements = emptyList(),
            appLinks = listOf(
                "https://play.google.com/store/apps/details?id=com.prodiadigital.patient&hl=id",
            ),
        ),
        ExperienceModel(
            companyName = "PT KB Finansia Multi Finance - Kreditplus",
            title = "Sr. Android Engineer",
            startDate = "Feb 2023",
            endDate = "Jun 2024",
            yearsOfExperience = "1y 5mos",
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
            startDate = "Sept 2021",
            endDate = "Dec 2022",
            yearsOfExperience = "1yr 4mos",
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
                "https://play.google.com/store/apps/details?id=com.app.icapp&hl=id",
            ),
        ),
        ExperienceModel(
            companyName = "PT Bank Mandiri (Persero) Tbk",
            title = "Android Engineer",
            startDate = "May 2021",
            endDate = "Aug 2021",
            yearsOfExperience = "4mos",
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
            startDate = "Jul 2019",
            endDate = "May 2021",
            yearsOfExperience = "1yr 11mos",
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
            ),
        ),
        ExperienceModel(
            companyName = "PT Solusi Teknologi Niaga - qasir.id",
            title = "Jr. Android Engineer",
            startDate = "Nov 2018",
            endDate = "Jun 2019",
            yearsOfExperience = "8mos",
            descriptions = listOf(
                "• develop POS features and some features of Mitra apps with other development team",
                "• translate design into good quality code and pixel perfect",
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