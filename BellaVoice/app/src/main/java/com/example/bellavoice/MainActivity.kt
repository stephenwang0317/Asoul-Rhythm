package com.example.bellavoice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bellavoice.Utils.LocalNavController
import com.example.bellavoice.ui.screen.AddPage
import com.example.bellavoice.ui.screen.MainPage
import com.example.bellavoice.ui.theme.ava.AvaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AvaTheme() { Navigator() }
        }
    }

}

@Composable
fun Navigator() {
    CompositionLocalProvider(
        LocalNavController provides rememberNavController()
    ) {
        val navController = LocalNavController.current
        NavHost(navController = navController, startDestination = "MainPage") {
            composable("MainPage") { MainPage() }
            composable("AddPage") { AddPage() }
        }
    }
}
