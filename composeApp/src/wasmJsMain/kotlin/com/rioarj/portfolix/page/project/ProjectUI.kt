package com.rioarj.portfolix.page.project

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rioarj.portfolix.component.HeaderPageUI
import kotlinx.coroutines.launch

@Composable
internal fun ProjectUI(modifier: Modifier = Modifier) {
    val coroutineScope = rememberCoroutineScope()
    val projectState = remember { ProjectHelper().project }
    val state = rememberPagerState { projectState.value.size }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderPageUI(
            title = "Projects",
            subtitle = "Done is better than perfect",
        )

        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            if (projectState.value.isFirstItem.not()) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            projectState.value.previousProject()
                            state.animateScrollToPage(projectState.value.selectedIndex.value)
                        }
                    },
                    content = {
                        Text(
                            text = "Previous"
                        )
                    }
                )
            }
            HorizontalPager(
                state = state,
                modifier = Modifier.width(500.dp),
                pageSpacing = 8.dp,
            ) { page ->
                Column {
                    Text(
                        modifier = Modifier.padding(24.dp),
                        text = "Selected project: ${projectState.value.projects[page].title}",
                    )
                }
            }
            if (projectState.value.isLastItem.not()) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            projectState.value.nextProject()
                            state.animateScrollToPage(projectState.value.selectedIndex.value)
                        }
                    },
                    content = {
                        Text(
                            text = "Next"
                        )
                    }
                )
            }
        }
    }
}