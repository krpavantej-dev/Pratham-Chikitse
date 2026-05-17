package com.example.mindmatrix.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Red80,
    secondary = RedGrey80,
    tertiary = Orange80,
    background = Color(0xFF1A0000),
    surface = Color(0xFF2C0A0A),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White,
    primaryContainer = Color(0xFF7F0000),
    onPrimaryContainer = Color(0xFFFFB3B3),
    secondaryContainer = Color(0xFF4A2020),
    onSecondaryContainer = Color(0xFFFFCCCC)
)

private val LightColorScheme = lightColorScheme(
    primary = Red40,
    secondary = RedGrey40,
    tertiary = Orange40,
    background = SurfaceWhite,
    surface = CardBg,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = Color(0xFF1A0000),
    onSurface = Color(0xFF1A0000),
    primaryContainer = Color(0xFFFFCDD2),
    onPrimaryContainer = Color(0xFF7F0000),
    secondaryContainer = Color(0xFFFFE0E0),
    onSecondaryContainer = Color(0xFF4A2020),
    errorContainer = Color(0xFFFFEBEE),
    onErrorContainer = Color(0xFFC62828)
)

@Composable
fun MindmatrixTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}