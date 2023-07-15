package com.example.bellavoice.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bellavoice.component.MyCircularProgressIndicator
import com.example.bellavoice.viewmodel.DownloadViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

@Preview(showBackground = true)
@Composable
fun AddPage(
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val downloadVM by remember {
        mutableStateOf(DownloadViewModel())
    }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        Spacer(
            modifier = Modifier.fillMaxHeight(0.1f)
        )
        Text(
            text = "输入BV号",
            fontSize = 26.sp,
            fontFamily = FontFamily.SansSerif,
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 10.dp, end = 5.dp)
                    .weight(0.7f),
                value = downloadVM.bv,
                onValueChange = { downloadVM.bv = it },
                singleLine = true,
                label = { Text(text = "BV号") },
                textStyle = TextStyle(
                    fontSize = 24.sp,
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 10.dp, start = 5.dp)
                    .weight(0.2f),
                value = downloadVM.part,
                onValueChange = { downloadVM.part = it },
                singleLine = true,
                label = { Text(text = "分p") },
                textStyle = TextStyle(
                    fontSize = 24.sp,
                )
            )
        }


        Button(
            onClick = {
                coroutineScope.launch {
                    downloadVM.getUrl()
                }
            },
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Image(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White)
            )
            Text(text = "解析", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))

        if (downloadVM.loading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MyCircularProgressIndicator(modifier = Modifier.size(300.dp))
            }
        } else {
//            SingleLine(title = "bv号", msg = downloadVM.text)
            MyText(title = "时间", msg = timeStamp2DateStr(downloadVM.result.timestamp))
            MyText(title = "信息", msg = downloadVM.result.message)
            MyText(title = "code", msg = downloadVM.result.status.toString())
            SingleLine(
                title = "url",
                msg = downloadVM.result.data?.urls?.getOrNull(0)?.url ?: "",
                readable = true
            )
            SingleLine(title = "标题", msg = downloadVM.fileName) {
                downloadVM.fileName = it
            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                coroutineScope.launch {
                    downloadVM.downloadPdf(baseActivity = context)
                }
            },
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Image(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                colorFilter = ColorFilter.tint(Color.White)
            )
            Text(text = "下载", fontSize = 20.sp)
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun timeStamp2DateStr(timeStamp: Double): String {

    val timeStamp2 = timeStamp.toLong() * 1000
    val date = Date(timeStamp2)
    val pattern = "yyyy-MM-dd HH:mm:ss"
    val simpleDateFormat = SimpleDateFormat(pattern)
    Log.i(
        "===========time",
        simpleDateFormat.format(date)
    )
    return simpleDateFormat.format(date)
}

@Composable
fun SingleLine(
    title: String = "title",
    msg: String = "msg",
    readable: Boolean = false,
    onValueChange: (String) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(bottom = 15.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title, fontSize = 20.sp,
            modifier = Modifier
                .padding(end = 10.dp)
                .width(60.dp)
        )
        TextField(
            value = msg,
            readOnly = readable,
            onValueChange = onValueChange,
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.weight(1f),
            colors = TextFieldDefaults.colors(unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer)
        )
    }
}

@Composable
fun MyText(
    title: String = "1231231",
    msg: String = "sdasdasdasdas"
) {
    Row(
        modifier = Modifier
            .padding(bottom = 15.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title, fontSize = 20.sp,
            modifier = Modifier
                .padding(end = 10.dp)
                .width(60.dp)
        )
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = RoundedCornerShape(10)
                ),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = msg,
                modifier = Modifier
                    .height(40.dp)
                    .padding(vertical = 5.dp)
                    .padding(start = 5.dp),
                maxLines = 1,
                overflow = TextOverflow.Visible,
                fontSize = 20.sp,
            )
            Divider(thickness = 1.dp, color = Color.DarkGray)
        }
    }
}