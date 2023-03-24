package com.linhos.wjycompose.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linhos.wjycompose.ui.components.TopAppBar

@Composable
fun StudyScreen() {
    Column {

        /*
        * 学习的标题栏
        * */
        TopAppBar(modifier = Modifier.padding(horizontal = 8.dp)) {

            Surface(
                modifier = Modifier.clip(RoundedCornerShape(16.dp)).weight(1f), //圆角
                color = Color(0x33FFFFFF),  //30%透明度
                contentColor = Color.White  //内容白色
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically  //垂直居中
                ) {
                    Icon(
                        Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(13.dp)
                    )
                    Text(
                        text = "搜索感兴趣的咨询或课程",
                        fontSize = 12.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

            }

            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "学习\n进度", fontSize = 10.sp, color = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "26%", fontSize = 12.sp, color = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White)


        }
        Text(modifier = Modifier.align(Alignment.CenterHorizontally), text = "study screen")
    }
}

@Preview
@Composable
fun StudyScreenPreview() {
    StudyScreen()
}