package com.rioarj.portfolix.toolbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun NavigationHeaderUI(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(
            onClick = {},
            content = {
                Text(
                    text = "Home",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                )
            },
        )
        Spacer(modifier = Modifier.width(24.dp))
        TextButton(
            onClick = {},
            content = {
                Text(
                    text = "About Me",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                )
            },
        )
        Spacer(modifier = Modifier.width(24.dp))
        TextButton(
            onClick = {},
            content = {
                Text(
                    text = "Resume",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                )
            },
        )
        Spacer(modifier = Modifier.width(24.dp))
        TextButton(
            onClick = {},
            content = {
                Text(
                    text = "Projects",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White,
                )
            },
        )
        Spacer(modifier = Modifier.width(24.dp))
    }
}