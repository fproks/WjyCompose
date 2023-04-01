package com.linhos.wjycompose.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.linhos.wjycompose.ui.components.TopAppBar


@Preview
@Composable
fun MineScreen() {
    Column(modifier = Modifier) {
        TopAppBar {
            Text(
                "我的",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
        //"https://robohash.org/YOUR-TEXT.png
        LazyColumn {
            //avatar 头像
            item {
                Row(modifier = Modifier.padding(16.dp)) {
                    AsyncImage(
                        model = "https://picsum.photos/200/300?random=11",
                        contentDescription = null,
                        modifier = Modifier.size(62.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.height(62.dp).padding(horizontal = 8.dp)
                    ) {
                        Text(
                            "未登录",
                            fontSize = 18.sp,
                            color = Color(0xff333333)
                        )
                        Text("已坚持学习0天", fontSize = 12.sp, color = Color(0xff999999))

                    }
                }
            }
        }

    }
}