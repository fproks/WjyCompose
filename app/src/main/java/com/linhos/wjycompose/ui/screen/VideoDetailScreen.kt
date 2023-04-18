package com.linhos.wjycompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.linhos.app.module.webview.MyWebView
import com.linhos.app.module.webview.rememberMyWebViewStateWithData
import com.linhos.wjycompose.ui.components.video.VideoPlayer
import com.linhos.wjycompose.ui.components.video.rememberVodController
import com.linhos.wjycompose.viewmodel.VideoViewModel


@Composable
fun VideoDetailScreen(videoViewModel: VideoViewModel = viewModel(), onBack: () -> Unit = {}) {
    var webViewState = rememberMyWebViewStateWithData(videoViewModel.content)
    val vodContoller = rememberVodController()
    LaunchedEffect(vodContoller) {
        vodContoller.startPlay(videoViewModel.videoUrl)

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
                VideoPlayer(vodContoller)

            }
            MyWebView(webViewState)

         /*   LazyColumn {
                item {
                    Spacer(modifier = Modifier.height(40.dp))
                }
                item {
                    Text(text = videoViewModel.title, color = Color(0xFF333333), fontSize = 16.sp)
                }
                item {
                    MyWebView(webViewState)
                }
            }*/
        }

    }
}