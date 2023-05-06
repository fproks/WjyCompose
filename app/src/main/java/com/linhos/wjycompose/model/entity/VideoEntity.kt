package com.linhos.wjycompose.model.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class VideoEntity(
    val title: String,
    val type: String = "视频课程",
    val duration: String,
    @Json(name = "cover")
    val imageUrl: String,
    val video: String? = null,
    val desc: String? = null
)
