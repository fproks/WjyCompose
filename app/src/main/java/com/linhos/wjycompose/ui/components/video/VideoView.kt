package com.linhos.wjycompose.ui.components.video

import android.view.LayoutInflater
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.linhos.wjycompose.R
import com.tencent.rtmp.TXVodPlayer
import com.tencent.rtmp.ui.TXCloudVideoView


@Composable
fun VideoView(vodPlayer: TXVodPlayer) {
    AndroidView(factory = { context ->
        (LayoutInflater.from(context).inflate(R.layout.video, null, false)
            .findViewById(R.id.video) as TXCloudVideoView).apply {
            vodPlayer.setPlayerView(this)
        }
        //也可以使用下面的方法，下面的方法不需要创建layout
        /* TXCloudVideoView(context).apply {
             vodPlayer.setPlayerView(this)
         }*/
    })
}