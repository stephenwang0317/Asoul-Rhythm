package com.example.bellavoice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bellavoice.ui.screen.AddPage
import com.example.bellavoice.ui.screen.MainPage
import com.example.bellavoice.ui.theme.BellaVoiceTheme2
import com.example.bellavoice.myutils.LocalNavController
import com.example.bellavoice.myutils.LocalThemeViewModel
import com.example.bellavoice.viewmodel.ThemeViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(
                LocalNavController provides rememberNavController(),
                LocalThemeViewModel provides ThemeViewModel()
            ) {
                val themeVm = LocalThemeViewModel.current
                BellaVoiceTheme2(themeChoose = themeVm.themeId) { Navigator() }
            }
        }
    }
}

@Composable
fun Navigator() {
    val navController = LocalNavController.current
    NavHost(navController = navController, startDestination = "MainPage") {
        composable("MainPage") { MainPage() }
        composable("AddPage") { AddPage() }
    }

}
