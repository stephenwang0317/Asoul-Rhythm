package com.example.bellavoice.theme.elieen

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable


private val LightColors = lightColorScheme(
    primary = elieen_theme_light_primary,
    onPrimary = elieen_theme_light_onPrimary,
    primaryContainer = elieen_theme_light_primaryContainer,
    onPrimaryContainer = elieen_theme_light_onPrimaryContainer,
    secondary = elieen_theme_light_secondary,
    onSecondary = elieen_theme_light_onSecondary,
    secondaryContainer = elieen_theme_light_secondaryContainer,
    onSecondaryContainer = elieen_theme_light_onSecondaryContainer,
    tertiary = elieen_theme_light_tertiary,
    onTertiary = elieen_theme_light_onTertiary,
    tertiaryContainer = elieen_theme_light_tertiaryContainer,
    onTertiaryContainer = elieen_theme_light_onTertiaryContainer,
    error = elieen_theme_light_error,
    errorContainer = elieen_theme_light_errorContainer,
    onError = elieen_theme_light_onError,
    onErrorContainer = elieen_theme_light_onErrorContainer,
    background = elieen_theme_light_background,
    onBackground = elieen_theme_light_onBackground,
    surface = elieen_theme_light_surface,
    onSurface = elieen_theme_light_onSurface,
    surfaceVariant = elieen_theme_light_surfaceVariant,
    onSurfaceVariant = elieen_theme_light_onSurfaceVariant,
    outline = elieen_theme_light_outline,
    inverseOnSurface = elieen_theme_light_inverseOnSurface,
    inverseSurface = elieen_theme_light_inverseSurface,
    inversePrimary = elieen_theme_light_inversePrimary,
    surfaceTint = elieen_theme_light_surfaceTint,
    outlineVariant = elieen_theme_light_outlineVariant,
    scrim = elieen_theme_light_scrim,
)


private val DarkColors = darkColorScheme(
    primary = elieen_theme_dark_primary,
    onPrimary = elieen_theme_dark_onPrimary,
    primaryContainer = elieen_theme_dark_primaryContainer,
    onPrimaryContainer = elieen_theme_dark_onPrimaryContainer,
    secondary = elieen_theme_dark_secondary,
    onSecondary = elieen_theme_dark_onSecondary,
    secondaryContainer = elieen_theme_dark_secondaryContainer,
    onSecondaryContainer = elieen_theme_dark_onSecondaryContainer,
    tertiary = elieen_theme_dark_tertiary,
    onTertiary = elieen_theme_dark_onTertiary,
    tertiaryContainer = elieen_theme_dark_tertiaryContainer,
    onTertiaryContainer = elieen_theme_dark_onTertiaryContainer,
    error = elieen_theme_dark_error,
    errorContainer = elieen_theme_dark_errorContainer,
    onError = elieen_theme_dark_onError,
    onErrorContainer = elieen_theme_dark_onErrorContainer,
    background = elieen_theme_dark_background,
    onBackground = elieen_theme_dark_onBackground,
    surface = elieen_theme_dark_surface,
    onSurface = elieen_theme_dark_onSurface,
    surfaceVariant = elieen_theme_dark_surfaceVariant,
    onSurfaceVariant = elieen_theme_dark_onSurfaceVariant,
    outline = elieen_theme_dark_outline,
    inverseOnSurface = elieen_theme_dark_inverseOnSurface,
    inverseSurface = elieen_theme_dark_inverseSurface,
    inversePrimary = elieen_theme_dark_inversePrimary,
    surfaceTint = elieen_theme_dark_surfaceTint,
    outlineVariant = elieen_theme_dark_outlineVariant,
    scrim = elieen_theme_dark_scrim,
)

@Composable
fun ElieenAppTheme(
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