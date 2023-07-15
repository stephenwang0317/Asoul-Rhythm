package com.example.bellavoice.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Equalizer
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bellavoice.model.SongBean
import com.example.bellavoice.viewmodel.SongsViewModel


@Composable
fun VoiceCard(
    modifier: Modifier = Modifier,
    bean: SongBean,
    vm: SongsViewModel,
    selfIndex: Int
) {
    ElevatedCard(
        modifier = modifier
            .defaultMinSize(
                minHeight = 100.dp
            )
            .clickable { vm.playMusic(bean) },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
    ) {
        Text(
            text = bean.song,
            fontSize = 20.sp,
            modifier = Modifier.padding(
                top = 5.dp,
                start = 10.dp,
                end = 10.dp
            ),
        )
        if (selfIndex == vm.getCurrent()){
            Row() {
                for(i in 1..3) {
                    Image(imageVector = Icons.Default.Equalizer, contentDescription = null)}
            }
        }
        else{
            Text(
                text = "Description Description Description Description",
                fontSize = 15.sp,
                modifier = Modifier.padding(
                    start = 10.dp,
                    end = 10.dp,
                    bottom = 5.dp
                )
            )
        }
    }
}