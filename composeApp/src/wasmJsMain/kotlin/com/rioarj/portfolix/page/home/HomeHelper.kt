package com.rioarj.portfolix.page.home

import org.jetbrains.compose.resources.DrawableResource
import portfolix.composeapp.generated.resources.Res
import portfolix.composeapp.generated.resources.ic_github
import portfolix.composeapp.generated.resources.ic_instagram
import portfolix.composeapp.generated.resources.ic_linkedin
import portfolix.composeapp.generated.resources.ic_medium
import portfolix.composeapp.generated.resources.ic_storage

data class PlatformModel(
    val icon: DrawableResource,
    val name: String,
    val url: String,
)

internal object HomeHelper {
    private val _portfolioByPlatforms = listOf(
        PlatformModel(
            icon = Res.drawable.ic_github,
            name = "Github",
            url = "https://github.com/Rarj"
        ),
        PlatformModel(
            icon = Res.drawable.ic_linkedin,
            name = "LinkedIn",
            url = "https://linkedin.com/in/ri0arjuna"
        ),
        PlatformModel(
            icon = Res.drawable.ic_instagram,
            name = "Instagram",
            url = "https://www.instagram.com/rio.arj"
        ),
        PlatformModel(
            icon = Res.drawable.ic_medium,
            name = "Medium",
            url = "https://ri0arjuna.medium.com/"
        ),
        PlatformModel(
            icon = Res.drawable.ic_storage,
            name = "Sonatype - Maven Central",
            url = "https://central.sonatype.com/artifact/com.rioarj.labs/cardx"
        ),
    )
    val portfolioPlatform get() = _portfolioByPlatforms
}