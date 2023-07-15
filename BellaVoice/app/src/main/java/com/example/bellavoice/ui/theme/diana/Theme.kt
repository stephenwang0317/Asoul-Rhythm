package com.example.bellavoice.ui.theme.diana

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable


private val LightColors = lightColorScheme(
    primary = diana_theme_light_primary,
    onPrimary = diana_theme_light_onPrimary,
    primaryContainer = diana_theme_light_primaryContainer,
    onPrimaryContainer = diana_theme_light_onPrimaryContainer,
    secondary = diana_theme_light_secondary,
    onSecondary = diana_theme_light_onSecondary,
    secondaryContainer = diana_theme_light_secondaryContainer,
    onSecondaryContainer = diana_theme_light_onSecondaryContainer,
    tertiary = diana_theme_light_tertiary,
    onTertiary = diana_theme_light_onTertiary,
    tertiaryContainer = diana_theme_light_tertiaryContainer,
    onTertiaryContainer = diana_theme_light_onTertiaryContainer,
    error = diana_theme_light_error,
    errorContainer = diana_theme_light_errorContainer,
    onError = diana_theme_light_onError,
    onErrorContainer = diana_theme_light_onErrorContainer,
    background = diana_theme_light_background,
    onBackground = diana_theme_light_onBackground,
    surface = diana_theme_light_surface,
    onSurface = diana_theme_light_onSurface,
    surfaceVariant = diana_theme_light_surfaceVariant,
    onSurfaceVariant = diana_theme_light_onSurfaceVariant,
    outline = diana_theme_light_outline,
    inverseOnSurface = diana_theme_light_inverseOnSurface,
    inverseSurface = diana_theme_light_inverseSurface,
    inversePrimary = diana_theme_light_inversePrimary,
    surfaceTint = diana_theme_light_surfaceTint,
    outlineVariant = diana_theme_light_outlineVariant,
    scrim = diana_theme_light_scrim,
)


private val DarkColors = darkColorScheme(
    primary = diana_theme_dark_primary,
    onPrimary = diana_theme_dark_onPrimary,
    primaryContainer = diana_theme_dark_primaryContainer,
    onPrimaryContainer = diana_theme_dark_onPrimaryContainer,
    secondary = diana_theme_dark_secondary,
    onSecondary = diana_theme_dark_onSecondary,
    secondaryContainer = diana_theme_dark_secondaryContainer,
    onSecondaryContainer = diana_theme_dark_onSecondaryContainer,
    tertiary = diana_theme_dark_tertiary,
    onTertiary = diana_theme_dark_onTertiary,
    tertiaryContainer = diana_theme_dark_tertiaryContainer,
    onTertiaryContainer = diana_theme_dark_onTertiaryContainer,
    error = diana_theme_dark_error,
    errorContainer = diana_theme_dark_errorContainer,
    onError = diana_theme_dark_onError,
    onErrorContainer = diana_theme_dark_onErrorContainer,
    background = diana_theme_dark_background,
    onBackground = diana_theme_dark_onBackground,
    surface = diana_theme_dark_surface,
    onSurface = diana_theme_dark_onSurface,
    surfaceVariant = diana_theme_dark_surfaceVariant,
    onSurfaceVariant = diana_theme_dark_onSurfaceVariant,
    outline = diana_theme_dark_outline,
    inverseOnSurface = diana_theme_dark_inverseOnSurface,
    inverseSurface = diana_theme_dark_inverseSurface,
    inversePrimary = diana_theme_dark_inversePrimary,
    surfaceTint = diana_theme_dark_surfaceTint,
    outlineVariant = diana_theme_dark_outlineVariant,
    scrim = diana_theme_dark_scrim,
)

@Composable
fun DianaAppTheme(
  useDarkTheme: Boolean = isSystemInDarkTheme(),
  content: @Composable() () -> Unit
) {
  val colors = if (!useDarkTheme) {
    LightColors
  } else {
    DarkColors
  }

  MaterialTheme(
    colorScheme = colors,
    content = content
  )
}