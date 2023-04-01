package com.linhos.wjycompose.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {
    val taskDate by mutableStateOf("学习周期：2022.01.01-2022.12.31")

    val totalPointOfYear by mutableStateOf(13500)
    var pointOfYear by mutableStateOf(10000)
        private set

    fun updataPointOfYear(point: Int) {
        pointOfYear = point
    }


}