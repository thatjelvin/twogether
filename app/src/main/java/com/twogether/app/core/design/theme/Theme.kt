package com.twogether.app.core.design.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = DuskRose,
    onPrimary = OnDuskRose,
    secondary = NightIndigo,
    onSecondary = OnDuskRose,
    tertiary = Gold,
    onTertiary = BodyText,
    background = CreamBackground,
    onBackground = BodyText,
    surface = CardSurface,
    onSurface = BodyText,
    surfaceVariant = SubtleFill,
    onSurfaceVariant = BodyText,
    outline = OutlineWarm,
    error = ErrorRed,
    onError = OnDuskRose,
)

private val DarkColors = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkOnPrimary,
    secondary = DarkSecondary,
    onSecondary = DarkOnSecondary,
    tertiary = DarkTertiary,
    onTertiary = DarkOnTertiary,
    background = DarkBackground,
    onBackground = DarkOnSurface,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkOnSurface,
    outline = DarkOutline,
    error = DarkError,
    onError = DarkOnPrimary,
)

@Composable
fun TwogetherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = Typography,
        shapes = TwogetherShapes,
        content = content,
    )
}
