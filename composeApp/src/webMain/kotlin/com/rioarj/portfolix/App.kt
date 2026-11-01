package com.rioarj.portfolix

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.rioarj.portfolix.page.aboutme.AboutMeUI
import com.rioarj.portfolix.page.home.HomeUI
import com.rioarj.portfolix.page.project.ProjectUI
import com.rioarj.portfolix.page.resume.ResumeUI
import com.rioarj.portfolix.smallscreen.portrait.PortraitPage
import com.rioarj.portfolix.style.PoppinsTypography
import com.rioarj.portfolix.toolbar.ToolbarUiKit
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun App() {
    MaterialTheme(
        typography = PoppinsTypography(),
    ) {
        val windowSizeClass = calculateWindowSizeClass()

        val widthExpanded = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded
        val heightExpanded = windowSizeClass.heightSizeClass == WindowHeightSizeClass.Expanded
        if (widthExpanded && heightExpanded) {
            val coroutineScope = rememberCoroutineScope()
            val state = rememberPagerState { 4 }

            Column {
                ToolbarUiKit(onScrollToPage = { index ->
                    coroutineScope.launch {
                        state.animateScrollToPage(index)
                    }
                })

                VerticalPager(
                    state = state,
                    modifier = Modifier.fillMaxSize(),
                    userScrollEnabled = false,
                ) { page ->
                    when (page) {
                        0 -> HomeUI()
                        1 -> AboutMeUI()
                        2 -> ResumeUI()
                        3 -> ProjectUI()
                    }
                }
            }
        } else PortraitPage()
    }
}