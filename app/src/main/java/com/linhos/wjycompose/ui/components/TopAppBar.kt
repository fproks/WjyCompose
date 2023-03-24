package com.linhos.wjycompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.linhos.wjycompose.ui.theme.Blue200
import com.linhos.wjycompose.ui.theme.Blue700

/**
 * 标题栏
 */
@Composable
fun TopAppBar(modifier: Modifier=Modifier,content: @Composable () -> Unit) {

    /*
    * 设置状态栏透明
    * */
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(key1 = Unit) {
        systemUiController.setStatusBarColor(Color.Transparent)
    }
    /*
    * 获取状态栏高度
    * */
    val statusBarHeight = with(LocalDensity.current) {
        WindowInsets.statusBars.getTop(this).toDp()
    }
    val appBarHeight = 56.dp
    Row(
        modifier = Modifier
            .background(Brush.linearGradient(listOf(Blue700, Blue200)))
            .fillMaxWidth()
            .height(appBarHeight+statusBarHeight)
            .padding(top = statusBarHeight).then(modifier),//连接两个modifier
        horizontalArrangement = Arrangement.Center,  //水平居中
        verticalAlignment = Alignment.CenterVertically //垂直居中
    ) { content() }
}

@Preview
@Composable
fun TopAppBarPreview() {
    TopAppBar {
        Text(text = "title")
    }
}