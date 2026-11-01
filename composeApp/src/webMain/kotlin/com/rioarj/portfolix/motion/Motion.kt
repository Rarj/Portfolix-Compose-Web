package com.rioarj.portfolix.motion

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.runtime.compositionLocalOf

/**
 * Full: Wasm build, every effect. Lite: JS fallback, reveals and simple hovers only.
 * None: reduced-motion preference, no transforms or animation.
 */
internal enum class MotionTier { Full, Lite, None }

internal val LocalMotionTier = compositionLocalOf { MotionTier.Full }

internal fun detectMotionTier(): MotionTier = when {
    prefersReducedMotion() -> MotionTier.None
    isJsFallback() -> MotionTier.Lite
    else -> MotionTier.Full
}

internal val MotionTier.animates get() = this != MotionTier.None
internal val MotionTier.isFull get() = this == MotionTier.Full

/** Shared timing so every effect feels related. */
internal object Motion {
    /** Fast start, long soft landing. */
    val standard = CubicBezierEasing(0.16f, 1f, 0.3f, 1f)

    /** Symmetric ease used for page-length scrolls. */
    val glide = CubicBezierEasing(0.65f, 0f, 0.35f, 1f)

    const val revealMs = 800
    const val glideMs = 900
    const val hoverMs = 250
}
