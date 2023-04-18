package com.linhos.wjycompose.ui.components.video

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*


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

    //控制控制器是否显示
    var showControllerBar by remember { mutableStateOf(false) }
    var timer: Timer? = null
    Box(modifier = Modifier.height(200.dp).fillMaxWidth().clickable {  //点击后控制器出现
        showControllerBar = !showControllerBar
        timer?.cancel()
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                showControllerBar = false
                timer?.cancel()
            }
        }, 3000L, 3000L)
    }) {
        VideoView(vodController.videoPlayer)
        if (showControllerBar) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
                Spacer(modifier = Modifier.height(1.dp))  //主要是为了上下排列用的，让row放到下面
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (vodController.playerValue.state == PlayState.Playing) {  //播放状态时显示暂停按钮
                        IconButton(onClick = {
                            vodController.pause()
                        }) {
                            Icon(imageVector = Icons.Default.Pause, contentDescription = null, tint = Color.White)
                        }
                    } else {
                        IconButton(onClick = {
                            vodController.resume()
                        }) {
                            Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null, tint = Color.White)
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
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Default.Fullscreen, contentDescription = null, tint = Color.White)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}