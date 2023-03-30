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

    val swipeUrl by mutableStateOf(
        listOf(
            SwiperEntity("https://picsum.photos/200/300?random=1"),
            SwiperEntity("https://picsum.photos/200/300?random=2"),
            SwiperEntity("https://picsum.photos/200/300?random=3"),
            SwiperEntity("https://picsum.photos/200/300?random=4"),
            SwiperEntity("https://picsum.photos/200/300?random=5")
        )
    )

    val notificationData by mutableStateOf(
        listOf(
            "测试例句1，这个例句比较短",
            "测试例句2，这个例句比较长,测试例句2，这个例句比较长，测试例句2，这个例句比较长，测试例句2，这个例句比较长",
            "测试例句3，这个例句比较短,测试例句3，这个例句比较短"
        )
    )
}