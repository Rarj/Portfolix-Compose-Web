package com.rioarj.portfolix.page.resume.education

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
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
import com.rioarj.portfolix.style.subtitleColor

@Composable
internal fun EducationComponent(
    modifier: Modifier = Modifier,
    education: EducationModel,
) {
    Row(
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            Spacer(modifier = Modifier.size(12.dp))

            Row(
                modifier = Modifier.wrapContentHeight(),
            ) {
                VerticalDivider(
                    modifier = Modifier.clip(RoundedCornerShape(percent = 50)).height(48.dp),
                    thickness = 4.dp,
                    color = orangeColor,
                )
                Column(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = education.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                    Text(
                        text = buildString {
                            append(education.degree)
                            append(" • ")
                            append(education.date)
                        },
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = subtitleColor,
                    )
                }
            }

            Spacer(modifier = Modifier.size(12.dp))
        }
    }
}