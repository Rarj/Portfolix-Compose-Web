package com.rioarj.portfolix.motion

internal actual fun isJsFallback(): Boolean = true

internal actual fun prefersReducedMotion(): Boolean = try {
    js("window.matchMedia('(prefers-reduced-motion: reduce)').matches") as Boolean
} catch (e: Throwable) {
    false
}
