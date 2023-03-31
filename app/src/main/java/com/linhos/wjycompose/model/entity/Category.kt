package com.linhos.wjycompose.model.entity

import androidx.compose.ui.graphics.vector.ImageVector

data class Category(
    val title: String,
    val icon: ImageVector
)

data class DataType(
    val title: String,
    val icon: ImageVector
)

data class SwiperEntity(
    val imageUrl: String
)


