package com.rioarj.portfolix.page.aboutme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.component.HeaderPageUI
import com.rioarj.portfolix.style.backgroundColor
import com.rioarj.portfolix.style.orangeColor
import com.rioarj.portfolix.style.subtitleColor

@Composable
internal fun AboutMeUI(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize().background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderPageUI(
            title = "About Me",
            subtitle = "Simplicity is the soul of efficiency",
        )
        Row(
            modifier = Modifier.fillMaxWidth(.95f)
                .fillMaxHeight(.95f)
                .clip(RoundedCornerShape(8.dp))
                .background(backgroundColor)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(.95f).padding(
                    all
                    = 24.dp
                ),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier.wrapContentSize().padding(top = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    val text = "Hi There! "
                    val builder = AnnotatedString.Builder(text)
                    builder.addStyle(SpanStyle(color = orangeColor), 0, text.length)
                    builder.append("I’m Rio, an experienced Android Engineer with a diverse background spanning multiple industries. I’m passionate about learning and exploring new technologies to create innovative and impactful solutions." + " Alongside my technical expertise, I’m known for being a friendly team player and a natural cheerleader, always encouraging and supporting those around me." + " I thrive in environments where collaboration is key, and I love motivating my team to push boundaries and achieve meaningful, high-impact outcomes together.")
                    Text(
                        modifier = Modifier.padding(horizontal = 4.dp).fillMaxWidth(.8f),
                        text = builder.toAnnotatedString(),
                        color = subtitleColor,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = .7.sp,
                        lineHeight = 36.sp,
                        textAlign = TextAlign.Justify,
                    )
                }

                Row(modifier = Modifier.fillMaxWidth(.80f).padding(top = 24.dp)) {
                    StudyUI()
                    SkillUI()
                }
            }
        }
    }
}