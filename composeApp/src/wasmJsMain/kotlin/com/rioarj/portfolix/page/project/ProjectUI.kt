package com.rioarj.portfolix.page.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.component.HeaderPageUI
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import portfolix.composeapp.generated.resources.Res
import portfolix.composeapp.generated.resources.ic_medium

@Composable
internal fun ProjectUI(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val projectState = remember { ProjectHelper().project }
    val selectedProject by remember { derivedStateOf { projectState.value.getSelectedProject() } }
    val state = rememberPagerState { projectState.value.size }
    val enabledPreviousButton = remember { derivedStateOf { projectState.value.isFirstItem.not() } }
    val enabledNextButton = remember { derivedStateOf { projectState.value.isLastItem.not() } }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderPageUI(
            title = "Projects",
            subtitle = "Done is better than perfect",
        )

        Row(
            modifier = Modifier.fillMaxWidth().padding(bottom = 64.dp).padding(horizontal = 64.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                enabled = enabledPreviousButton.value,
                onClick = {
                    coroutineScope.launch {
                        projectState.value.previousProject()
                        state.animateScrollToPage(projectState.value.selectedIndex.value)
                    }
                },
                content = {
                    Icon(
                        painter = painterResource(Res.drawable.ic_medium),
                        contentDescription = "Previous Button"
                    )
                },
            )

            HorizontalPager(
                state = state,
                modifier = Modifier.width(700.dp),
                pageSpacing = 8.dp,
                contentPadding = PaddingValues(24.dp),
                userScrollEnabled = false,
            ) {
                Card(modifier = Modifier.fillMaxSize()) {
                    Column {
                        Text(
                            text = selectedProject.title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                        )
                        Row {
                            selectedProject.links.forEach { linkModel ->
                                Image(painter = painterResource(linkModel.icon), linkModel.name)
                                Spacer(modifier = Modifier.size(4.dp))
                            }
                        }
                    }
                }
            }

            IconButton(
                enabled = enabledNextButton.value,
                onClick = {
                    coroutineScope.launch {
                        projectState.value.nextProject()
                        state.animateScrollToPage(projectState.value.selectedIndex.value)
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