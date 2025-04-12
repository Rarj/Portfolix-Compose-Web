package com.rioarj.portfolix.page.project

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.component.HeaderPageUI
import com.rioarj.portfolix.style.backgroundColor
import com.rioarj.portfolix.style.notSelectedColor
import com.rioarj.portfolix.style.orangeColor
import com.rioarj.portfolix.style.subtitleColor
import com.rioarj.portfolix.style.urlColor
import org.jetbrains.compose.resources.painterResource
import portfolix.composeapp.generated.resources.Res
import portfolix.composeapp.generated.resources.ic_link_arrow

@Composable
internal fun ProjectUI(modifier: Modifier = Modifier) {
    val projectState = remember { ProjectHelper().project }

    Column(
        modifier = modifier.fillMaxSize().background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderPageUI(
            title = "Projects",
            subtitle = "Done is better than perfect",
        )

        val url = LocalUriHandler.current
        Spacer(modifier = Modifier.size(48.dp))
        Column(
            modifier = Modifier.fillMaxWidth(.45f).fillMaxHeight(.95f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
        ) {
            projectState.value.projects.forEach { projectModel ->
                Row {
                    Image(
                        modifier = Modifier.padding(horizontal = 16.dp).size(48.dp).border(
                            border = BorderStroke(1.dp, Color.White),
                            shape = RoundedCornerShape(8.dp)
                        ),
                        painter = painterResource(projectModel.projectIcon),
                        contentDescription = "Project Icon",
                    )
                    Column {
                        Text(
                            text = projectModel.title,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = subtitleColor,
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(
                            textAlign = TextAlign.Justify,
                            text = projectModel.desc,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.LightGray,
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        if (projectModel.links.isNotEmpty()) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                projectModel.links.forEach { linkModel ->
                                    Text(
                                        modifier = Modifier.pointerHoverIcon(
                                            PointerIcon.Hand, true
                                        ),
                                        text = linkModel.name,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = subtitleColor,
                                    )
                                    IconButton(
                                        modifier = Modifier.padding(start = 4.dp).size(14.dp),
                                        onClick = { url.openUri(linkModel.link) },
                                        content = {
                                            Icon(
                                                painter = painterResource(Res.drawable.ic_link_arrow),
                                                contentDescription = "Go to ${linkModel.name}",
                                                tint = urlColor,
                                            )
                                        },
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                }
                            }
                            Spacer(modifier = Modifier.size(20.dp))
                        }
                    }
                }
                HorizontalDivider(
                    modifier = Modifier.padding(start = 80.dp),
                    thickness = 1.dp,
                    color = notSelectedColor,
                )
                Spacer(modifier = Modifier.size(24.dp))
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.padding(start = 80.dp)
                        .pointerHoverIcon(PointerIcon.Hand, true)
                        .clickable { url.openUri("https://github.com/Rarj?tab=repositories") },
                    text = "More Projects",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    color = orangeColor,
                )
                IconButton(
                    modifier = Modifier.padding(start = 4.dp).size(14.dp),
                    onClick = { url.openUri("https://github.com/Rarj?tab=repositories") },
                    content = {
                        Icon(
                            painter = painterResource(Res.drawable.ic_link_arrow),
                            contentDescription = "Go to Github Repositories",
                            tint = urlColor,
                        )
                    },
                )
            }
        }
    }
}