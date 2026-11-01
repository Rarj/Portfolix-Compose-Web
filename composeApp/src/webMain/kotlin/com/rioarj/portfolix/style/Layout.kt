package com.rioarj.portfolix.style

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/** Coarse breakpoints: <600dp, 600–839dp, 840dp+. */
internal enum class ScreenSize { Compact, Medium, Expanded }

internal val LocalScreenSize = compositionLocalOf { ScreenSize.Expanded }

internal val ScreenSize.isCompact get() = this == ScreenSize.Compact
internal val ScreenSize.isExpanded get() = this == ScreenSize.Expanded

internal val ScreenSize.gutter: Dp
    get() = when (this) {
        ScreenSize.Compact -> 20.dp
        ScreenSize.Medium -> 32.dp
        ScreenSize.Expanded -> 48.dp
    }

/** Semantic type scale; steps down with the window width. */
internal class TypeScale(
    val hero: TextUnit,
    val section: TextUnit,
    val heroName: TextUnit,
    val display: TextUnit,
    val tagline: TextUnit,
    val h2: TextUnit,
    val h3: TextUnit,
    val body: TextUnit,
    val small: TextUnit,
)

@Composable
internal fun typeScale(): TypeScale = when (LocalScreenSize.current) {
    ScreenSize.Expanded -> TypeScale(88.sp, 44.sp, 30.sp, 44.sp, 18.sp, 28.sp, 20.sp, 18.sp, 15.sp)
    ScreenSize.Medium -> TypeScale(64.sp, 36.sp, 26.sp, 38.sp, 17.sp, 26.sp, 19.sp, 17.sp, 14.sp)
    ScreenSize.Compact -> TypeScale(42.sp, 28.sp, 22.sp, 30.sp, 15.sp, 22.sp, 18.sp, 16.sp, 14.sp)
}
