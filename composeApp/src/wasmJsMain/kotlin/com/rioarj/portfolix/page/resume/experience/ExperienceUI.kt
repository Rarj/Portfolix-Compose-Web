package com.rioarj.portfolix.page.resume.experience

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.style.orangeColor
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import portfolix.composeapp.generated.resources.Res
import portfolix.composeapp.generated.resources.ic_chevron_down
import portfolix.composeapp.generated.resources.ic_chevron_up
import portfolix.composeapp.generated.resources.ic_link

@Composable
internal fun ExperienceUI(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val experienceState = remember { ExperienceHelper().experience }
    val selectedExperience by remember { derivedStateOf { experienceState.value.getSelectedExperience() } }
    val state = rememberPagerState { experienceState.value.size }
    val enabledPreviousButton =
        remember { derivedStateOf { experienceState.value.isFirstItem.not() } }
    val enabledNextButton = remember { derivedStateOf { experienceState.value.isLastItem.not() } }

    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(all = 16.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Column(
                modifier = Modifier.padding(end = 16.dp),
            ) {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    enabled = enabledPreviousButton.value,
                    onClick = {
                        coroutineScope.launch {
                            experienceState.value.previousExperience()
                            state.animateScrollToPage(experienceState.value.selectedIndex.value)
                        }
                    },
                    content = {
                        Icon(
                            painter = painterResource(Res.drawable.ic_chevron_up),
                            contentDescription = "Previous Button",
                        )
                    },
                )
                Spacer(modifier = Modifier.size(4.dp))
                IconButton(
                    modifier = Modifier.size(24.dp),
                    enabled = enabledNextButton.value,
                    onClick = {
                        coroutineScope.launch {
                            experienceState.value.nextExperience()
                            state.animateScrollToPage(experienceState.value.selectedIndex.value)
                        }
                    },
                    content = {
                        Icon(
                            painter = painterResource(Res.drawable.ic_chevron_down),
                            contentDescription = "Next Button",
                        )
                    },
                )
            }

            VerticalPager(
                state = state,
                modifier = Modifier.width(800.dp).fillMaxHeight(),
                userScrollEnabled = false,
            ) {
                Column {
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        VerticalDivider(
                            modifier = Modifier.clip(RoundedCornerShape(percent = 50))
                                .height(48.dp),
                            thickness = 4.dp,
                            color = orangeColor,
                        )
                        Column(
                            modifier = Modifier.padding(horizontal = 4.dp),
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Row {
                                Text(
                                    text = selectedExperience.companyName,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                if (selectedExperience.appLinks.isNotEmpty()) {
                                    val url = LocalUriHandler.current
                                    IconButton(
                                        modifier = Modifier.padding(start = 4.dp).size(24.dp),
                                        onClick = {
                                            url.openUri(selectedExperience.appLinks.first())
                                        },
                                        content = {
                                            Icon(
                                                painter = painterResource(Res.drawable.ic_link),
                                                contentDescription = "Link Icon to Play Store",
                                            )
                                        }
                                    )
                                }
                            }

                            Text(
                                text = buildString {
                                    append(selectedExperience.title)
                                    append(" • ")
                                    append(selectedExperience.startDate)
                                    append("–")
                                    append(selectedExperience.endDate)
                                    append(" • ")
                                    append(selectedExperience.yearsOfExperience)
                                },
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.size(12.dp))

                    val descriptions = selectedExperience.descriptions
                    Text(
                        text = "Responsibilities",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    repeat(descriptions.size) { index ->
                        Text(
                            text = descriptions[index],
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    }

                    Spacer(modifier = Modifier.size(14.dp))

                    val achievements = selectedExperience.achievements
                    if (achievements.isNotEmpty()) {
                        Text(
                            text = "Achievements",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                        repeat(achievements.size) { index ->
                            Text(
                                text = achievements[index],
                                color = Color.Black,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                            )
                        }
                    }
                }
            }
        }
    }
}