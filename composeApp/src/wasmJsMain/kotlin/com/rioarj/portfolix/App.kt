package com.rioarj.portfolix

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.rioarj.portfolix.page.HomeUI
import com.rioarj.portfolix.style.PoppinsTypography
import com.rioarj.portfolix.toolbar.ToolbarUiKit
import kotlinx.coroutines.launch

@Composable
fun App() {
    MaterialTheme(
        typography = PoppinsTypography(),
    ) {
        val coroutineScope = rememberCoroutineScope()
        val state = rememberPagerState { 3 }

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
                    1 -> {
                        // TODO: Integrate with Resume page
                    }
                    2 -> {

                    }

                    3 -> {
                        // TODO: Integrate with Project page
                    }
                }
            }
        }
    }
}