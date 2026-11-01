package com.rioarj.portfolix.style

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.Font
import portfolix.composeapp.generated.resources.Res
import portfolix.composeapp.generated.resources.poppins_bold
import portfolix.composeapp.generated.resources.poppins_extra_bold
import portfolix.composeapp.generated.resources.poppins_medium
import portfolix.composeapp.generated.resources.poppins_regular
import portfolix.composeapp.generated.resources.poppins_semi_bold

@Composable
private fun PoppinsFontFamily() = FontFamily(
    Font(resource = Res.font.poppins_medium, weight = FontWeight.Medium),
    Font(resource = Res.font.poppins_bold, weight = FontWeight.Bold),
    Font(resource = Res.font.poppins_extra_bold, weight = FontWeight.ExtraBold),
    Font(resource = Res.font.poppins_regular, weight = FontWeight.Normal),
    Font(resource = Res.font.poppins_semi_bold, weight = FontWeight.SemiBold),
)

@Composable
fun PoppinsTypography() = Typography().run {
    val fontFamily = PoppinsFontFamily()
    copy(
        displayLarge = displayLarge.copy(fontFamily = fontFamily),
        displayMedium = displayMedium.copy(fontFamily = fontFamily),
        displaySmall = displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = titleLarge.copy(fontFamily = fontFamily),
        titleMedium = titleMedium.copy(fontFamily = fontFamily),
        titleSmall = titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = bodyLarge.copy(fontFamily = fontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = bodySmall.copy(fontFamily = fontFamily),
        labelLarge = labelLarge.copy(fontFamily = fontFamily),
        labelMedium = labelMedium.copy(fontFamily = fontFamily),
        labelSmall = labelSmall.copy(fontFamily = fontFamily)
    )
}
