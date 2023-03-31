package com.linhos.wjycompose.viewmodel

import androidx.lifecycle.ViewModel
import com.linhos.wjycompose.model.entity.VideoEntity

class VideoViewModel : ViewModel() {
    val list =
        listOf(
            VideoEntity(
                title = "第一个video entity title,第一个video entity title,第一个video entity title,第一个video entity title",
                type = "视频课程",
                duration = "00:02:00",
                imageUrl = "https://picsum.photos/200/300?random=1"
            ),
            VideoEntity(
                title = "第2个video entity title,第2个video entity title,第2个video entity title",
                type = "视频课程",
                duration = "00:03:00",
                imageUrl = "https://picsum.photos/200/300?random=2"
            ),
            VideoEntity(
                title = "第3个video entity title",
                type = "视频课程",
                duration = "00:04:00",
                imageUrl = "https://picsum.photos/200/300?random=3"
            ), VideoEntity(
                title = "第4个video entity title",
                type = "视频课程",
                duration = "00:05:00",
                imageUrl = "https://picsum.photos/200/300?random=4"
            ), VideoEntity(
                title = "第5个video entity title",
                type = "视频课程",
                duration = "00:06:00",
                imageUrl = "https://picsum.photos/200/300?random=5"
            ), VideoEntity(
                title = "第6个video entity title",
                type = "视频课程",
                duration = "00:07:00",
                imageUrl = "https://picsum.photos/200/300?random=6"
            ), VideoEntity(
                title = "第7个video entity title",
                type = "视频课程",
                duration = "00:08:00",
                imageUrl = "https://picsum.photos/200/300?random=7"
            ), VideoEntity(
                title = "第8个video entity title,第8个video entity title,第8个video entity title,第8个video entity title",
                type = "视频课程",
                duration = "00:09:00",
                imageUrl = "https://picsum.photos/200/300?random=8"
            )
        )
}