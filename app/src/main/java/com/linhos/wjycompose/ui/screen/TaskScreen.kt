package com.linhos.wjycompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.linhos.wjycompose.ui.components.CircleRing
import com.linhos.wjycompose.viewmodel.TaskViewModel


@Preview
@Composable
fun TaskScreen(taskVM: TaskViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF149EE7), Color.White)))
    ) {
        //标题栏
        androidx.compose.material.TopAppBar(
            title = {
                Text(
                    "学习任务",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    color = Color.White
                )
            },
            elevation = 0.dp,
            modifier = Modifier.statusBarsPadding(), //
            backgroundColor = Color.Transparent  //背景色透明
        )
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {//横向居中
            //学习周期
            item {
                Text(
                    text = taskVM.taskDate,
                    fontSize = 12.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            //园以及内部的文字
            item {
                val boxSize: Int
                with(LocalConfiguration.current) {
                    boxSize = screenWidthDp / 2
                }
                Box(contentAlignment = Alignment.Center, modifier = Modifier.height(boxSize.dp).padding(top = 16.dp)) {
                    CircleRing(boxSize) { taskVM.pointOfYear.toFloat() / taskVM.totalPointOfYear }  //外部的园
                    //学习积分文字
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {//内容横向居中
                        Text(
                            buildAnnotatedString {
                                append(taskVM.pointOfYear.toString())
                                withStyle(SpanStyle(fontSize = 12.sp)) {
                                    append("分")
                                }
                            },
                            fontSize = 36.sp, color = Color.White
                        )
                        Text(text = "学年积分", fontSize = 12.sp, color = Color.White)
                    }
                }
            }
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,//均匀分布
                    modifier = Modifier.fillMaxWidth().offset(y = (-40).dp)//整体往上移
                ) {
                    Column {
                        Text("${taskVM.totalPointOfYear}分", color = Color.White, fontSize = 16.sp)
                        Text("学年规定积分", color = Color.White, fontSize = 12.sp)
                    }
                    Column {
                        Text(
                            text = "${taskVM.totalPointOfYear - taskVM.pointOfYear}分",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                        Text("还差", color = Color.White, fontSize = 12.sp)
                    }
                }
            }
        }


    }
}