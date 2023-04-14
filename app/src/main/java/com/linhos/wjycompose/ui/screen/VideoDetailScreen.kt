package com.linhos.wjycompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.linhos.app.module.webview.MyWebView
import com.linhos.app.module.webview.rememberMyWebViewStateWithData
import com.linhos.wjycompose.ui.components.video.VideoView
import com.linhos.wjycompose.viewmodel.VideoViewModel
import com.tencent.rtmp.TXVodPlayer


@Composable
fun VideoDetailScreen(videoViewModel: VideoViewModel = viewModel(), onBack: () -> Unit = {}) {
    var webViewState = rememberMyWebViewStateWithData(videoViewModel.content)
    val vodPlayer = TXVodPlayer(LocalContext.current)
    LaunchedEffect(vodPlayer) {
        vodPlayer.startVodPlay("https://1251245530.vod2.myqcloud.com/34386e2dvodtranssh1251245530/fe4d4574243791581392273409/video_10_0.m3u8")
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "视频详情",
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
                            onBack()
                        })
                },
                actions = {
                    Box(modifier = Modifier.width(72.dp))
                }
            )
        },
        modifier = Modifier.background(MaterialTheme.colors.primary).statusBarsPadding()
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize()) {
            Box(modifier = Modifier.height(200.dp)) {
                VideoView(vodPlayer)
            }


            LazyColumn {
                item {
                    Spacer(modifier = Modifier.height(40.dp))
                }
                item {
                    Text(text = videoViewModel.title, color = Color(0xFF333333), fontSize = 16.sp)
                }
                item {
                    MyWebView(webViewState)
                }
            }
        }

    }
}