package com.linhos.wjycompose.model.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleEntity(
    val title: String,
    val source: String,
    @Json(name = "time") //将time 转成 timestamp
    val timestamp: String,
    val content: String?=null

)
