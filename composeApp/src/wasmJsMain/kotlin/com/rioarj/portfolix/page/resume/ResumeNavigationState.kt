package com.rioarj.portfolix.page.resume

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.rioarj.portfolix.style.backgroundColor
import org.jetbrains.compose.resources.DrawableResource
import portfolix.composeapp.generated.resources.Res
import portfolix.composeapp.generated.resources.ic_github

data class ResumeTypeModel(
    val name: String,
    val icon: DrawableResource,
    val isSelected: Boolean = false,
) {
    fun color() = if (isSelected) Color.White else Color.Black
    fun backgroundColor() = if (isSelected) backgroundColor else Color.White
    fun iconColor() = if (isSelected) Color.White else Color.Black
}

internal class ResumeNavigationState {
    private val menus = mutableStateOf(
        mapOf(
            "education" to ResumeTypeModel(
                name = "Education",
                icon = Res.drawable.ic_github,
                isSelected = true
            ),
            "work_experience" to ResumeTypeModel(
                name = "Work Experience",
                icon = Res.drawable.ic_github
            ),
            "tech_stack" to ResumeTypeModel(name = "Technology", icon = Res.drawable.ic_github),
            "interest" to ResumeTypeModel(name = "Interest", icon = Res.drawable.ic_github),
        )
    )

    fun getNavigationMenus() = menus

    fun setSelectedMenu(key: String) {
        menus.value = menus.value.mapValues {
            if (it.key == key) {
                it.value.copy(isSelected = true)
            } else {
                it.value.copy(isSelected = false)
            }
        }
    }
}