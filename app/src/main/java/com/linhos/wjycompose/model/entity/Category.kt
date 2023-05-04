package com.linhos.wjycompose.model.entity

import androidx.compose.ui.graphics.vector.ImageVector
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Category(val title: String, val id: String)

data class DataType(
    val title: String,
    val icon: ImageVector
)

@JsonClass(generateAdapter = true)
data class SwiperEntity(
    @Json(name = "imgUrl") val imageUrl: String,
    val title: String? = ""
)


