package com.rioarj.portfolix.component

import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale

@Stable
internal fun Modifier.mirror() = this.scale(scaleX = -1f, scaleY = 1f)