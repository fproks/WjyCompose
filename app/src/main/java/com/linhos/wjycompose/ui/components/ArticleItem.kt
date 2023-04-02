package com.linhos.wjycompose.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linhos.wjycompose.model.entity.ArticleEntity

@Composable
fun ArticleItem(article: ArticleEntity, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(
            text = article.title,
            color = Color(0xFF333333),
            fontSize = 16.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                "来源： ${article.source}",
                color = Color(0xFF999999),
                fontSize = 10.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                article.timestamp,
                color = Color(0xFF999999),
                fontSize = 10.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Divider(modifier = Modifier.padding(top = 8.dp))
    }
}

@Preview
@Composable
fun ArticleItemPreview() {
    ArticleItem(
        ArticleEntity(
            title = "新闻列表title 1 新闻列表title 1 新闻列表title 1新闻列表title 1 新闻列表title 1 新闻列表title 1",
            source = "新闻来源",
            timestamp = "2020-02-10"
        )
    )
}