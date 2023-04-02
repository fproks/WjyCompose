package com.linhos.wjycompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.linhos.wjycompose.R
import com.linhos.wjycompose.model.entity.MenuItem
import com.linhos.wjycompose.ui.components.TopAppBar


@Preview
@Composable
fun MineScreen() {
    val menus = listOf(
        MenuItem(R.drawable.learning_credits, "学习积分"),
        MenuItem(R.drawable.browsing_history, "浏览记录"),
        MenuItem(R.drawable.work_profile, "学习档案"),
        MenuItem(R.drawable.faq, "常见问题"),
        MenuItem(R.drawable.version, "版本信息"),
        MenuItem(R.drawable.personal_settings, "个人设置"),
        MenuItem(R.drawable.contact_us, "联系我们")
    )
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

            itemsIndexed(menus) { index, menu ->
                if (index == 3) {//中间的间隔
                    Spacer(modifier = Modifier.fillMaxWidth().height(32.dp).background(Color(0xFFF5F5F5)))
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,  //垂直居中
                    modifier = Modifier.padding(8.dp).fillMaxSize()  //row 占满横向空间
                ) {//Row是 在一行内排列
                    Icon(
                        painter = painterResource(menu.icon),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = MaterialTheme.colors.primary
                    )
                    Column(modifier = Modifier.weight(1f).padding(horizontal = 8.dp)) {//按列，往下排列
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween, //两侧放置
                            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),//占满横向空间
                            verticalAlignment = Alignment.CenterVertically //垂直居中
                        ) {
                            Text(menu.title, fontSize = 16.sp, color = Color(0xFF333333))
                            Icon(
                                imageVector = Icons.Default.ArrowForwardIos,
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = Modifier.width(13.dp)
                            )
                        }
                        Divider()
                    }
                }
            }
        }

    }
}