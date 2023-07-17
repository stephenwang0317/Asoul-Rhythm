package com.example.bellavoice.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bellavoice.R

@Composable

fun SingerCard(
    modifier: Modifier = Modifier,
    painter: Painter,
    name: String
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10))
            .height(60.dp)
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(10)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
//            modifier = Modifier.size(60.dp)
        )
        Text(text = name, modifier = Modifier.padding(start = 5.dp))
    }
}

@Composable
@Preview(showBackground = true)
fun SingerGrid(modifier: Modifier = Modifier) {
    val paddingValues = PaddingValues(5.dp)
    Column(modifier = modifier) {
        Row(Modifier.fillMaxWidth()) {
            SingerCard(
                modifier = Modifier
                    .weight(1f)
                    .padding(paddingValues),
                painter = painterResource(id = R.drawable.avatar_a),
                name = "向晚"
            )
            SingerCard(
                modifier = Modifier
                    .weight(1f)
                    .padding(paddingValues),
                painter = painterResource(id = R.drawable.avatar_b),
                name = "贝拉"
            )
        }
        Row(Modifier.fillMaxWidth()) {
            SingerCard(
                modifier = Modifier
                    .weight(1f)
                    .padding(paddingValues),
                painter = painterResource(id = R.drawable.avatar_d),
                name = "嘉然"
            )
            SingerCard(
                modifier = Modifier
                    .weight(1f)
                    .padding(paddingValues),
                painter = painterResource(id = R.drawable.avatar_e),
                name = "乃琳"
            )
        }
    }
}