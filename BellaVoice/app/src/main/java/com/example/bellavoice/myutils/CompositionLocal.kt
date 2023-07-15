package com.example.bellavoice.myutils

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import com.example.bellavoice.viewmodel.ThemeViewModel


val LocalNavController =
    compositionLocalOf<NavHostController> { error("NavHostController Context Not Found") }

val LocalOpenDelay =
    compositionLocalOf<Boolean> { error("Open Delay Context Not Found") }

val LocalThemeViewModel =
    compositionLocalOf<ThemeViewModel> { error("Theme ViewModel Not Found") }