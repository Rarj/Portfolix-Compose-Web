package com.rioarj.portfolix.motion

@JsFun("() => window.matchMedia('(prefers-reduced-motion: reduce)').matches")
private external fun matchesReducedMotion(): Boolean

internal actual fun isJsFallback(): Boolean = false

internal actual fun prefersReducedMotion(): Boolean = try {
    matchesReducedMotion()
} catch (e: Throwable) {
    false
}
