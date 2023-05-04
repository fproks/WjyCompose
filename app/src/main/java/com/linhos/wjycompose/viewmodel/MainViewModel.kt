package com.linhos.wjycompose.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.linhos.wjycompose.model.entity.Category
import com.linhos.wjycompose.model.entity.DataType
import com.linhos.wjycompose.model.entity.SwiperEntity
import com.linhos.wjycompose.model.service.HomeService

class MainViewModel : ViewModel() {
    var categorys by mutableStateOf(
        listOf(
            Category("生活学习", "123"),
            Category("生活学习", "123")
        )
    )
        private set

    var placeholder by mutableStateOf(false)
        private set

    suspend fun categorysData() {
        val res = HomeService.instance().category()
        if (res.code == 0) {
            categorys = res.data
            placeholder = true
        } else {
            val message = res.message
        }
    }


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

    var swipeUrl by mutableStateOf(listOf(SwiperEntity("https://picsum.photos/200/300?random=1")))
        private set
    var swiperLoaded by mutableStateOf(false)
    private  set

    //获取轮播数据
    suspend fun swiperDate() {
        val swiperResponse = HomeService.instance().banner()
        if (swiperResponse.code == 0 && swiperResponse.data != null) {
            swipeUrl = swiperResponse.data
            swiperLoaded=true
        } else {
            val message = swiperResponse.message
        }

    }

    val notificationData by mutableStateOf(
        listOf(
            "测试例句1，这个例句比较短",
            "测试例句2，这个例句比较长,测试例句2，这个例句比较长，测试例句2，这个例句比较长，测试例句2，这个例句比较长",
            "测试例句3，这个例句比较短,测试例句3，这个例句比较短"
        )
    )
}