package com.rioarj.portfolix.page.resume

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.component.HeaderPageUI
import com.rioarj.portfolix.page.resume.education.EducationComponent
import com.rioarj.portfolix.page.resume.education.EducationHelper
import com.rioarj.portfolix.page.resume.experience.ExperienceUI
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun ResumeUI(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val state = rememberPagerState { 4 }

    val menuInstance = remember { ResumeNavigationState() }
    val menus = remember { menuInstance.getNavigationMenus() }

    Column(
        modifier = modifier.width(1080.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderPageUI(
            title = "Résumé",
            subtitle = "Strive not to be a success, but rather to be of value.",
        )
        Row {
            Column {
                menus.value.forEach { menu ->
                    Row(
                        modifier = Modifier.background(menu.value.backgroundColor()),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            painter = painterResource(menu.value.icon),
                            tint = menu.value.iconColor(),
                            contentDescription = "${menu.value.name} Icon",
                        )
                        Text(
                            modifier = Modifier.clickable {
                                menuInstance.setSelectedMenu(menu.key)
                                menus.value.values.mapIndexed { index, selectedMenu ->
                                    if (menu.value.name.equals(
                                            selectedMenu.name,
                                            ignoreCase = true
                                        )
                                    ) {
                                        coroutineScope.launch { state.animateScrollToPage(index) }
                                    }
                                }
                            },
                            text = menu.value.name,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = menu.value.color(),
                        )
                        Spacer(modifier = Modifier.width(24.dp))
                    }
                }
            }
            VerticalPager(
                state = state,
                modifier = Modifier.fillMaxSize(),
                userScrollEnabled = false,
            ) { page ->
                when (page) {
                    0 -> {
                        val educations = EducationHelper.educations
                        repeat(educations.size) {
                            EducationComponent(education = educations[it])
                        }
                    }
                    1 -> ExperienceUI()
                    2 -> Text("Technology")
                    3 -> Text("Interests")
                }
            }
        }
    }
}