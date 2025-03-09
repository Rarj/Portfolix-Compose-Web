package com.rioarj.portfolix.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.component.HeaderPageUI
import com.rioarj.portfolix.style.backgroundColor
import com.rioarj.portfolix.style.orangeColor
import com.rioarj.portfolix.style.subtitleColor
import org.jetbrains.compose.resources.painterResource
import portfolix.composeapp.generated.resources.Res
import portfolix.composeapp.generated.resources.about_me

@Composable
internal fun AboutMeUI(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderPageUI(
            title = "About Me",
            subtitle = "Simplicity is the soul of efficiency",
        )
        Row(
            modifier = Modifier
                .wrapContentSize()
                .padding(bottom = 100.dp)
                .padding(horizontal = 100.dp)
                .background(backgroundColor)
        ) {
            Image(
                modifier = Modifier.wrapContentSize(),
                painter = painterResource(Res.drawable.about_me),
                contentDescription = "Profile Picture"
            )
            Column(
                modifier = Modifier.fillMaxSize().padding(all = 64.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Hi there!",
                    color = orangeColor,
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = "I’m Rio, an experienced Android Engineer with a diverse background spanning multiple industries. I’m passionate about learning and exploring new technologies to create innovative and impactful solutions.\n" + "\n" + "Alongside my technical expertise, I’m known for being a friendly team player and a natural cheerleader, always encouraging and supporting those around me.\n" + "\n" + "I thrive in environments where collaboration is key, and I love motivating my team to push boundaries and achieve meaningful, high-impact outcomes together.",
                    color = subtitleColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 0.7.sp,
                )
            }
        }
    }
}