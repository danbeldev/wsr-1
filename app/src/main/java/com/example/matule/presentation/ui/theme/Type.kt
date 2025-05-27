package com.example.matule.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.matule.R

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

data class CustomTypography(
    val title1Semibold: TextStyle,
//    val title1Heavy: TextStyle,
//    val title2Regular: TextStyle,
//    val title2Semibold: TextStyle,
//    val title2Heavy: TextStyle,
//    val title3Regular: TextStyle,
//    val title3Medium: TextStyle,
//    val title3Semibold: TextStyle,
//    val headlineRegular: TextStyle,
//    val headlineMedium: TextStyle,
//    val textRegular: TextStyle,
//    val textMedium: TextStyle,
//    val captionRegular: TextStyle,
//    val captionSemibold: TextStyle,
//    val caption2Regular: TextStyle,
//    val caption2Bold: TextStyle
)

val LocalTypography = staticCompositionLocalOf<CustomTypography> {
    error("typography not found")
}

fun createCustomTypography(): CustomTypography {
    return CustomTypography(
        title1Semibold = TextStyle(
            fontFamily = FontFamily(Font(R.font.sf)),
            fontWeight = FontWeight.W600,
            fontSize = 24.sp,
            color = Color.Black
        )
    )
}