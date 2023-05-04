package com.linhos.wjycompose.ui.screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.linhos.wjycompose.ui.components.ArticleItem
import com.linhos.wjycompose.ui.components.NotificationContent
import com.linhos.wjycompose.ui.components.TopAppBar
import com.linhos.wjycompose.ui.components.VideoItem
import com.linhos.wjycompose.viewmodel.ArticleViewModel
import com.linhos.wjycompose.viewmodel.MainViewModel
import com.linhos.wjycompose.viewmodel.VideoViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StudyScreen(
    viewModel: MainViewModel = viewModel(),
    articleViewModel: ArticleViewModel = viewModel(),
    videoViewModel: VideoViewModel = viewModel(),
    onNavigateToArticle: () -> Unit = {},
    onNavigateToVideo: () -> Unit = {},
    onNavigateToHistory: () -> Unit = {}
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.categorysData()
        articleViewModel.fetchArticleList()
    }

    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    fun refresh() = refreshScope.launch {
        refreshing = true
        articleViewModel.fetchArticleList()
        delay(1500)
        refreshing = false
    }
    Column {

        StudystatusBar(onNavigateToHistory)

        CategoryTab(viewModel)
        TypesRowTab(viewModel)

        //https://developer.android.com/reference/kotlin/androidx/compose/material/pullrefresh/package-summary
        //下拉刷新
        val pullRefreshState =
            rememberPullRefreshState(refreshing = refreshing, onRefresh = ::refresh)//下拉时触发onRefresh
        Box(Modifier.pullRefresh(pullRefreshState)) {

            LazyColumn {
                item {
                    Swiper(viewModel)
                }
                item {
                    NotificationContent(viewModel)
                }
                if (!refreshing) {
                    if (viewModel.typesIndex == 0) {
                        items(articleViewModel.list) { article ->
                            //添加了一个导航
                            ArticleItem(article, modifier = Modifier
                                .clickable {
                                    Log.d("===", "study click")
                                    onNavigateToArticle()
                                }
                                .placeholder(
                                    visible = !articleViewModel.fetachLoaded,
                                    highlight = PlaceholderHighlight.shimmer()
                                ))
                        }
                    } else {
                        items(videoViewModel.list) { video ->
                            //添加导航点击事件
                            VideoItem(video, modifier = Modifier.clickable {
                                Log.d("===", "navigate to video")
                                onNavigateToVideo()
                            })
                        }
                    }
                }
            }
            //refresh 的指示器，仅当refreshing 为true 时才显示
            PullRefreshIndicator(
                refreshing = refreshing, state = pullRefreshState, Modifier.align(
                    Alignment.TopCenter
                )
            )
        }

    }
}


/*
      * 学习的标题栏
      * */
@Composable
fun StudystatusBar(onNavigateToHistory: () -> Unit = {}) {
    TopAppBar(modifier = Modifier.padding(horizontal = 8.dp)) {
        Row {
            Surface(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .weight(1f), //圆角
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
            Text(
                text = "学习\n进度",
                fontSize = 10.sp,
                color = Color.White,
                modifier = Modifier.clickable {
                    onNavigateToHistory()
                })
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
            containerColor = Color(0x22149EE7),
            contentColor = Color(0xFF149EE7),
            indicator = {}//选中时下划线颜色
        ) {
            viewModel.categorys.forEachIndexed { index, category ->
                Tab(
                    selected = index == viewModel.studyCategoryIndex,
                    onClick = { viewModel.updateStudyCategoryIndex(index) },
                    //icon = { Icon(imageVector = category.icon, contentDescription = null) },
                    text = {
                        Text(
                            text = category.title,
                            fontSize = 14.sp,
                            maxLines = 1,
                            modifier = Modifier.placeholder(visible = !viewModel.placeholder) //placeholder
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
            containerColor = Color.Transparent,
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
                    text = {
                        Text(
                            text = type.title,
                            modifier = Modifier.padding(vertical = 8.dp),
                            fontSize = 16.sp
                        )
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun Swiper(viewModel: MainViewModel = viewModel()) {

    val virtualPageSize = Int.MAX_VALUE  //循环轮播
    var actualPageSize = viewModel.swipeUrl.size
    val initPageIndex = virtualPageSize / 2
    val pagerState = rememberPagerState(initialPage = initPageIndex)
    val mod = Math.floorMod(initPageIndex, actualPageSize)
    if (actualPageSize > 0) {
        val coroutineScope = rememberCoroutineScope()
        DisposableEffect(Unit) {
            coroutineScope.launch {
                viewModel.swiperDate()
                Log.d("===", "swipUrl is ${viewModel.swipeUrl.size}")
                actualPageSize = viewModel.swipeUrl.size
            }
            val timer = Timer() //定时器
            timer.schedule(object : TimerTask() {
                override fun run() {
                    coroutineScope.launch {
                        if (viewModel.swiperLoaded) {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1) //翻页
                        }
                    }

                }

            }, 3000, 3000) //3秒后开始，每3秒执行一次
            onDispose {
                timer.cancel()
            }
        }
        HorizontalPager(  //横向轮播图
            pageCount = virtualPageSize,
            pageSpacing = 8.dp, //两图间隔
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .height(200.dp),
            state = pagerState,
            userScrollEnabled = viewModel.swiperLoaded
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