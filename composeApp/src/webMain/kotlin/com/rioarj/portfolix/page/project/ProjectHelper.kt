package com.rioarj.portfolix.page.project

import androidx.compose.runtime.mutableStateOf
import org.jetbrains.compose.resources.DrawableResource
import portfolix.composeapp.generated.resources.Res
import portfolix.composeapp.generated.resources.ic_github
import portfolix.composeapp.generated.resources.ic_link_arrow
import portfolix.composeapp.generated.resources.project_movix

data class ProjectModel(
    val projectIcon: DrawableResource,
    val desc: String,
    val title: String,
    val links: List<LinkModel>,
)

data class LinkModel(
    val name: String,
    val link: String,
)

data class Project(
    val size: Int,
    val projects: List<ProjectModel>,
)

internal class ProjectHelper {
    private val _projects = listOf(
        ProjectModel(
            projectIcon = Res.drawable.project_movix,
            desc = "MoviX makes movie browsing simple and fun with an easy-to-use interface and powerful search—powered by the TMDB API for rich movie details.",
            title = "MoviX",
            links = listOf(
                LinkModel(
                    name = "Github",
                    link = "https://github.com/Rarj/MoviX",
                ), LinkModel(
                    name = "Playstore",
                    link = "https://play.google.com/store/apps/details?id=com.arj.movix",
                )
            ),
        ), ProjectModel(
            projectIcon = Res.drawable.ic_github,
            desc = "Kotlin library that lets you easily preview debit cards in your Android app using Jetpack Compose. It comes with smooth flip animations to bring your card designs to life and make the user experience more fun and interactive.",
            title = "CardX",
            links = listOf(
                LinkModel(
                    name = "Sonatype",
                    link = "https://central.sonatype.com/artifact/com.rioarj.labs/cardx",
                )
            ),
        ), ProjectModel(
            projectIcon = Res.drawable.ic_link_arrow,
            desc = "This site is a quick peek into my work experience and a little about who I am. Think of it as my digital handshake—nice to meet you!",
            title = "PortfoliX",
            links = listOf(
                LinkModel(
                    name = "Github",
                    link = "https://github.com/Rarj/Portfolix-Compose-Web",
                ),
            ),
        )
    )

    val project = mutableStateOf(
        Project(
            size = _projects.size,
            projects = _projects,
        )
    )
}