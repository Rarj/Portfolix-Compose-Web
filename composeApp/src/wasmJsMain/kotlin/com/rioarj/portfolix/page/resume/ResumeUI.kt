package com.rioarj.portfolix.page.resume

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row {
                menus.value.forEach { menu ->
                    val tabSelectionColor = menu.value.selectionColor()
                    Column(
                        modifier = Modifier.padding(8.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        IconButton(
                            onClick = {
                                menuInstance.setSelectedMenu(menu.key)
                                menus.value.values.mapIndexed { index, selectedMenu ->
                                    if (menu.value.name.equals(
                                            selectedMenu.name, ignoreCase = true
                                        )
                                    ) {
                                        coroutineScope.launch { state.animateScrollToPage(index) }
                                    }
                                }
                            },
                            content = {
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    painter = painterResource(menu.value.icon),
                                    tint = tabSelectionColor,
                                    contentDescription = "${menu.value.name} Icon",
                                )
                            },
                        )
                        HorizontalDivider(
                            modifier = Modifier.width(50.dp).clip(
                                RoundedCornerShape(
                                    topStartPercent = 50,
                                    topEndPercent = 50,
                                )
                            ),
                            thickness = 6.dp,
                            color = tabSelectionColor,
                        )
                    }
                }
            }
            HorizontalPager(
                state = state,
                modifier = Modifier.fillMaxHeight(),
                userScrollEnabled = false,
            ) { page ->
                when (page) {
                    0 -> ExperienceUI()
                    1 -> {
                        val educations = EducationHelper.educations
                        Column(
                            modifier = Modifier.fillMaxSize().padding(all = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            repeat(educations.size) {
                                EducationComponent(education = educations[it])
                            }
                        }
                    }

                    2 -> Text("Technology")
                    3 -> Text("Interests")
                }
            }
        }
    }
}