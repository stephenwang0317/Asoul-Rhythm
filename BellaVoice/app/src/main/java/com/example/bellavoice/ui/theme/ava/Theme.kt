package com.example.bellavoice.ui.theme.ava

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable


private val LightColors = lightColorScheme(
    primary = ava_theme_light_primary,
    onPrimary = ava_theme_light_onPrimary,
    primaryContainer = ava_theme_light_primaryContainer,
    onPrimaryContainer = ava_theme_light_onPrimaryContainer,
    secondary = ava_theme_light_secondary,
    onSecondary = ava_theme_light_onSecondary,
    secondaryContainer = ava_theme_light_secondaryContainer,
    onSecondaryContainer = ava_theme_light_onSecondaryContainer,
    tertiary = ava_theme_light_tertiary,
    onTertiary = ava_theme_light_onTertiary,
    tertiaryContainer = ava_theme_light_tertiaryContainer,
    onTertiaryContainer = ava_theme_light_onTertiaryContainer,
    error = ava_theme_light_error,
    errorContainer = ava_theme_light_errorContainer,
    onError = ava_theme_light_onError,
    onErrorContainer = ava_theme_light_onErrorContainer,
    background = ava_theme_light_background,
    onBackground = ava_theme_light_onBackground,
    surface = ava_theme_light_surface,
    onSurface = ava_theme_light_onSurface,
    surfaceVariant = ava_theme_light_surfaceVariant,
    onSurfaceVariant = ava_theme_light_onSurfaceVariant,
    outline = ava_theme_light_outline,
    inverseOnSurface = ava_theme_light_inverseOnSurface,
    inverseSurface = ava_theme_light_inverseSurface,
    inversePrimary = ava_theme_light_inversePrimary,
    surfaceTint = ava_theme_light_surfaceTint,
    outlineVariant = ava_theme_light_outlineVariant,
    scrim = ava_theme_light_scrim,
)


private val DarkColors = darkColorScheme(
    primary = ava_theme_dark_primary,
    onPrimary = ava_theme_dark_onPrimary,
    primaryContainer = ava_theme_dark_primaryContainer,
    onPrimaryContainer = ava_theme_dark_onPrimaryContainer,
    secondary = ava_theme_dark_secondary,
    onSecondary = ava_theme_dark_onSecondary,
    secondaryContainer = ava_theme_dark_secondaryContainer,
    onSecondaryContainer = ava_theme_dark_onSecondaryContainer,
    tertiary = ava_theme_dark_tertiary,
    onTertiary = ava_theme_dark_onTertiary,
    tertiaryContainer = ava_theme_dark_tertiaryContainer,
    onTertiaryContainer = ava_theme_dark_onTertiaryContainer,
    error = ava_theme_dark_error,
    errorContainer = ava_theme_dark_errorContainer,
    onError = ava_theme_dark_onError,
    onErrorContainer = ava_theme_dark_onErrorContainer,
    background = ava_theme_dark_background,
    onBackground = ava_theme_dark_onBackground,
    surface = ava_theme_dark_surface,
    onSurface = ava_theme_dark_onSurface,
    surfaceVariant = ava_theme_dark_surfaceVariant,
    onSurfaceVariant = ava_theme_dark_onSurfaceVariant,
    outline = ava_theme_dark_outline,
    inverseOnSurface = ava_theme_dark_inverseOnSurface,
    inverseSurface = ava_theme_dark_inverseSurface,
    inversePrimary = ava_theme_dark_inversePrimary,
    surfaceTint = ava_theme_dark_surfaceTint,
    outlineVariant = ava_theme_dark_outlineVariant,
    scrim = ava_theme_dark_scrim,
)

@Composable
fun AvaTheme(
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