package com.rioarj.portfolix.page.resume.experience

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.style.orangeColor
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import portfolix.composeapp.generated.resources.Res
import portfolix.composeapp.generated.resources.ic_medium

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
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 64.dp).padding(horizontal = 64.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                enabled = enabledPreviousButton.value,
                onClick = {
                    coroutineScope.launch {
                        experienceState.value.previousExperience()
                        state.animateScrollToPage(experienceState.value.selectedIndex.value)
                    }
                },
                content = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_medium),
                        contentDescription = "Previous Button"
                    )
                },
            )

            VerticalPager(
                state = state,
                modifier = Modifier.width(700.dp),
                pageSpacing = 8.dp,
                contentPadding = PaddingValues(24.dp),
                userScrollEnabled = false,
            ) {
                Row(
                    modifier = modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    VerticalDivider(
                        modifier = Modifier.clip(RoundedCornerShape(percent = 50)).height(40.dp),
                        thickness = 2.dp,
                        color = orangeColor,
                    )
                    Column(
                        modifier = Modifier.padding(horizontal = 4.dp),
                    ) {
                        Text(
                            text = selectedExperience.companyName,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            text = selectedExperience.title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                    Spacer(modifier = Modifier.size(100.dp))
                    Text(
                        modifier = Modifier.clip(RoundedCornerShape(percent = 50)).background(
                            orangeColor
                        ).padding(all = 8.dp),
                        text = selectedExperience.yearsOfExperience(),
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
            IconButton(
                enabled = enabledNextButton.value,
                onClick = {
                    coroutineScope.launch {
                        experienceState.value.nextExperience()
                        state.animateScrollToPage(experienceState.value.selectedIndex.value)
                    }
                },
                content = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_medium),
                        contentDescription = "Next Button"
                    )
                },
            )
        }
    }
}