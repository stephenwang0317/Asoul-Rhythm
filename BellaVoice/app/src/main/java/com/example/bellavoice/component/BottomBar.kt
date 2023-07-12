package com.example.bellavoice.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bellavoice.viewmodel.SongsViewModel

@Composable
//@Preview(showBackground = true)
fun BottomBar(
    vm: SongsViewModel
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .defaultMinSize(0.dp, 80.dp)
            .background(Color.LightGray),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically, //设置垂直方向对齐
            horizontalArrangement = Arrangement.spacedBy(10.dp) //设置子项的间距
        ) {
            Image(
                imageVector = if (vm.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        if (vm.isPlaying) vm.stopPlay()
                        else vm.resumePlay()
                    }
            )
        }
    }
}
