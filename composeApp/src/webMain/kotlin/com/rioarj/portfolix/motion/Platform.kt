package com.rioarj.portfolix.motion

/** True on the JS fallback build (Safari, Brave, older iOS), which is slower than Wasm. */
internal expect fun isJsFallback(): Boolean

/** True when the user asked the OS/browser for reduced motion. */
internal expect fun prefersReducedMotion(): Boolean
