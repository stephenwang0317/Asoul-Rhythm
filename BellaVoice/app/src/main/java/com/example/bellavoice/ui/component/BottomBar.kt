package com.example.bellavoice.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.bellavoice.viewmodel.SongsViewModel

@Composable
fun BottomBar(
    vm: SongsViewModel
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .defaultMinSize(0.dp, 80.dp)
            .padding(horizontal = 10.dp)
            .padding(bottom = 10.dp)
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(20)
            )
            .clickable(enabled = false) { }
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(20)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))
        BottomIcon(imageVector = Icons.Default.SkipPrevious) {
            vm.previousSong()
        }
        BottomIcon(imageVector = if (vm.isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow) {
            if (vm.isPlaying) vm.stopPlay()
            else vm.resumePlay()
        }
        BottomIcon(imageVector = Icons.Default.SkipNext) {
            vm.nextSong()
        }

    }
}


@Composable
fun BottomIcon(
    imageVector: ImageVector,
    onClickAction: () -> Unit = {}
) {
    Image(
        imageVector = imageVector,
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
            .padding(5.dp)
            .clickable { onClickAction() },
        colorFilter = ColorFilter.tint(
            MaterialTheme.colorScheme.onSecondary
        )
    )
}

