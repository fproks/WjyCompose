package com.linhos.wjycompose.ui.components.video

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class PlayerValue {
    var  duration by mutableStateOf(0L)  //总时长

    var currentPosition by mutableStateOf(0L) //当前时长
}