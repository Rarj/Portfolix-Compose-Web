package com.rioarj.portfolix.page.resume.experience

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class ExperienceModel(
    val companyName: String,
    val title: String,
    val descriptions: List<String>,
    val achievements: List<String>,
)

private const val FIRST_INDEX = 0

data class Experience(
    val size: Int,
    val experiences: List<ExperienceModel>,
    var selectedIndex: MutableState<Int> = mutableStateOf(FIRST_INDEX),
) {
    val isFirstItem get() = selectedIndex.value == FIRST_INDEX
    val isLastItem get() = selectedIndex.value == experiences.size - 1
    fun getSelectedExperience() = experiences[selectedIndex.value]
    fun nextExperience() {
        selectedIndex.value++
    }

    fun previousExperience() {
        selectedIndex.value--
    }
}