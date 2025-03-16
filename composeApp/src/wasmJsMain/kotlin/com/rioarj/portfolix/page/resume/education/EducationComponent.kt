package com.rioarj.portfolix.page.resume.education

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.style.orangeColor

@Composable
internal fun EducationComponent(
    modifier: Modifier = Modifier,
    education: EducationModel,
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
                text = education.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = education.degree,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
            )
        }
        Spacer(modifier = Modifier.size(100.dp))
        Text(
            modifier = Modifier.clip(RoundedCornerShape(percent = 50)).background(orangeColor)
                .padding(all = 8.dp),
            text = education.date,
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
    }
}