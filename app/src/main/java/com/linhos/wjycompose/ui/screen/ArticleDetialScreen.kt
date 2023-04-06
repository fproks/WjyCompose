package com.linhos.wjycompose.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.TextFormat
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ArticleDetailScreen(onBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    "文章详情",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                )
            },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.NavigateBefore,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            onBack() //点击后触发返回事件
                        })
                },
                actions = {
                    Icon(imageVector = Icons.Default.FormatSize, contentDescription = null,
                        modifier = Modifier.clickable {
                            //TODO 设置文字大小
                        }.padding(8.dp)
                    )
                })
        },
        modifier = Modifier.background(MaterialTheme.colors.primary).statusBarsPadding(),
    ) {
        Text("这是文章详情")
    }
}