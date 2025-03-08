package com.rioarj.portfolix.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rioarj.portfolix.style.orangeColor
import kotlinx.coroutines.delay

@Composable
internal fun TypewriterText(texts: List<String>) {
    var textIndex by remember { mutableStateOf(0) }
    var textToDisplay by remember { mutableStateOf("") }
    val textChars: List<List<String>> = remember {
        texts.map { text -> text.map { it.toString() } }
    }

    LaunchedEffect(key1 = texts) {
        while (textIndex < textChars.size) {
            textChars[textIndex].forEachIndexed { charIndex, _ ->
                textToDisplay = textChars[textIndex].take(n = charIndex + 1).joinToString(separator = "")
                delay(160)
            }
            textIndex = textChars.size
            delay(1000)
        }
    }

    Text(
        text = textToDisplay,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = orangeColor,
    )
}