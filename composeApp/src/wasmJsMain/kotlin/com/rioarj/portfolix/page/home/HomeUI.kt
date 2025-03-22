package com.rioarj.portfolix.page.home

import androidx.compose.foundation.BasicTooltipBox
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberBasicTooltipState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.component.TypewriterText
import com.rioarj.portfolix.style.backgroundColor
import com.rioarj.portfolix.style.subtitleColor
import org.jetbrains.compose.resources.painterResource
import portfolix.composeapp.generated.resources.Res
import portfolix.composeapp.generated.resources.profile

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
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
                val url = LocalUriHandler.current
                HomeHelper.portfolioPlatform.forEach { model ->
                    val tooltipPosition = TooltipDefaults.rememberPlainTooltipPositionProvider()
                    val tooltipState = rememberBasicTooltipState(isPersistent = false)

                    BasicTooltipBox(
                        positionProvider = tooltipPosition,
                        tooltip = {
                            Text(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(16))
                                    .background(Color.White)
                                    .padding(4.dp),
                                text = model.name,
                            )
                        },
                        state = tooltipState,
                    ) {
                        IconButton(
                            modifier = Modifier.size(30.dp),
                            onClick = { url.openUri(model.url) },
                            content = {
                                Icon(
                                    modifier = Modifier.padding(all = 2.dp),
                                    tint = Color.White,
                                    painter = painterResource(model.icon),
                                    contentDescription = model.name,
                                )
                            },
                        )
                    }

                    Spacer(modifier = Modifier.size(4.dp))
                }
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