package com.linhos.wjycompose.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel



import com.linhos.wjycompose.viewmodel.MainViewModel


@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun NotificationContent(viewModel: MainViewModel = viewModel()) {
    Row(
        modifier = Modifier.background(Color(0x22149EE7)).clip(RoundedCornerShape(8.dp)).height(45.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "最新活动", color = Color(0xFF149EE7), fontSize = 14.sp
        )
        Spacer(modifier = Modifier.width(8.dp))
        VerticalPager(
            pageCount = viewModel.notificationData.size,
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) { index ->
            Text(
                viewModel.notificationData[index],
                maxLines = 1,
                color = Color(0xFF333333),
                fontSize = 14.sp,
                overflow = TextOverflow.Ellipsis,

                )
        }
        Spacer(Modifier.width(8.dp))
        Text("更多", maxLines = 1, color = Color(0xFF666666), fontSize = 14.sp)
    }
}