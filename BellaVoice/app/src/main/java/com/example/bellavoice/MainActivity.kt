package com.example.bellavoice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.util.Consumer
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bellavoice.ui.screen.AddPage
import com.example.bellavoice.ui.screen.MainPage
import com.example.bellavoice.ui.theme.BellaVoiceTheme2
import com.example.bellavoice.myutils.LocalNavController
import com.example.bellavoice.myutils.LocalSongsViewModel
import com.example.bellavoice.myutils.LocalThemeViewModel
import com.example.bellavoice.viewmodel.SongsViewModel
import com.example.bellavoice.viewmodel.ThemeViewModel


class MainActivity : ComponentActivity() {

    private var songsViewModel: SongsViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(
                LocalNavController provides rememberNavController(),
                LocalThemeViewModel provides ThemeViewModel(LocalContext.current),
                LocalSongsViewModel provides SongsViewModel(LocalContext.current)
            ) {
                songsViewModel = LocalSongsViewModel.current
                val themeVm = LocalThemeViewModel.current
                BellaVoiceTheme2(themeChoose = themeVm.themeId) { Navigator() }
            }
        }

        processExtraData()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        processExtraData()
    }

    private fun processExtraData() {
        intent.let { it ->
            it.clipData.let { cd ->

                Log.e("===========", cd?.getItemAt(0).toString())
            }
        }
    }

    override fun onDestroy() {
        songsViewModel?.release()
        super.onDestroy()
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
