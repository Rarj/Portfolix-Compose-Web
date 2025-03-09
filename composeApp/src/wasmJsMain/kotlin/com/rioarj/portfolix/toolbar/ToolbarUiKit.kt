package com.rioarj.portfolix.toolbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.style.backgroundColor
import com.rioarj.portfolix.style.orangeColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ToolbarUiKit(
    modifier: Modifier = Modifier,
    onScrollToPage: (Int) -> Unit,
) {
    TopAppBar(
        modifier = modifier.height(160.dp),
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
            builder.addStyle(SpanStyle(color = orangeColor), text.length - 1, text.length)

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = builder.toAnnotatedString(),
                    fontSize = 50.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White,
                )

                NavigationHeaderUI(onSelectedIndex = onScrollToPage)
            }
        },
    )
}