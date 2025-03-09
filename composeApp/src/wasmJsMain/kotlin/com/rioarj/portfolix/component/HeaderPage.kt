package com.rioarj.portfolix.component

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.style.orangeColor

@Composable
fun ColumnScope.HeaderPageUI(
    title: String,
    subtitle: String,
) {
    this.apply {
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = title,
            color = Color.Black,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = subtitle,
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
        )
        Spacer(modifier = Modifier.size(8.dp))
        HorizontalDivider(
            modifier = Modifier.width(100.dp)
                .clip(RoundedCornerShape(percent = 50)),
            color = orangeColor,
            thickness = 4.dp,
        )
        Spacer(modifier = Modifier.size(24.dp))
    }
}