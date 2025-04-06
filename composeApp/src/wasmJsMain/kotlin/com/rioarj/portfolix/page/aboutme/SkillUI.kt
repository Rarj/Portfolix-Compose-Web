package com.rioarj.portfolix.page.aboutme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.style.backgroundColor
import com.rioarj.portfolix.style.orangeColor
import com.rioarj.portfolix.style.subtitleColor

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun RowScope.SkillUI(modifier: Modifier = Modifier) {
    this.apply {
        Column(
            modifier = modifier.padding(start = 24.dp, top = 24.dp, end = 24.dp)
                .clip(RoundedCornerShape(8.dp)),
        ) {
            Text(
                text = "Skills",
                color = orangeColor,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            )

            val skills = listOf(
                "Kotlin",
                "Jetpack Compose & XML",
                "Android Jetpack",
                "Coroutines & Flow",
                "MVVM",
                "MVP",
                "MVI",
                "Hilt & Dagger 2",
                "Koin",
                "Modularization",
                "ReactiveX",
                "Firebase",
                "RestAPI",
                "GraphQL",
                "Retrofit 2",
                "OkHTTP",
                "Fastlane",
                "JAVA",
                "Ruby",
                "Python",
                "ReactJS",
            )
            FlowRow {
                repeat(skills.size) { index ->
                    Text(
                        modifier = Modifier.padding(2.dp).clip(RoundedCornerShape(8.dp))
                            .background(subtitleColor).padding(horizontal = 8.dp, vertical = 4.dp),
                        text = skills[index],
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = backgroundColor,
                    )
                }
            }
        }
    }
}