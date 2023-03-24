package com.linhos.wjycompose.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.linhos.wjycompose.model.entity.Category

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

    val types by mutableStateOf(listOf("文章", "视频"))
    var typesIndex by mutableStateOf(0)
        private set

    fun updateTypesIndex(index: Int) {
        typesIndex = index
    }
}