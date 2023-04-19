package com.linhos.wjycompose.ui.components.video

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.FullscreenExit
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*


@Composable
fun VideoPlayer(vodController: VideoController, videoUrl: String) {
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

    /*  var coverImage by remember { mutableStateOf<Bitmap?>(null) }
      LaunchedEffect(Unit) {
          val url ="https://www.bilibili.com/video/BV1aS4y1D7dv?t=656.0&p=35"
          Log.d("===", videoUrl)
          val mmr = MediaMetadataRetriever()
          mmr.setDataSource(videoUrl, hashMapOf())
          withContext(Dispatchers.Default) {
              coverImage = mmr.getFrameAtTime(0L, MediaMetadataRetriever.OPTION_PREVIOUS_SYNC)
              mmr.release()
          }
      }*/

    val activity = LocalContext.current as Activity
    //控制控制器是否显示
    var showControllerBar by remember { mutableStateOf(false) }
    var timer: Timer? = null
    Box(modifier = Modifier.fillMaxWidth().clickable {  //点击后控制器出现
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

        //视频加载层
        if (vodController.playerValue.state == PlayState.Loading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center).size(60.dp))
        }

        if (vodController.playerValue.state == PlayState.None) {
            Box(modifier = Modifier.fillMaxSize()) {
                IconButton(onClick = {
                    vodController.startPlay(videoUrl)
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

                    //切换横竖屏
                    if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                        IconButton(onClick = {
                            //切换横屏
                            activity.requestedOrientation =ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                        }) {
                            Icon(imageVector = Icons.Default.Fullscreen, contentDescription = null, tint = Color.White)
                        }
                    }else{
                        IconButton(onClick = {
                            activity.requestedOrientation =ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                        }) {
                            Icon(imageVector = Icons.Default.FullscreenExit, contentDescription = null, tint = Color.White)
                        }
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}