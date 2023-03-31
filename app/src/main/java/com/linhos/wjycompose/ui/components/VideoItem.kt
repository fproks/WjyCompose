package com.linhos.wjycompose.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import coil.compose.AsyncImage
import com.linhos.wjycompose.model.entity.VideoEntity


/**
 * 使用constrain layout 设置compose 间的关系
 */
@Composable
fun VideoItem(videoEntity: VideoEntity) {
    /* 两种不同的写法
    val constraintSet = ConstraintSet {
         constrain(cover) {
             start.linkTo(parent.start, margin = 8.dp)
             centerVerticallyTo(parent)
             width = Dimension.value(115.5.dp)
         }
         constrain(title) {
             start.linkTo(cover.end, margin = 8.dp)
             end.linkTo(parent.end, margin = 8.dp)
             top.linkTo(cover.top)
             width = Dimension.fillToConstraints //宽度自适应
         }
         constrain(type) {
             start.linkTo(title.start)

             bottom.linkTo(cover.bottom)
         }
         constrain(duration) {
             start.linkTo(type.end, margin = 16.dp)
             bottom.linkTo(type.bottom)
         }
         constrain(divider) {
             start.linkTo(parent.start)
             top.linkTo(cover.bottom, margin = 8.dp)
             end.linkTo(parent.end)
         }
     }*/
    ConstraintLayout {
        val (cover, title, type, duration, divider) = createRefs()
        AsyncImage(
            videoEntity.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop, //裁剪
            modifier = Modifier
                .aspectRatio(16 / 9f)
                .clip(RoundedCornerShape(8.dp)).constrainAs(cover) {
                    start.linkTo(parent.start, margin = 8.dp)
                    centerVerticallyTo(parent)
                    width = Dimension.value(115.5.dp)
                }

        )
        Text(
            text = videoEntity.title,
            fontSize = 16.sp,
            color = Color(0xFF666666),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(title) {
                start.linkTo(cover.end, margin = 8.dp)
                end.linkTo(parent.end, margin = 8.dp)
                top.linkTo(cover.top)
                width = Dimension.fillToConstraints //宽度自适应
            }
        )
        Text(
            text = videoEntity.type,
            fontSize = 10.sp,
            color = Color(0xFF999999),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(type) {
                start.linkTo(title.start)
                bottom.linkTo(cover.bottom)
            }
        )
        Text(
            text = "时长：${videoEntity.duration}",
            fontSize = 10.sp,
            color = Color(0xFF999999),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(duration) {
                start.linkTo(type.end, margin = 16.dp)
                bottom.linkTo(type.bottom)
            }
        )
        Divider(modifier = Modifier.constrainAs(divider) {
            start.linkTo(parent.start)
            top.linkTo(cover.bottom, margin = 8.dp)
            end.linkTo(parent.end)
        })
    }
}

@Preview
@Composable
fun viderItemPreview() {
    VideoItem(
        VideoEntity(
            title = "第一个video entity title",
            type = "视频课程",
            duration = "00:02:00",
            imageUrl = "https://picsum.photos/200/300?random=1"
        )
    )
}


@Preview
@Composable
public fun ScreenExample2() {
    ConstraintLayout(
        ConstraintSet {                                  // Create a constraint set which is passed to ConstraintLayout
            val button = createRefFor("button")          // Create ID "button"
            val title = createRefFor("title") // Create ID "title"
            val g1 = createGuidelineFromStart(80.dp)     // Create Guideline "g1"
            constrain(button) {                          // Create Constraint for id "button"
                top.linkTo(title.bottom, 16.dp)          // Constrain top to bottom of title with 16dp margin
                start.linkTo(g1)                         // Constrain start to g1 (0dp margin implied)

            }
            constrain(title) {                           // Create Constraint for id "title"
                centerVerticallyTo(parent)               // center vertically in parent (equivalent to top_toTop="parent" bottom_toBottom="parent"
                start.linkTo(g1)                         // Constrain start to guideline
            }
        },
        modifier = Modifier
            .fillMaxSize()
    ) {
        Button(
            modifier = Modifier.layoutId("button"),      // Button has an id "button"
            onClick = {},
        ) {
            Text(text = "login")
        }
        Text(
            modifier = Modifier.layoutId("title"),       // Texthas an id "title"
            text = "welcome",
            style = MaterialTheme.typography.h2,
        )
    }
}