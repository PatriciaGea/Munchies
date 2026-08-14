package com.umain.munchies.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    background = MunchiesBackground,
    onBackground = MunchiesText,
    surface = MunchiesBackground,
    onSurface = MunchiesText
)

private val DarkColors = darkColorScheme(
    background = MunchiesBackgroundDark,
    onBackground = MunchiesTextDark,
    surface = MunchiesBackgroundDark,
    onSurface = MunchiesTextDark
)

@Composable
fun MunchiesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = MunchiesTypography,
        content = content
    )
}