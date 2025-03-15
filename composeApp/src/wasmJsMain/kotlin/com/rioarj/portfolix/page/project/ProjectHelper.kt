package com.rioarj.portfolix.page.project

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import org.jetbrains.compose.resources.DrawableResource
import portfolix.composeapp.generated.resources.Res
import portfolix.composeapp.generated.resources.ic_github

data class ProjectModel(
    val desc: String,
    val title: String,
    val links: List<LinkModel>,
)

data class LinkModel(
    val name: String,
    val link: String,
    val icon: DrawableResource,
)

private const val FIRST_INDEX = 0

data class Project(
    val size: Int,
    val projects: List<ProjectModel>,
    var selectedIndex: MutableState<Int> = mutableStateOf(FIRST_INDEX),
) {
    val isFirstItem get() = selectedIndex.value == FIRST_INDEX
    val isLastItem get() = selectedIndex.value == projects.size - 1
    fun nextProject() {
        selectedIndex.value++
    }

    fun previousProject() {
        selectedIndex.value--
    }
}

internal class ProjectHelper {
    private val _projects = listOf(
        ProjectModel(
            desc = "MoviX is an innovative application designed to enhance your movie browsing experience by providing an intuitive and user-friendly interface. Discover and explore a vast collection of movies effortlessly with MoviX's robust search and detailed information features.",
            title = "MoviX",
            links = listOf(
                LinkModel(
                    name = "Github",
                    link = "https://github.com/Rarj/MoviX",
                    icon = Res.drawable.ic_github,
                ), LinkModel(
                    name = "Playstore",
                    link = "https://play.google.com/store/apps/details?id=com.arj.movix&pli=1",
                    icon = Res.drawable.ic_github,
                )
            ),
        ), ProjectModel(
            desc = "Test 2",
            title = "Test 2",
            links = listOf(
                LinkModel(
                    name = "Github",
                    link = "https://github.com/Rarj/MoviX",
                    icon = Res.drawable.ic_github,
                ), LinkModel(
                    name = "Playstore",
                    link = "https://play.google.com/store/apps/details?id=com.arj.movix&pli=1",
                    icon = Res.drawable.ic_github,
                )
            ),
        ), ProjectModel(
            desc = "Test 3",
            title = "Test 3",
            links = listOf(
                LinkModel(
                    name = "Github",
                    link = "https://github.com/Rarj/MoviX",
                    icon = Res.drawable.ic_github,
                ), LinkModel(
                    name = "Playstore",
                    link = "https://play.google.com/store/apps/details?id=com.arj.movix&pli=1",
                    icon = Res.drawable.ic_github,
                )
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