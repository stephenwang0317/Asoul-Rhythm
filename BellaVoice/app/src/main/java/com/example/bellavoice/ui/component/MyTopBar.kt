package com.example.bellavoice.ui.component

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.core.content.ContextCompat
import com.example.bellavoice.R
import com.example.bellavoice.myutils.LocalNavController
import com.example.bellavoice.myutils.LocalThemeViewModel
import com.example.bellavoice.viewmodel.SongsViewModel
import com.example.bellavoice.viewmodel.ThemeViewModel
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
    val themeVM = LocalThemeViewModel.current

    CenterAlignedTopAppBar(
        navigationIcon = {
            IconButton(onClick = { navController.navigate("AddPage") }) {
                Image(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary)
                )
            }
        },
        title = {
            Row() {
                TopBarTitleImage(
                    drawableId = R.drawable._ava,
                    themeVM = themeVM
                ) { themeVM.changeTheme(1) }
                TopBarTitleImage(
                    drawableId = R.drawable._bella,
                    themeVM = themeVM
                ) { themeVM.changeTheme(2) }
                TopBarTitleImage(
                    drawableId = R.drawable._diana,
                    themeVM = themeVM
                ) { themeVM.changeTheme(3) }
                TopBarTitleImage(
                    drawableId = R.drawable._elieen,
                    themeVM = themeVM
                ) { themeVM.changeTheme(4) }
            }
        },
        windowInsets = TopAppBarDefaults.windowInsets,
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
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary)
                )
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun TopBarTitleImage(
    drawableId: Int,
    themeVM: ThemeViewModel,
    onClick: () -> Unit
) {
    Image(
        painter = painterResource(id = drawableId),
        contentDescription = null,
        modifier = Modifier.clickable(onClick = onClick)
    )
}