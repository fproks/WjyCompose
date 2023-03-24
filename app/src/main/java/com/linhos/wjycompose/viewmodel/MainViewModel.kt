package com.linhos.wjycompose.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.linhos.wjycompose.model.entity.Category

class MainViewModel : ViewModel() {
    val categorys =
        mutableStateOf(listOf(Category("生活学习", Icons.Default.Person), Category("生活学习", Icons.Default.Person)))
}