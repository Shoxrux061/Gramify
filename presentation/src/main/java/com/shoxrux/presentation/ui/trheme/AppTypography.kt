package com.shoxrux.presentation.ui.trheme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.shoxrux.presentation.R
import com.shoxrux.presentation.ui.colors.Black
import com.shoxrux.presentation.ui.colors.HeadlineTextColor
import com.shoxrux.presentation.ui.colors.TitleTextColor

val AppTypography = Typography(

    headlineLarge = TextStyle(
        fontSize = 28.sp,
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        color = HeadlineTextColor
    ),
    headlineMedium = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        color = HeadlineTextColor
    ),
    headlineSmall = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        color = HeadlineTextColor
    ),


    titleLarge = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        color = TitleTextColor
    ),
    titleMedium = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        color = TitleTextColor
    ),
    titleSmall = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        color = TitleTextColor
    ),

    bodyLarge = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        color = Black
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        color = Black
    ),
    bodySmall = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.poppins_semi_bold)),
        color = Black
    ),

    labelLarge = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        color = HeadlineTextColor

    ),

    labelMedium = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        color = HeadlineTextColor
    ),

    labelSmall = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        color = HeadlineTextColor

    )


)