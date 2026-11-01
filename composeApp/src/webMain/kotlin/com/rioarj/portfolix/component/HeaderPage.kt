package com.rioarj.portfolix.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.style.orangeColor
import com.rioarj.portfolix.style.subtitleColor

@Composable
fun ColumnScope.HeaderPageUI(
    title: String,
    subtitle: String,
) {
    this.apply {
        Column(
            modifier = Modifier.clip(
                RoundedCornerShape(
                    topStartPercent = 10,
                    topEndPercent = 50,
                    bottomEndPercent = 10,
                    bottomStartPercent = 50,
                )
            ).background(subtitleColor).padding(all = 20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = subtitle,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
            )
        }
        Spacer(modifier = Modifier.size(4.dp))
        HorizontalDivider(
            modifier = Modifier.width(100.dp).clip(RoundedCornerShape(percent = 50)),
            color = orangeColor,
            thickness = 4.dp,
        )
    }
}