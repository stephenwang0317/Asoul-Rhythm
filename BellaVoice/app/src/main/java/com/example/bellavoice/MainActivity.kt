package com.example.bellavoice

import android.app.DownloadManager
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bellavoice.myutils.LocalDownloadViewModel
import com.example.bellavoice.myutils.LocalNavController
import com.example.bellavoice.myutils.LocalSongsViewModel
import com.example.bellavoice.myutils.LocalThemeViewModel
import com.example.bellavoice.myutils.MyBroadcastReceiver
import com.example.bellavoice.myutils.ShortUrltoLong
import com.example.bellavoice.ui.screen.AddPage
import com.example.bellavoice.ui.screen.ChangeImage
import com.example.bellavoice.ui.screen.MainPage
import com.example.bellavoice.ui.theme.BellaVoiceTheme2
import com.example.bellavoice.viewmodel.DownloadViewModel
import com.example.bellavoice.viewmodel.SongsViewModel
import com.example.bellavoice.viewmodel.ThemeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainActivity : ComponentActivity() {

    private var songsViewModel: SongsViewModel? = null
    private var navController: NavHostController? = null
    private var downloadViewModel = DownloadViewModel()

    private val myReceiver = MyBroadcastReceiver(downloadViewModel)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(
                LocalNavController provides rememberNavController(),
                LocalThemeViewModel provides ThemeViewModel(LocalContext.current),
                LocalSongsViewModel provides SongsViewModel(LocalContext.current),
                LocalDownloadViewModel provides downloadViewModel
            ) {
                songsViewModel = LocalSongsViewModel.current
                navController = LocalNavController.current
                val themeVm = LocalThemeViewModel.current
                BellaVoiceTheme2(themeChoose = themeVm.themeId) { Navigator() }
            }
        }
        registerReceiver(myReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
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
                val regex = Regex("https://[a-zA-Z0-9./?=_-]+")
                Log.d("==========", cd.toString())
                val matches = regex.findAll(cd.toString())
                // 遍历并打印匹配的链接
                for (match in matches) {
                    val url = match.value
                    Log.d("===============", "提取到的HTTPS链接：$url")

                    lifecycleScope.launch {
                        val longUrl = withContext(Dispatchers.IO) { ShortUrltoLong(url) }
                        Log.d("===============", "解析到的HTTPS链接：$longUrl")
                        downloadViewModel.bv = longUrl
                        navController?.navigate("AddPage")
                    }

                    break
                }
            }
        }
    }

    override fun onResume() {
        registerReceiver(myReceiver, IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))
        super.onResume()
    }

    override fun onPause() {
        unregisterReceiver(myReceiver)
        super.onPause()
    }

    override fun onDestroy() {
        songsViewModel?.release()
        unregisterReceiver(myReceiver)
        super.onDestroy()
    }
}

@Composable
fun Navigator() {
    val navController = LocalNavController.current
    NavHost(navController = navController, startDestination = "MainPage") {
        composable("MainPage") { MainPage() }
        composable("AddPage") { AddPage() }
        composable(
            route = "ChangeImage/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            ChangeImage(id = it.arguments?.getInt("id") ?: 0)
        }
    }
}
