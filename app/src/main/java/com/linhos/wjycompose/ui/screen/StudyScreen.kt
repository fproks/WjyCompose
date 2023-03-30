package com.linhos.wjycompose.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.linhos.wjycompose.ui.components.NotificationContent
import com.linhos.wjycompose.ui.components.TopAppBar
import com.linhos.wjycompose.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

@OptIn(ExperimentalPagerApi::class)
@Composable
fun StudyScreen(viewModel: MainViewModel = viewModel()) {
    Column {

        StudystatusBar()

        CategoryTab(viewModel)
        TypesRowTab(viewModel)
        Swiper(viewModel)
        NotificationContent(viewModel)

    }
}


/*
      * 学习的标题栏
      * */
@Composable
fun StudystatusBar() {
    TopAppBar(modifier = Modifier.padding(horizontal = 8.dp)) {
        Row {
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
    }
}

/*
* 分类目录
* */
@Composable
fun CategoryTab(viewModel: MainViewModel = viewModel()) {
    Row {
        TabRow(
            selectedTabIndex = viewModel.studyCategoryIndex,
            backgroundColor = Color(0x22149EE7),
            contentColor = Color(0xFF149EE7)
        ) {
            viewModel.categorys.forEachIndexed { index, category ->
                LeadingIconTab(
                    selected = index == viewModel.studyCategoryIndex,
                    onClick = { viewModel.updateStudyCategoryIndex(index) },
                    icon = { Icon(imageVector = category.icon, contentDescription = null) },
                    text = {
                        Text(
                            text = category.title,
                            fontSize = 14.sp
                        )
                    },
                    selectedContentColor = Color(0xFF149EE7),
                    unselectedContentColor = Color(0xFF666666)
                )
            }
        }
    }
}

@Preview
@Composable
fun TypesRowTab(viewModel: MainViewModel = viewModel()) {
    Row {
        TabRow(
            selectedTabIndex = viewModel.typesIndex,
            backgroundColor = Color.Transparent,
            contentColor = Color(0xFF149EE7),
            indicator = {},
            divider = {}  //分割线为空
        ) {
            viewModel.types.forEachIndexed { index, type ->
                LeadingIconTab(
                    selected = index == viewModel.typesIndex,
                    onClick = { viewModel.updateTypesIndex(index) },
                    selectedContentColor = Color(0xFF149EE7),
                    unselectedContentColor = Color(0xFF666666),
                    icon = { Icon(type.icon, contentDescription = null) },
                    text = { Text(text = type.title, modifier = Modifier.padding(vertical = 8.dp), fontSize = 16.sp) }
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun Swiper(viewModel: MainViewModel = viewModel()) {
    val virtualPageSize = Int.MAX_VALUE  //循环轮播
    val actualPageSize = viewModel.swipeUrl.size
    val initPageIndex = virtualPageSize / 2
    var pagerState = rememberPagerState(initialPage = initPageIndex)
    val mod = Math.floorMod(initPageIndex, actualPageSize)
    if (actualPageSize > 0) {
        val coroutineScope = rememberCoroutineScope()
        DisposableEffect(Unit) {
            val timer = Timer() //定时器
            timer.schedule(object : TimerTask() {
                override fun run() {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1) //翻页
                    }

                }

            }, 3000,3000) //3秒后开始，每3秒执行一次
            onDispose {
                timer.cancel()
            }
        }
        HorizontalPager(  //横向轮播图
            count = virtualPageSize,
            itemSpacing = 8.dp, //两图间隔
            modifier = Modifier.clip(RoundedCornerShape(8.dp)).height(200.dp),
            state = pagerState
        ) { index ->
            AsyncImage(
                model = viewModel.swipeUrl[Math.floorMod(index - mod, actualPageSize)].imageUrl,
                contentDescription = null,
                modifier = Modifier.aspectRatio(16 / 9f),
                contentScale = ContentScale.Crop
            )
        }
    } else {
        Row(modifier = Modifier.height(200.dp)) { }
    }

}


/*
* 类型选择Tab
* */
@Preview
@Composable
fun StudyScreenPreview() {
    StudyScreen()
}