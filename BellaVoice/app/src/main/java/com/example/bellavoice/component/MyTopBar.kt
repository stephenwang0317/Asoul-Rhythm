package com.example.bellavoice.component

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import com.example.bellavoice.R
import com.example.bellavoice.Utils.LocalNavController
import com.example.bellavoice.viewmodel.SongsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar() {
    val navController = LocalNavController.current
    val context = LocalContext.current
    val songVM = SongsViewModel()
    val coroutineScope = rememberCoroutineScope()
    val refreshLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGrant ->
        if (isGrant) {
            coroutineScope.launch { songVM.loadLocalSongs() }
        } else {
            Toast.makeText(context, "请授予权限", Toast.LENGTH_SHORT).show()
        }
    }

    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = { navController.navigate("AddPage") }) {
                Image(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },
        title = {
            Row() {
                Image(painter = painterResource(id = R.drawable._ava), contentDescription = null)
                Image(painter = painterResource(id = R.drawable._bella), contentDescription = null)
                Image(painter = painterResource(id = R.drawable._diana), contentDescription = null)
                Image(painter = painterResource(id = R.drawable._elieen), contentDescription = null)
            }
        },
        windowInsets = TopAppBarDefaults.windowInsets,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.LightGray
        ),
        actions = {
            IconButton(
                onClick = {
                    when {
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ) == PackageManager.PERMISSION_GRANTED -> {
                            coroutineScope.launch { songVM.loadLocalSongs() }
                        }

                        else -> {
                            refreshLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                        }
                    }
                },
            ) {
                Image(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Black)
                )
            }
        }

    )
}