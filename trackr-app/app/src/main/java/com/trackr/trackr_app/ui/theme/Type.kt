package com.trackr.trackr_app.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.trackr.trackr_app.R

val Rubik = FontFamily(
    Font(R.font.rubik_regular),
    Font(R.font.rubik_bold, FontWeight.Bold),
    Font(R.font.rubik_black, FontWeight.Black)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),

    h1 = TextStyle(
        fontFamily = Rubik,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp
    )

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)