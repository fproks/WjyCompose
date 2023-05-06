package com.linhos.wjycompose.ui.extension

import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.flow.collect
import java.util.logging.Logger

//添加一个方法，监听Lazy 是否已到底部，到底部则刷新数据
@Composable
fun LazyListState.OnBottomReached(onBottom: () -> Unit={}) {
    val shouldLoaderMode = remember {
        //如果某个状态是从其他状态对象计算或派生得出的，请使用 derivedStateOf
        derivedStateOf {
            //获取显示的最后一条item
            val lastVisibleItem =
                layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf true
            //判断是否为所有的最后一条Item
            lastVisibleItem.index == layoutInfo.totalItemsCount - 1
        }

    }
    //https://developer.android.com/jetpack/compose/side-effects?hl=zh-cn#snapshotFlow
    LaunchedEffect(shouldLoaderMode) {
        snapshotFlow { shouldLoaderMode.value }.collect {
            if (it) {
                Log.d("===", "已到底部")
                onBottom()
            }
        }

    }


}