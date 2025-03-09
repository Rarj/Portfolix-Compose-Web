package com.rioarj.portfolix

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.rioarj.portfolix.page.HomeUI
import com.rioarj.portfolix.style.PoppinsTypography
import com.rioarj.portfolix.toolbar.ToolbarUiKit

@Composable
fun App() {
    MaterialTheme(
        typography = PoppinsTypography(),
    ) {
        Column {
            ToolbarUiKit()
            HomeUI()
        }
    }
}