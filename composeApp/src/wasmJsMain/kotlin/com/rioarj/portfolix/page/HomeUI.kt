package com.rioarj.portfolix.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.component.TypewriterText
import com.rioarj.portfolix.style.backgroundColor
import com.rioarj.portfolix.style.subtitleColor
import org.jetbrains.compose.resources.painterResource
import portfolix.composeapp.generated.resources.Res
import portfolix.composeapp.generated.resources.ic_github
import portfolix.composeapp.generated.resources.ic_instagram
import portfolix.composeapp.generated.resources.ic_linkedin
import portfolix.composeapp.generated.resources.ic_medium
import portfolix.composeapp.generated.resources.ic_storage
import portfolix.composeapp.generated.resources.profile

@Composable
internal fun HomeUI(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            modifier = Modifier.clip(CircleShape).size(350.dp),
            painter = painterResource(Res.drawable.profile),
            contentDescription = "Image Profile",
        )
        Spacer(Modifier.size(30.dp))
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row {
                Image(painter = painterResource(Res.drawable.ic_github), "Github")
                Spacer(modifier = Modifier.size(4.dp))
                Image(painter = painterResource(Res.drawable.ic_linkedin), "LinkedIn")
                Spacer(modifier = Modifier.size(4.dp))
                Image(painter = painterResource(Res.drawable.ic_instagram), "Instagram")
                Spacer(modifier = Modifier.size(4.dp))
                Image(
                    painter = painterResource(Res.drawable.ic_medium), contentDescription = "Medium"
                )
                Spacer(modifier = Modifier.size(4.dp))
                Image(
                    painter = painterResource(Res.drawable.ic_storage), "Sonatype - Maven Central"
                )
            }
            Row {
                Text(
                    text = "Hello, I'm ",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Medium,
                )
                TypewriterText(listOf("Rio Arjuna"))
            }
            Text(
                text = "Sr. Android Engineer",
                color = subtitleColor,
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
            )
            Text(
                text = "Innovation through collaboration, impact through technology",
                color = subtitleColor,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
            )
        }
    }
}