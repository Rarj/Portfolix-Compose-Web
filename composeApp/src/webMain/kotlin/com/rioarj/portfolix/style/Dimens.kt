package com.rioarj.portfolix.style

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

/** 8dp grid spacing tokens. */
internal object Space {
    val xs = 4.dp
    val sm = 8.dp
    val md = 16.dp
    val lg = 24.dp
    val xl = 40.dp
    val xxl = 64.dp
    val section = 96.dp
}

internal object Shapes {
    val small = RoundedCornerShape(12.dp)
    val medium = RoundedCornerShape(20.dp)
    val pill = RoundedCornerShape(percent = 50)
}

internal val MaxContentWidth = 1100.dp
internal val NavHeight = 72.dp
