package com.example.bellavoice.ui.screen

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun MainPage(
    modifier: Modifier = Modifier
) {
    val songVM = LocalSongsViewModel.current
    val targetSong = songVM.targetSong
    val lazyListState = rememberLazyListState()

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
            songList = targetSong,
            lazyListState = lazyListState,
        )
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
fun MainPageContent(
    contentPaddingValues: PaddingValues,
    songsViewModel: SongsViewModel,
    songList: MutableList<SongBean>,
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

            item { MainPageTitle(title = "播放列表") }
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
