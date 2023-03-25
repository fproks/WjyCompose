package com.linhos.wjycompose.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.linhos.wjycompose.model.entity.Category
import com.linhos.wjycompose.model.entity.DataType
import com.linhos.wjycompose.model.entity.SwiperEntity

class MainViewModel : ViewModel() {
    val categorys by
    mutableStateOf(
        listOf(
            Category("生活学习", Icons.Default.Person),
            Category("生活学习", Icons.Default.Person)
        )
    )

    var studyCategoryIndex by mutableStateOf(0)
        private set

    fun updateStudyCategoryIndex(index: Int) {
        studyCategoryIndex = index
    }

    val types by mutableStateOf(
        listOf(
            DataType("相关资讯", icon = Icons.Default.Description),
            DataType("视频课程", Icons.Default.SmartDisplay)
        )
    )
    var typesIndex by mutableStateOf(0)
        private set

    fun updateTypesIndex(index: Int) {
        typesIndex = index
    }

    val swipeUrl by mutableStateOf(listOf(SwiperEntity("https://docs.bughub.icu/compose/assets/banner1.webp")))
}