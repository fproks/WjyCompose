package com.linhos.wjycompose.ui.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


import com.linhos.wjycompose.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.timer


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun NotificationContent(viewModel: MainViewModel = viewModel()) {


    //无限轮播
    val virtualCount = Int.MAX_VALUE
    val actualCount = viewModel.notificationData.size
    val initialIndex = virtualCount / 2
    val mod = Math.floorMod(initialIndex, actualCount)
    val pagerState = rememberPagerState(initialIndex)

    val coroutineScope = rememberCoroutineScope()
    DisposableEffect(Unit) {
        val timer = Timer()
        timer.schedule(object : TimerTask() {//定时器，定时换页
            override fun run() {
                coroutineScope.launch {
                    Log.d("TIMER", "start to ${pagerState.currentPage + 1}")
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }

        }, 3000, 3000)
        onDispose {
            timer.cancel()
        }
    }
    Row(
        modifier = Modifier
            .padding(8.dp)//background 在padding 后，padding 出来的8dp才没有颜色
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0x22149EE7))
            .height(45.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,  //居中
        horizontalArrangement = Arrangement.SpaceBetween  //两侧排列
    ) {
        Text(
            "最新活动", color = Color(0xFF149EE7), fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        VerticalPager(
            pageCount = virtualCount,
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start,  //左侧排列
            state = pagerState
        ) { index ->
            Text(
                viewModel.notificationData[Math.floorMod(index - mod, actualCount)],
                maxLines = 1,
                color = Color(0xFF333333),
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,  //省略号

            )
        }
        Spacer(Modifier.width(8.dp))
        Text("更多", maxLines = 1, color = Color(0xFF666666), fontSize = 14.sp)
    }
}