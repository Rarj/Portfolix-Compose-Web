package com.rioarj.portfolix.toolbar

import androidx.compose.runtime.mutableStateOf
import com.rioarj.portfolix.style.notSelectedColor
import com.rioarj.portfolix.style.selectedColor

data class Menu(
    val name: String,
    val isSelected: Boolean,
) {
    fun color() = if (isSelected) selectedColor else notSelectedColor
}

class NavigationState {
    private val menus = mutableStateOf(mapOf(
        "home" to Menu(name = "Home", isSelected = true),
        "about_me" to Menu(name = "About Me", isSelected = false),
        "resume" to Menu(name = "Working Experience", isSelected = false),
        "projects" to Menu(name = "Projects", isSelected = false),
    ))

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