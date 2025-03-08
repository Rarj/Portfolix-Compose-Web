package com.rioarj.portfolix

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.rioarj.portfolix.toolbar.ToolbarUiKit
import com.rioarj.portfolix.style.PoppinsTypography

@Composable
fun App() {
    MaterialTheme(
        typography = PoppinsTypography(),
    ) {
        ToolbarUiKit()
    }
}