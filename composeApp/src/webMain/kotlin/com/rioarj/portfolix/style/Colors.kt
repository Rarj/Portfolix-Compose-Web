package com.rioarj.portfolix.style

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

// Surfaces
internal val backgroundColor = Color(0xFF12141F)
internal val surfaceColor = Color(0xFF1A1D2E)
internal val surfaceRaisedColor = Color(0xFF222640)
internal val hairlineColor = Color(0x14FFFFFF)

// Accent + text
internal val orangeColor = Color(0xFFFF5823)
internal val subtitleColor = Color(0xFFFFF8DC)
internal val mutedColor = Color(0xFFA3A3A3)
internal val amberColor = Color(0xFFFFA24C)
internal val urlColor = Color(0xFF329DA8)

internal val selectedColor = orangeColor
internal val notSelectedColor = mutedColor

internal val PortfolixColorScheme: ColorScheme = darkColorScheme(
    primary = orangeColor,
    onPrimary = Color.White,
    background = backgroundColor,
    onBackground = subtitleColor,
    surface = surfaceColor,
    onSurface = subtitleColor,
    surfaceVariant = surfaceRaisedColor,
    onSurfaceVariant = mutedColor,
    outline = hairlineColor,
)
