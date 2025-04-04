package com.example.itemsui.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.itemsui.R

val LufgaFont = FontFamily(
    Font(R.font.lufga_thin, FontWeight.Thin),
    Font(R.font.lufga_medium, FontWeight.Medium),
    Font(R.font.lufga_bold, FontWeight.Bold),
    Font(R.font.lufga_semi_bold, FontWeight.SemiBold),
    Font(R.font.lufga_medium, FontWeight.Medium),
    Font(R.font.lufga_regular, FontWeight.Normal),
    Font(R.font.lufga_light, FontWeight.Light),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)