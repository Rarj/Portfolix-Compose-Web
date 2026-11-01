package com.rioarj.portfolix.page.resume

import androidx.compose.runtime.mutableStateOf
import com.rioarj.portfolix.style.notSelectedColor
import com.rioarj.portfolix.style.selectedColor
import org.jetbrains.compose.resources.DrawableResource
import portfolix.composeapp.generated.resources.Res
import portfolix.composeapp.generated.resources.ic_confetti
import portfolix.composeapp.generated.resources.ic_technology
import portfolix.composeapp.generated.resources.ic_university
import portfolix.composeapp.generated.resources.ic_work

data class ResumeTypeModel(
    val name: String,
    val icon: DrawableResource,
    val isSelected: Boolean = false,
) {
    fun selectionColor() = if (isSelected) selectedColor else notSelectedColor
}

internal class ResumeNavigationState {
    private val menus = mutableStateOf(
        mapOf(
            "work_experience" to ResumeTypeModel(
                name = "Experience",
                icon = Res.drawable.ic_work,
                isSelected = true,
            ),
            "education" to ResumeTypeModel(
                name = "Education",
                icon = Res.drawable.ic_university,
            ),
            "tech_stack" to ResumeTypeModel(
                name = "Technology",
                icon = Res.drawable.ic_technology,
            ),
            "interest" to ResumeTypeModel(
                name = "Interest",
                icon = Res.drawable.ic_confetti,
            ),
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