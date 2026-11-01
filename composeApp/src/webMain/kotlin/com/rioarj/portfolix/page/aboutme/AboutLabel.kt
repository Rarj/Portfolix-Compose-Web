package com.rioarj.portfolix.page.aboutme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.rioarj.portfolix.style.orangeColor
import com.rioarj.portfolix.style.typeScale

/** Orange sub-heading used for the "Study" and "Skills" blocks. */
@Composable
internal fun AboutLabel(text: String) {
    Text(
        text = text,
        color = orangeColor,
        fontSize = typeScale().h3,
        fontWeight = FontWeight.Bold,
    )
}
