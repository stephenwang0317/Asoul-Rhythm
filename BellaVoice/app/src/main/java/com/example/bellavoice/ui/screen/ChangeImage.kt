package com.example.bellavoice.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bellavoice.R
import com.example.bellavoice.model.SongBean
import com.example.bellavoice.myutils.LocalDownloadViewModel
import com.example.bellavoice.myutils.LocalNavController
import com.example.bellavoice.myutils.LocalSongsViewModel
import com.example.bellavoice.ui.theme.ava.ava_theme_light_primaryContainer
import com.example.bellavoice.ui.theme.bella.bella_theme_light_primaryContainer
import com.example.bellavoice.ui.theme.diana.diana_theme_light_primaryContainer
import com.example.bellavoice.ui.theme.elieen.elieen_theme_light_primaryContainer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeImage(
    id: Int
) {
    val navController = LocalNavController.current
    var bv by remember {
        mutableStateOf("")
    }

    val downloadVM = LocalDownloadViewModel.current
    val songVM = LocalSongsViewModel.current
    val coroutineScope = rememberCoroutineScope()
    var bean = SongBean()

    var songName by remember {
        mutableStateOf("")
    }
    var imgUrl by remember {
        mutableStateOf("")
    }
    var songNameChanged by remember {
        mutableStateOf(false)
    }
    var imgUrlChanged by remember {
        mutableStateOf(false)
    }

    val nameChosen = remember {
        mutableStateListOf(false, false, false, false)
    }
    val nameList = arrayListOf("向晚AvA", "贝拉Bella", "嘉然Diana", "乃琳Eileen")

    LaunchedEffect(Unit) {
        bean = songVM.getSingleSong(id)
        songName = bean.song
        imgUrl = bean.uri
        val name = bean.singer.split(", ")
        for (a in name) {
            for (index in 0 until nameList.size) {
                if (a == nameList[index]) {
                    nameChosen[index] = true
                    break
                }
            }
        }
    }


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
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.loading),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                        Text(text = "修改信息", fontSize = 25.sp)
                        Image(
                            painter = painterResource(id = R.drawable.loading),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                    }
                },
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
            modifier = Modifier.padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Text(text = "修改封面", modifier = Modifier.padding(10.dp), fontSize = 20.sp)
            }
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .padding(bottom = 5.dp, start = 10.dp)
                    .fillMaxWidth()
            ) {
                TextField(
                    value = bv,
                    onValueChange = { str -> bv = str },
                    leadingIcon = {
                        Image(imageVector = Icons.Default.Image, contentDescription = null)
                    },
                    label = {
                        Text(text = "bv号")
                    },
                    trailingIcon = {
                        IconButton(onClick = { bv = "" }) {
                            Image(imageVector = Icons.Default.Close, contentDescription = null)
                        }
                    }
                )
                TextButton(
                    onClick = {
                        coroutineScope.launch {
                            val result = downloadVM.getUrl(bv)
                            imgUrl = result.data?.img ?: ""
                            imgUrlChanged = true
                        }
                    }
                ) {
                    Text("解析")
                }
            }
            AsyncImage(
                model = imgUrl,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.album_default),
                error = painterResource(id = R.drawable.album_default),
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .aspectRatio(1.6f)
                    .clip(
                        RoundedCornerShape(10)
                    )
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(10)
                    ),
                contentScale = ContentScale.Crop
            )
            Divider(modifier = Modifier.padding(vertical = 5.dp), thickness = 2.dp)
            EditTextField(
                str = songName,
                label = "名字",
                onChange = { str ->
                    songName = str
                    songNameChanged = true
                },
                onClose = { songName = ""; songNameChanged = true },
            )
            Divider(modifier = Modifier.padding(vertical = 5.dp), thickness = 2.dp)

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Text(text = "演唱者", modifier = Modifier.padding(10.dp), fontSize = 20.sp)

            }
            NameChip(nameChosen)

            Row {
                TextButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Text("取消")
                }
                TextButton(
                    onClick = {
                        coroutineScope.launch {
                            if (imgUrlChanged) bean.uri = imgUrl
                            if (songNameChanged) bean.song = songName
                            val name: StringBuilder = StringBuilder()
                            for (index in 0..3) {
                                val b = nameChosen[index]
                                Log.e("============", "$index, $b")
                                if (b) {
                                    when (index) {
                                        0 -> name.append("向晚AvA, ")
                                        1 -> name.append("贝拉Bella, ")
                                        2 -> name.append("嘉然Diana, ")
                                        else -> name.append("乃琳Eileen")
                                    }
                                }
                            }
                            Log.e("================", name.toString())
                            bean.singer = name.toString()
                            songVM.upsert(bean)
                            navController.popBackStack()
                        }
                    }
                ) {
                    Text("更新")
                }
            }
        }
    }
}

@Composable
fun EditTextField(
    modifier: Modifier = Modifier,
    str: String,
    label: String,
    onChange: (String) -> Unit,
    onClose: () -> Unit
) {
    TextField(
        value = str,
        onValueChange = { onChange(it) },
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 10.dp, vertical = 5.dp
            ),
        trailingIcon = {
            IconButton(onClick = onClose) {
                Image(imageVector = Icons.Default.Close, contentDescription = null)
            }
        },
        label = {
            Text(text = label)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NameChip(
    mutableList: MutableList<Boolean>
) {
    val nameList = arrayListOf("向晚AvA", "贝拉Bella", "嘉然Diana", "乃琳Eileen")
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            repeat(4) { index ->
                InputChip(
                    selected = mutableList[index],
                    onClick = { mutableList[index] = !mutableList[index] },
                    label = { Text(nameList[index]) },
                    avatar = {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = null,
                            Modifier.size(InputChipDefaults.AvatarSize)
                        )
                    },
                    colors = InputChipDefaults.inputChipColors(
                        selectedContainerColor = when (index) {
                            0 -> ava_theme_light_primaryContainer
                            1 -> bella_theme_light_primaryContainer
                            2 -> diana_theme_light_primaryContainer
                            else -> elieen_theme_light_primaryContainer
                        }
                    ),
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}