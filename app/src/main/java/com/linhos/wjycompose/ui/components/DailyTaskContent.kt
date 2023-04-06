package com.linhos.wjycompose.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun DailyTaskContent() {
    Column {
        DailyTaskItem(
            "登录",
            "5积分/每日首次登录",
            "已获得5积分/每日上限5积分",
            1f
        )
        DailyTaskItem(
            "文章学习",
            "10积分/每有效阅读1篇文章",
            "已获得50积分/每日上限100积分",
            0.5f
        )
        DailyTaskItem(
            "视听学习",
            "10积分/每有效观看视频或收听音频累计1分钟",
            "已获得70积分/每日上限100积分",
            0.7f
        )
    }
}

@Composable
fun DailyTaskItem(title: String, detail: String, credits: String, percent: Float = 1.0f) {
    val inLineContentId = "inLineContentId"
    val annotatedDetail = buildAnnotatedString {
        append(detail)
        appendInlineContent(inLineContentId) //添加的内容对应的ID
    }
    val inLineContent = mapOf(
        Pair(
            inLineContentId,
            InlineTextContent(
                Placeholder(
                    width = 14.sp,
                    height = 14.sp,
                    placeholderVerticalAlign = PlaceholderVerticalAlign.TextCenter
                )
            ) {
                Icon(Icons.Default.HelpOutline, contentDescription = null, modifier = Modifier.clickable {
                    Log.d("===", "点击了问号")
                })
            })
    )

    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(7.5f).padding(end = 8.dp)) {
            Text(title, fontSize = 16.sp, color = Color(0xff333333), modifier = Modifier.padding(vertical = 8.dp))

            /*  Row {
                  Text(annotatedDetail, inlineContent = inLineContent, fontSize = 14.sp, color = Color(0xff333333))
                  Icon(Icons.Default.HelpOutline, contentDescription = null)
              }*/
            //将icon 融合进text
            Text(
                annotatedDetail,
                inlineContent = inLineContent,
                fontSize = 14.sp,
                color = Color(0xff333333),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                LinearProgressIndicator(progress = percent, modifier = Modifier.weight(3f).padding(end = 4.dp))
                Text(credits, fontSize = 10.sp, color = Color(0xff333333), modifier = Modifier.weight(7f, fill = false))
            }
        }
        if (percent < 1f) {
            OutlinedButton(
                onClick = {
                    Log.d("===", "go to learn")
                }, border = BorderStroke(1.dp, Color(0xFFFF5900)), shape = CircleShape,
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFFF5900)),
                modifier = Modifier.weight(2.5f)
            ) {
                Text("去学习")
            }
        } else {
            OutlinedButton(
                onClick = {}, shape = CircleShape,
                border = ButtonDefaults.outlinedBorder,
                modifier = Modifier.weight(2.5f),
                enabled = false
            ) {
                Text("已完成")
            }
        }
    }
}