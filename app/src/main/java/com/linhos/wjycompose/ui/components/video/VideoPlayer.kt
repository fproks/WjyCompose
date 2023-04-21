package com.linhos.wjycompose.ui.components.video

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.FullscreenExit
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver


@Composable
fun VideoPlayer(vodController: VideoController) {
    //存储时间字符串
    var timeFormatterText by remember { mutableStateOf("") }

    //currentPosition 改变后调用
    LaunchedEffect(vodController.playerValue.currentPosition) {
        val position = vodController.playerValue.currentPosition
        val duration = vodController.playerValue.duration
        val MINUTE = 60
        val HOURS = 60 * 60
        timeFormatterText = String.format(
            "%02d:%02d:%02d / %02d:%02d:%02d",
            position / HOURS,
            (position % HOURS) / MINUTE,
            (position % MINUTE),
            duration / HOURS,
            (duration % HOURS) / MINUTE,
            (duration % MINUTE)
        )
    }


    val configuration = LocalConfiguration.current  //获取当前屏幕状态

    val activity = LocalContext.current as Activity
    BackHandler(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }
    val lifecycleOwner = LocalLifecycleOwner.current

    //监控lifecycle，key 使用controller会频繁触发
    DisposableEffect(Unit) {
        val lifecycleEventObserver = LifecycleEventObserver { _, event ->
            Log.d("===",event.name)
            when (event) {//lifecycle 被暂停或者唤醒

                Lifecycle.Event.ON_RESUME -> vodController.resume()
                Lifecycle.Event.ON_PAUSE -> vodController.pause()
                else -> {
                    Log.d("===","lifecycle")
                }
            }
        }
        lifecycleOwner.lifecycle.addObserver(lifecycleEventObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleEventObserver)
            vodController.stopPlay()
        }
    }

    //控制控制器是否显示
    var showControllerBar by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .fillMaxWidth()
        .clickable {  //点击后控制器出现
            showControllerBar = !showControllerBar
        }) {
        VideoView(vodController.videoPlayer)

        //视频加载层
        if (vodController.playerValue.state == PlayState.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(60.dp)
            )
        }

        if (vodController.playerValue.state == PlayState.None) {
            Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray)) {
                IconButton(onClick = {
                    vodController.startPlay()
                    Log.d("===","PlayState.None,start play")
                }, modifier = Modifier.align(Alignment.Center)) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow,
                        contentDescription = null,
                        modifier = Modifier.size(60.dp)
                    )
                }
            }

        }

        //视频控制层
        if (showControllerBar) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    Spacer(modifier = Modifier.height(1.dp))  //主要是为了上下排列用的，让row放到下面
                } else {
                    //切回竖屏按键
                    IconButton(
                        onClick = {
                            activity.requestedOrientation =
                                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                        }, modifier = Modifier
                            .padding(16.dp)
                            .size(45.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
                //下面的控制条
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (vodController.playerValue.state == PlayState.Playing) {  //播放状态时显示暂停按钮
                        IconButton(onClick = {
                            vodController.pause()
                        }) {
                            Icon(
                                imageVector = Icons.Default.Pause,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    } else {
                        IconButton(onClick = {
                            vodController.resume()
                        }) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }

                    Slider(
                        value = vodController.playerValue.currentPosition.toFloat(),
                        onValueChange = {
                            vodController.playerValue.currentPosition = it.toLong()
                            vodController.seekTo(it.toLong())
                        },
                        valueRange = 0f..vodController.playerValue.duration.toFloat(),
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = timeFormatterText, color = Color.White, fontSize = 14.sp)

                    //切换横竖屏
                    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                        IconButton(onClick = {
                            //切换横屏
                            activity.requestedOrientation =
                                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                        }) {
                            Icon(
                                imageVector = Icons.Default.Fullscreen,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    } else {
                        IconButton(onClick = {
                            activity.requestedOrientation =
                                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                        }) {
                            Icon(
                                imageVector = Icons.Default.FullscreenExit,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}


