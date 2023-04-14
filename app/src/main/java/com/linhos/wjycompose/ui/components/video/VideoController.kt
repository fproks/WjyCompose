package com.linhos.wjycompose.ui.components.video

import android.content.Context
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.tencent.rtmp.ITXVodPlayListener
import com.tencent.rtmp.TXLiveConstants
import com.tencent.rtmp.TXVodPlayer

class VideoController(context: Context) {

    val playerValue = PlayerValue()
    private val videoPlayer = TXVodPlayer(context).apply {
        setVodListener(object : ITXVodPlayListener {
            override fun onPlayEvent(player: TXVodPlayer?, event: Int, param: Bundle?) {
                when (event) {
                    //获取视频时长和进度
                    TXLiveConstants.PLAY_EVT_PLAY_PROGRESS -> {
                        playerValue.duration = param?.getInt(TXLiveConstants.EVT_PLAY_DURATION)?.toLong() ?: 0L
                        playerValue.currentPosition = param?.getInt(TXLiveConstants.EVT_PLAY_PROGRESS)?.toLong() ?: 0L
                    }

                }
            }

            override fun onNetStatus(player: TXVodPlayer?, args: Bundle?) {
                TODO("Not yet implemented")
            }

        })
    }

    fun startPlay(url: String) {
        videoPlayer.startVodPlay(url)
        videoPlayer.setRenderRotation(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION)
    }

    fun stopPlay() {
        videoPlayer.stopPlay(true)
    }

    fun pause() {
        videoPlayer.pause()
    }

    fun resume() {
        videoPlayer.resume()
    }


    fun seekTo(millSeconds: Long) {
        videoPlayer.seek((millSeconds / 100).toInt())
    }

    fun setUpView(view: @Composable (TXVodPlayer) -> Unit) {
        view(videoPlayer)
    }
}

@Composable
fun rememberVodController(): VideoController {
    val context = LocalContext.current
    return remember { VideoController(context) }
}