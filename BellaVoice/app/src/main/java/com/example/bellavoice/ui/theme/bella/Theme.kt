package com.example.bellavoice.ui.theme.bella

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable


private val LightColors = lightColorScheme(
    primary = bella_theme_light_primary,
    onPrimary = bella_theme_light_onPrimary,
    primaryContainer = bella_theme_light_primaryContainer,
    onPrimaryContainer = bella_theme_light_onPrimaryContainer,
    secondary = bella_theme_light_secondary,
    onSecondary = bella_theme_light_onSecondary,
    secondaryContainer = bella_theme_light_secondaryContainer,
    onSecondaryContainer = bella_theme_light_onSecondaryContainer,
    tertiary = bella_theme_light_tertiary,
    onTertiary = bella_theme_light_onTertiary,
    tertiaryContainer = bella_theme_light_tertiaryContainer,
    onTertiaryContainer = bella_theme_light_onTertiaryContainer,
    error = bella_theme_light_error,
    errorContainer = bella_theme_light_errorContainer,
    onError = bella_theme_light_onError,
    onErrorContainer = bella_theme_light_onErrorContainer,
    background = bella_theme_light_background,
    onBackground = bella_theme_light_onBackground,
    surface = bella_theme_light_surface,
    onSurface = bella_theme_light_onSurface,
    surfaceVariant = bella_theme_light_surfaceVariant,
    onSurfaceVariant = bella_theme_light_onSurfaceVariant,
    outline = bella_theme_light_outline,
    inverseOnSurface = bella_theme_light_inverseOnSurface,
    inverseSurface = bella_theme_light_inverseSurface,
    inversePrimary = bella_theme_light_inversePrimary,
    surfaceTint = bella_theme_light_surfaceTint,
    outlineVariant = bella_theme_light_outlineVariant,
    scrim = bella_theme_light_scrim,
)


private val DarkColors = darkColorScheme(
    primary = bella_theme_dark_primary,
    onPrimary = bella_theme_dark_onPrimary,
    primaryContainer = bella_theme_dark_primaryContainer,
    onPrimaryContainer = bella_theme_dark_onPrimaryContainer,
    secondary = bella_theme_dark_secondary,
    onSecondary = bella_theme_dark_onSecondary,
    secondaryContainer = bella_theme_dark_secondaryContainer,
    onSecondaryContainer = bella_theme_dark_onSecondaryContainer,
    tertiary = bella_theme_dark_tertiary,
    onTertiary = bella_theme_dark_onTertiary,
    tertiaryContainer = bella_theme_dark_tertiaryContainer,
    onTertiaryContainer = bella_theme_dark_onTertiaryContainer,
    error = bella_theme_dark_error,
    errorContainer = bella_theme_dark_errorContainer,
    onError = bella_theme_dark_onError,
    onErrorContainer = bella_theme_dark_onErrorContainer,
    background = bella_theme_dark_background,
    onBackground = bella_theme_dark_onBackground,
    surface = bella_theme_dark_surface,
    onSurface = bella_theme_dark_onSurface,
    surfaceVariant = bella_theme_dark_surfaceVariant,
    onSurfaceVariant = bella_theme_dark_onSurfaceVariant,
    outline = bella_theme_dark_outline,
    inverseOnSurface = bella_theme_dark_inverseOnSurface,
    inverseSurface = bella_theme_dark_inverseSurface,
    inversePrimary = bella_theme_dark_inversePrimary,
    surfaceTint = bella_theme_dark_surfaceTint,
    outlineVariant = bella_theme_dark_outlineVariant,
    scrim = bella_theme_dark_scrim,
)

@Composable
fun BellaAppTheme(
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