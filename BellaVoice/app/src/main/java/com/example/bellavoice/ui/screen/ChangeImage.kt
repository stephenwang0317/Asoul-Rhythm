package com.example.bellavoice.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.bellavoice.R
import com.example.bellavoice.myutils.LocalDownloadViewModel
import com.example.bellavoice.myutils.LocalNavController
import com.example.bellavoice.myutils.LocalSongsViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeImage(
    id: Int
) {
    val navController = LocalNavController.current
    var bv by remember {
        mutableStateOf("")
    }
    var img_url by remember {
        mutableStateOf("")
    }

    val downloadVM = LocalDownloadViewModel.current
    val songVM = LocalSongsViewModel.current
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Image(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onPrimary)
                        )
                    }
                },
                title = { Text(text = "修改封面") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                windowInsets = TopAppBarDefaults.windowInsets,
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MainPageTitle(title = "修改封面")
            TextField(
                value = bv,
                onValueChange = { bv = it },
                leadingIcon = {
                    Image(imageVector = Icons.Default.Image, contentDescription = null)
                },
                label = {
                    Text(text = "bv号")
                }
            )
            AsyncImage(
                model = img_url,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.album_default),
                error = painterResource(id = R.drawable.album_default),
                modifier = Modifier
                    .aspectRatio(1.6f)
                    .clip(
                        RoundedCornerShape(20)
                    ),
                contentScale = ContentScale.Crop
            )
            Row() {
                TextButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Text("取消")
                }
                TextButton(
                    onClick = {
                        coroutineScope.launch {
                            val result = downloadVM.getUrl(bv)
                            img_url = result.data?.img ?: ""
                        }
                    }
                ) {
                    Text("解析")
                }
                TextButton(
                    onClick = {
                        coroutineScope.launch {
                            val bean = songVM.getSingleSong(id)
                            bean.uri = img_url
                            songVM.upsert(bean)
                        }
                    }
                ) {
                    Text("更新")
                }
            }
        }
    }
}