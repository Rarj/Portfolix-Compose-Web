package com.rioarj.portfolix.smallscreen.portrait

import androidx.compose.foundation.BasicTooltipBox
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberBasicTooltipState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.component.TypewriterText
import com.rioarj.portfolix.page.home.HomeHelper
import com.rioarj.portfolix.style.backgroundColor
import com.rioarj.portfolix.style.orangeColor
import com.rioarj.portfolix.style.subtitleColor
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import portfolix.composeapp.generated.resources.Res
import portfolix.composeapp.generated.resources.profile

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
internal fun PortraitPage() {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { },
        content = { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(
                        message = "For the best experience, use desktop.",
                        duration = SnackbarDuration.Indefinite,
                    )
                }

                Column(
                    modifier = Modifier.fillMaxSize().background(backgroundColor),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                ) {
                    TopAppBar(
                        modifier = Modifier.padding(vertical = 24.dp).fillMaxWidth(),
                        colors = TopAppBarColors(
                            containerColor = backgroundColor,
                            titleContentColor = Color.Unspecified,
                            navigationIconContentColor = Color.Unspecified,
                            actionIconContentColor = Color.Unspecified,
                            scrolledContainerColor = backgroundColor,
                        ),
                        title = {
                            val text = "[PortfoliX]"
                            val builder = AnnotatedString.Builder(text)
                            builder.addStyle(SpanStyle(color = orangeColor), 0, 1)
                            builder.addStyle(
                                SpanStyle(color = orangeColor),
                                text.length - 1,
                                text.length
                            )

                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = builder.toAnnotatedString(),
                                fontSize = 40.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                            )
                        },
                    )

                    Spacer(Modifier.size(20.dp))
                    Image(
                        modifier = Modifier.clip(CircleShape).size(200.dp),
                        painter = painterResource(Res.drawable.profile),
                        contentDescription = "Image Profile",
                    )
                    Spacer(Modifier.size(20.dp))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Row {
                            val url = LocalUriHandler.current
                            HomeHelper.portfolioPlatform.forEach { model ->
                                val tooltipPosition =
                                    TooltipDefaults.rememberPlainTooltipPositionProvider()
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
                                text = "I'm ",
                                color = Color.White,
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Medium,
                            )
                            TypewriterText(listOf("Rio Arjuna"))
                        }
                        Text(
                            text = "Sr. Android Engineer",
                            color = subtitleColor,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )
                        Text(
                            text = "Innovation through collaboration,\nimpact through technology",
                            color = subtitleColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    )
}

