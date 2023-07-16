package com.example.bellavoice.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Equalizer
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bellavoice.model.SongBean
import com.example.bellavoice.viewmodel.SongsViewModel


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VoiceCard(
    modifier: Modifier = Modifier,
    bean: SongBean,
    vm: SongsViewModel,
    selfIndex: Int
) {
    var expand by remember {
        mutableStateOf(false)
    }

    ElevatedCard(
        modifier = modifier
            .defaultMinSize(
                minHeight = 100.dp
            )
            .combinedClickable(
                onClick = { vm.playMusic(bean) },
                onLongClick = {
                    expand = true
                }
            ),
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
        if (selfIndex == vm.getCurrent()) {
            Row() {
                for (i in 1..3) {
                    Image(imageVector = Icons.Default.Equalizer, contentDescription = null)
                }
            }
        } else {
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
        Row(modifier=Modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.weight(1f))
            Box {
                MyDropdownMenu(expand = expand) { expand = false }
            }
        }
    }
}

@Composable
fun MyDropdownMenu(
    expand: Boolean,
    onDismissRequest: () -> Unit
) {
    DropdownMenu(
        expanded = expand,
        onDismissRequest = onDismissRequest,
        modifier = Modifier.wrapContentHeight(),
    ) {
        DropdownMenuItem(
            text = { Text("Edit") },
            onClick = { /* Handle edit! */ },
            leadingIcon = {
                Icon(
                    Icons.Outlined.Edit,
                    contentDescription = null
                )
            }
        )
        DropdownMenuItem(
            text = { Text("Delete") },
            onClick = { /* Handle edit! */ },
            leadingIcon = {
                Icon(
                    Icons.Outlined.Delete,
                    contentDescription = null
                )
            }
        )
        DropdownMenuItem(
            text = { Text("Favorite") },
            onClick = { /* Handle edit! */ },
            leadingIcon = {
                Icon(
                    Icons.Outlined.Favorite,
                    contentDescription = null
                )
            }
        )
    }
}