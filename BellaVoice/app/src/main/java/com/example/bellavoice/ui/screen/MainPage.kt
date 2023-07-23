package com.example.bellavoice.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bellavoice.model.SongBean
import com.example.bellavoice.myutils.LocalSongsViewModel
import com.example.bellavoice.ui.component.BottomBar
import com.example.bellavoice.ui.component.MyTopBar
import com.example.bellavoice.ui.component.SingerGrid
import com.example.bellavoice.ui.component.VoiceCard2
import com.example.bellavoice.viewmodel.SongsViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun MainPage(
    modifier: Modifier = Modifier
) {
    val songVM = LocalSongsViewModel.current
    val lazyListState = rememberLazyListState()
    val songState = songVM.songsState.collectAsState()

    LaunchedEffect(Unit) {
        songVM.loadLocalSongs()
        songVM.searchSong("")
    }

    Scaffold(
        topBar = { MyTopBar() },
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = Modifier.background(MaterialTheme.colorScheme.primaryContainer),
        bottomBar = { BottomBar(vm = songVM) }
    ) {
        MainPageContent(
            contentPaddingValues = it,
            songsViewModel = songVM,
            songList = songState.value.songs,
            lazyListState = lazyListState,
        )
    }
}

@Composable
fun MainPageContent(
    contentPaddingValues: PaddingValues,
    songsViewModel: SongsViewModel,
    songList: List<SongBean>,
    lazyListState: LazyListState
) {
    var picExpand by remember { mutableStateOf(false) }
    LazyColumn(
        content = {

            item {
                SingerHeader(
                    expand = picExpand,
                    textClick = { picExpand = !picExpand }
                )
            }

            item { PlayListTitle(songsViewModel = songsViewModel) }
            itemsIndexed(songList) { index, bean ->
                VoiceCard2(
                    bean = bean,
                    vm = songsViewModel
                )
            }
        },
        verticalArrangement = Arrangement.spacedBy(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(horizontal = 15.dp),

        state = lazyListState,
        contentPadding = contentPaddingValues
    )
}

@Composable
fun SingerHeader(
    expand: Boolean,
    textClick: () -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        MainPageTitle(
            title = "ASOUL时代，沸腾期待",
            modifier = Modifier.clickable { textClick() })
        SingerGrid(expand = expand)
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
fun MainPageTitle(
    modifier: Modifier = Modifier,
    title: String = "歌曲列表",
    otherComponent: @Composable () -> Unit = {},
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
        )
        otherComponent()
    }
}

@Composable
fun PlayListTitle(
    modifier: Modifier = Modifier,
    songsViewModel: SongsViewModel
) {

    val sortType = arrayOf("id", "歌手", "歌名")
    var choose by remember {
        mutableStateOf(0)
    }
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "歌曲列表",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Row(
            modifier = Modifier.padding(end = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    choose = if (choose + 1 < sortType.size) choose + 1 else 0
                    when (choose) {
                        0 -> coroutineScope.launch { songsViewModel.getAllById() }
                        1 -> coroutineScope.launch { songsViewModel.getAllBySinger() }
                        else -> coroutineScope.launch { songsViewModel.getAllBySong() }
                    }
                },
                modifier = Modifier
                    .padding(end = 5.dp)
                    .size(20.dp)
            ) {
                Image(imageVector = Icons.Default.Sort, contentDescription = null)
            }
            Text(text = sortType[choose], fontSize = 20.sp)
        }
    }
}