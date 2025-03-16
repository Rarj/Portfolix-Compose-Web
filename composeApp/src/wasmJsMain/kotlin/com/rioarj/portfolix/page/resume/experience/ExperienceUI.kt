package com.rioarj.portfolix.page.resume.experience

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier

@Composable
internal fun ExperienceUI(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val experienceState = remember { ExperienceHelper().experience }
    val selectedExperience by remember { derivedStateOf { experienceState.value.getSelectedExperience() } }
    val state = rememberPagerState { experienceState.value.size }
    val enabledPreviousButton =
        remember { derivedStateOf { experienceState.value.isFirstItem.not() } }
    val enabledNextButton = remember { derivedStateOf { experienceState.value.isLastItem.not() } }

//    Column(
//        modifier = modifier,
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ) {
//        Row(
//            modifier = Modifier.fillMaxWidth().padding(bottom = 64.dp).padding(horizontal = 64.dp),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically,
//        ) {
//            IconButton(
//                enabled = enabledPreviousButton.value,
//                onClick = {
//                    coroutineScope.launch {
//                        projectState.value.previousProject()
//                        state.animateScrollToPage(projectState.value.selectedIndex.value)
//                    }
//                },
//                content = {
//                    Icon(
//                        painter = painterResource(Res.drawable.ic_medium),
//                        contentDescription = "Previous Button"
//                    )
//                },
//            )
//
//            HorizontalPager(
//                state = state,
//                modifier = Modifier.width(700.dp),
//                pageSpacing = 8.dp,
//                contentPadding = PaddingValues(24.dp),
//                userScrollEnabled = false,
//            ) {
//                Card(modifier = Modifier.fillMaxSize()) {
//                    Column {
//                        Text(
//                            text = selectedProject.title,
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Medium,
//                            color = Color.White,
//                        )
//                        Row {
//                            selectedProject.links.forEach { linkModel ->
//                                Image(painter = painterResource(linkModel.icon), linkModel.name)
//                                Spacer(modifier = Modifier.size(4.dp))
//                            }
//                        }
//                    }
//                }
//            }
//
//            IconButton(
//                enabled = enabledNextButton.value,
//                onClick = {
//                    coroutineScope.launch {
//                        projectState.value.nextProject()
//                        state.animateScrollToPage(projectState.value.selectedIndex.value)
//                    }
//                },
//                content = {
//                    Icon(
//                        painter = painterResource(Res.drawable.ic_medium),
//                        contentDescription = "Next Button"
//                    )
//                },
//            )
//        }
//    }
}