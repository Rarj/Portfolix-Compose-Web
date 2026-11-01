package com.rioarj.portfolix.motion

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.staticCompositionLocalOf

/** Scroll position and viewport height, read only in layout/draw/effect scopes. */
internal class ScrollInfo(val scroll: ScrollState, val viewportPx: Float)

internal val LocalScrollInfo = staticCompositionLocalOf { ScrollInfo(ScrollState(0), 1000f) }
