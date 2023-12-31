package com.example.bellavoice.ui.component

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bellavoice.R
import com.example.bellavoice.model.SongBean
import com.example.bellavoice.myutils.LocalNavController
import com.example.bellavoice.viewmodel.SongsViewModel
import kotlinx.coroutines.launch

@Composable
fun MyDropdownMenu(
    expand: Boolean,
    onDismissRequest: () -> Unit,
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit
) {
    DropdownMenu(
        expanded = expand,
        onDismissRequest = onDismissRequest,
        modifier = Modifier.wrapContentHeight(),
    ) {
        DropdownMenuItem(
            text = { Text("Edit") },
            onClick = onClickEdit,
            leadingIcon = {
                Icon(
                    Icons.Outlined.Edit,
                    contentDescription = null
                )
            }
        )
        DropdownMenuItem(
            text = { Text("Delete") },
            onClick = onClickDelete,
            leadingIcon = {
                Icon(
                    Icons.Outlined.Delete,
                    contentDescription = null
                )
            }
        )
    }
}

@Composable
fun VoiceCard2(
    bean: SongBean,
    vm: SongsViewModel
) {
    var expand by remember {
        mutableStateOf(false)
    }
    val navController = LocalNavController.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(MaterialTheme.colorScheme.primaryContainer)
            .clickable {
                vm.playMusic(bean)
                Log.i("===========", "Column Click")
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = bean.uri,
                contentDescription = null,
                placeholder = painterResource(id = R.drawable.album_default),
                error = painterResource(id = R.drawable.album_default),
                modifier = Modifier
                    .size(96.dp, 60.dp)
                    .clip(
                        RoundedCornerShape(20)
                    ),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(start = 5.dp)
                    .weight(1f)
            ) {
                Text(
                    text = bean.song,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 5.dp),
                    maxLines = 1,
                    color = if (bean.exist) MaterialTheme.colorScheme.onPrimaryContainer
                    else Color.LightGray,
                )
                Text(
                    text = bean.singer,
                    fontSize = 14.sp,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Icon(
                imageVector = Icons.Outlined.Favorite,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.outline,
                modifier = Modifier.clickable {
                    Log.i("===========", "Favorite Click")
                }
            )
            Spacer(
                modifier = Modifier.width(15.dp)
            )
            Box {
                Icon(
                    imageVector = Icons.Outlined.MoreHoriz,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.clickable { expand = true }
                )
                MyDropdownMenu(
                    expand = expand,
                    onDismissRequest = { expand = false },
                    onClickEdit = { navController.navigate("ChangeImage/" + bean.id) },
                    onClickDelete = { coroutineScope.launch{ vm.delete(bean) } }
                )
            }
        }
        Divider(modifier = Modifier.fillMaxWidth(), thickness = 1.dp)
    }
}
