package com.example.bellavoice.Utils

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController


val LocalNavController =
    compositionLocalOf<NavHostController> { error("NavHostController Context Not Found") }

val LocalOpenDelay =
    compositionLocalOf<Boolean> { error("Open Delay Context Not Found") }
