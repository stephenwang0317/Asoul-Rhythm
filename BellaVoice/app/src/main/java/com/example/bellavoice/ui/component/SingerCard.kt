package com.example.bellavoice.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.bellavoice.R

@Composable

fun SingerCard(
    modifier: Modifier = Modifier,
    painter: Painter,
    name: String,
    height: Dp = 60.dp
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10))
            .height(height)
            .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(10)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
//            modifier = Modifier.size(60.dp)
        )
    }
}

@Composable
//@Preview(showBackground = true)
fun SingerGrid(
    modifier: Modifier = Modifier,
    expand: Boolean
) {
    val paddingValues = PaddingValues(vertical = 5.dp)

    val temModifier = when (expand) {
        true -> Modifier
            .padding(paddingValues)
            .aspectRatio(1f)

        else -> Modifier.padding(paddingValues)
    }



    Column(modifier = modifier) {
        Row(Modifier.fillMaxWidth()) {
            SingerCard(
                modifier = temModifier
                    .padding(end = 5.dp)
                    .weight(1f),
                painter = painterResource(id = R.drawable.avatar_a),
                name = "向晚"
            )
            SingerCard(
                modifier = temModifier
                    .padding(start = 5.dp)
                    .weight(1f),
                painter = painterResource(id = R.drawable.avatar_b),
                name = "贝拉"
            )
        }
        Row(Modifier.fillMaxWidth()) {
            SingerCard(
                modifier = temModifier
                    .padding(end = 5.dp)
                    .weight(1f),
                painter = painterResource(id = R.drawable.avatar_d),
                name = "嘉然"
            )
            SingerCard(
                modifier = temModifier
                    .padding(start = 5.dp)
                    .weight(1f),
                painter = painterResource(id = R.drawable.avatar_e),
                name = "乃琳"
            )
        }
    }
}

