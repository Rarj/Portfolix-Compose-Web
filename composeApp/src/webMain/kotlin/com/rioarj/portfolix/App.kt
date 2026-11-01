package com.rioarj.portfolix

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.rioarj.portfolix.component.ScrollProgressBar
import com.rioarj.portfolix.motion.LocalMotionTier
import com.rioarj.portfolix.motion.LocalPointer
import com.rioarj.portfolix.motion.LocalScrollInfo
import com.rioarj.portfolix.motion.Motion
import com.rioarj.portfolix.motion.PointerState
import com.rioarj.portfolix.motion.ScrollInfo
import com.rioarj.portfolix.motion.animates
import com.rioarj.portfolix.motion.detectMotionTier
import com.rioarj.portfolix.motion.isFull
import com.rioarj.portfolix.motion.trackPointer
import com.rioarj.portfolix.navigation.Navbar
import com.rioarj.portfolix.page.aboutme.AboutMeUI
import com.rioarj.portfolix.page.home.HomeUI
import com.rioarj.portfolix.page.project.ProjectUI
import com.rioarj.portfolix.page.resume.ResumeUI
import com.rioarj.portfolix.style.LocalScreenSize
import com.rioarj.portfolix.style.PoppinsTypography
import com.rioarj.portfolix.style.PortfolixColorScheme
import com.rioarj.portfolix.style.ScreenSize
import com.rioarj.portfolix.style.backgroundColor
import com.rioarj.portfolix.style.orangeColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun App() {
    MaterialTheme(
        colorScheme = PortfolixColorScheme,
        typography = PoppinsTypography(),
    ) {
        val screenSize = when (calculateWindowSizeClass().widthSizeClass) {
            WindowWidthSizeClass.Compact -> ScreenSize.Compact
            WindowWidthSizeClass.Medium -> ScreenSize.Medium
            else -> ScreenSize.Expanded
        }
        val tier = remember { detectMotionTier() }
        CompositionLocalProvider(
            LocalScreenSize provides screenSize,
            LocalMotionTier provides tier,
        ) {
            Portfolio()
        }
    }
}

@Composable
private fun Portfolio() {
    val scroll = rememberScrollState()
    val scope = rememberCoroutineScope()
    val tier = LocalMotionTier.current
    val pointer = remember { PointerState() }

    /** Top of each section in content coordinates, filled in as sections are laid out. */
    val tops = remember { mutableStateMapOf<Int, Int>() }
    // True while a nav click is scrolling, so the bar does not hide mid-glide.
    val navLock = remember { BooleanArray(1) }
    var navVisible by remember { mutableStateOf(true) }

    LaunchedEffect(scroll) {
        var anchor = 0
        snapshotFlow { scroll.value }.collect { v ->
            when {
                navLock[0] || v < 120 -> {
                    navVisible = true; anchor = v
                }

                v > anchor + 24 -> {
                    navVisible = false; anchor = v
                }

                v < anchor - 24 -> {
                    navVisible = true; anchor = v
                }
            }
        }
    }

    BoxWithConstraints(
        Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .trackPointer(pointer)
            .drawBehind {
                // Soft cursor spotlight behind the content (Full tier, real mouse only).
                if (tier.isFull && pointer.active) {
                    drawRect(
                        Brush.radialGradient(
                            colors = listOf(orangeColor.copy(alpha = 0.10f), Color.Transparent),
                            center = pointer.position.value,
                            radius = 320.dp.toPx(),
                        )
                    )
                }
            },
    ) {
        val viewportHeight = maxHeight
        val viewportPx = with(LocalDensity.current) { maxHeight.toPx() }
        val info = remember(scroll, viewportPx) { ScrollInfo(scroll, viewportPx) }

        val active by remember {
            derivedStateOf {
                if (scroll.maxValue > 0 && scroll.value >= scroll.maxValue - 4) {
                    SectionCount - 1
                } else {
                    val probe = scroll.value + viewportPx * 0.35f
                    (0 until SectionCount).lastOrNull { (tops[it] ?: Int.MAX_VALUE) <= probe } ?: 0
                }
            }
        }
        val navShown by animateFloatAsState(
            targetValue = if (navVisible) 1f else 0f,
            animationSpec = if (tier.animates) tween(350, easing = Motion.standard) else snap(),
            label = "navShown",
        )

        CompositionLocalProvider(
            LocalScrollInfo provides info,
            LocalPointer provides pointer,
        ) {
            Column(Modifier.fillMaxSize().verticalScroll(scroll).clipToBounds()) {
                Section(0, scroll, tops) { HomeUI(minHeight = viewportHeight) }
                Section(1, scroll, tops) { AboutMeUI() }
                Section(2, scroll, tops) { ResumeUI() }
                Section(3, scroll, tops) { ProjectUI() }
            }

            Navbar(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .graphicsLayer { translationY = -(1f - navShown) * size.height },
                selected = active,
                onSelect = { index ->
                    scope.launch {
                        navLock[0] = true
                        navVisible = true
                        try {
                            scroll.animateScrollTo(
                                tops[index] ?: 0,
                                if (tier.animates) tween(
                                    Motion.glideMs,
                                    easing = Motion.glide
                                ) else snap(),
                            )
                        } finally {
                            navLock[0] = false
                        }
                    }
                },
            )
            ScrollProgressBar(Modifier.align(Alignment.TopStart))
        }
    }
}

private const val SectionCount = 4

/** Records the section's top (content coordinates) for scroll-spy and nav clicks. */
@Composable
private fun Section(
    index: Int,
    scroll: ScrollState,
    tops: MutableMap<Int, Int>,
    content: @Composable () -> Unit,
) {
    Box(
        Modifier
            .fillMaxWidth()
            .onGloballyPositioned { tops[index] = (it.positionInRoot().y + scroll.value).toInt() },
    ) { content() }
}
