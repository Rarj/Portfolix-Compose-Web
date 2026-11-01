package com.rioarj.portfolix

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import org.jetbrains.skiko.wasm.onWasmReady

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    // Skiko's wasm runtime loads asynchronously on the JS target; wait for it.
    onWasmReady {
        ComposeViewport(document.body!!) {
            App()
        }
    }
}
