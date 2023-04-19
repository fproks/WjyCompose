package com.linhos.wjycompose.ui.screen

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.linhos.app.module.webview.MyWebView
import com.linhos.app.module.webview.rememberMyWebViewStateWithData
import com.linhos.wjycompose.ui.components.video.VideoPlayer
import com.linhos.wjycompose.ui.components.video.rememberVodController
import com.linhos.wjycompose.viewmodel.VideoViewModel


@Composable
fun VideoDetailScreen(videoViewModel: VideoViewModel = viewModel(), onBack: () -> Unit = {}) {
    var webViewState = rememberMyWebViewStateWithData(videoViewModel.content)
    val vodContoller = rememberVodController()
    val systemUiController = rememberSystemUiController() //控制状态栏
    val configuration = LocalConfiguration.current
    LaunchedEffect(configuration.orientation) {
        //横屏时状态栏隐藏并透明
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            systemUiController.isSystemBarsVisible = true
        } else {
            systemUiController.isSystemBarsVisible = false
            systemUiController.setSystemBarsColor(Color.Transparent)
        }
    }

    if (configuration.orientation ==Configuration.ORIENTATION_PORTRAIT) {
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
                    VideoPlayer(vodContoller, videoViewModel.videoUrl)

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
    }else{
        Column (modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxSize()){
                VideoPlayer(vodContoller,videoViewModel.videoUrl)
            }
            //不知道为啥，有这个就显示不出来
           // MyWebView(webViewState)
        }

    }
}