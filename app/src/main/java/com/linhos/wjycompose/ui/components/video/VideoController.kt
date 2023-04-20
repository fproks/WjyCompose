package com.linhos.wjycompose.ui.components.video

import android.content.Context
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import com.tencent.rtmp.ITXVodPlayListener
import com.tencent.rtmp.TXLiveConstants
import com.tencent.rtmp.TXVodPlayer

class VideoController(context: Context, vodUrl: String) {

    var playerValue = PlayerValue(vodUrl)
    val videoPlayer = TXVodPlayer(context).apply {
        setVodListener(object : ITXVodPlayListener {
            override fun onPlayEvent(player: TXVodPlayer?, event: Int, param: Bundle?) {
                when (event) {
                    TXLiveConstants.PLAY_EVT_PLAY_LOADING -> {
                        playerValue.state = PlayState.Loading
                    }

                    TXLiveConstants.PLAY_EVT_VOD_PLAY_PREPARED,
                    TXLiveConstants.PLAY_EVT_RCV_FIRST_I_FRAME,
                    TXLiveConstants.PLAY_EVT_VOD_LOADING_END -> {
                        playerValue.state = PlayState.Playing
                    }
                    //获取视频时长和进度
                    TXLiveConstants.PLAY_EVT_PLAY_PROGRESS -> {
                        playerValue.duration =
                            param?.getInt(TXLiveConstants.EVT_PLAY_DURATION)?.toLong() ?: 0L
                        playerValue.currentPosition =
                            param?.getInt(TXLiveConstants.EVT_PLAY_PROGRESS)?.toLong() ?: 0L
                    }

                }
            }

            override fun onNetStatus(player: TXVodPlayer?, args: Bundle?) {

            }

        })

    }.also { it.setRenderMode(TXLiveConstants.RENDER_MODE_FULL_FILL_SCREEN) }

    fun startPlay() {
        videoPlayer.startVodPlay(playerValue.vodUrl)
        playerValue.state = PlayState.Playing
    }

    fun stopPlay() {
        videoPlayer.stopPlay(true)
    }

    fun pause() {
        videoPlayer.pause()
        playerValue.state = PlayState.Pause
    }

    fun resume() {
        videoPlayer.resume()
        playerValue.state = PlayState.Playing
    }

    fun restore() {
        when (playerValue.state) {
            PlayState.Playing -> {
                videoPlayer.stopPlay(true)
                videoPlayer.setStartTime(playerValue.currentPosition.toFloat())
                videoPlayer.startVodPlay(playerValue.vodUrl)
            }

            PlayState.None -> {}
            PlayState.Loading -> {}
            PlayState.Pause -> {
                videoPlayer.stopPlay(true)
                videoPlayer.setStartTime(playerValue.currentPosition.toFloat())
                videoPlayer.startVodPlay(playerValue.vodUrl)
                pause()
            }
        }

    }

    fun seekTo(seconds: Long) {
        videoPlayer.seek(seconds.toInt())
    }


}

/*
@Composable
fun rememberVodController(): VideoController {
    val context = LocalContext.current
    return remember { VideoController(context) }
}*/
@Composable
fun rememberVodController(context: Context = LocalContext.current, vodUrl: String) =
    rememberSaveable(saver = object : Saver<VideoController, PlayerValue> {
        override fun restore(value: PlayerValue): VideoController {
            val controller = VideoController(context, vodUrl)
            controller.playerValue = value
            return controller
        }

        override fun SaverScope.save(controller: VideoController): PlayerValue {
            return controller.playerValue
        }

    }) {
        VideoController(context, vodUrl)
    }