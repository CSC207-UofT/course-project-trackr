package com.trackr.trackr_app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = DarkBlue,
    primaryVariant = DarkBlueVariant,
    secondary = LightBlue,
    onPrimary = Color.White,
    background = BackgroundBlue,
    onBackground = LightBlue
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    primaryVariant = LightBlueVariant,
    secondary = DarkBlue,
    onPrimary = DarkBlueVariant,
    background = LightBackground,
    onBackground = LightBlue


    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun TrackrappTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val systemUiController = rememberSystemUiController()

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    systemUiController.setSystemBarsColor(colors.primaryVariant)

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )


}