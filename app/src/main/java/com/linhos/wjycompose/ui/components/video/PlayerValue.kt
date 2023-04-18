package com.linhos.wjycompose.ui.components.video

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class PlayerValue {
    var duration by mutableStateOf(0L)  //总时长

    var currentPosition by mutableStateOf(0L) //当前时长

    var state by mutableStateOf(PlayState.None)
}

enum class PlayState {
    None,   //未播放
    Loading,  //加载中
    Playing,   //播放中
    Pause   //暂停
}