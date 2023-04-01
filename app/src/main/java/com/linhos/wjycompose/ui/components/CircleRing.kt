package com.linhos.wjycompose.ui.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CircleRing(boxSize: Int, percent: () -> Float) {

    val strokeWidth = 30f //画笔宽度
    val startAngle = -10f  //其实角度
    val endAngel = 200f   //终点角度
    val percentAngle: () -> Float = { startAngle + ((endAngel - startAngle) * percent()) }
    Log.d("PRECENT", percentAngle.toString())
    Canvas(modifier = Modifier.size(boxSize.dp)) {
        rotate(180f) {
            drawArc(//画背景的弧形
                Color(0, 0, 0, 33),
                startAngle = startAngle,
                sweepAngle = endAngel,
                useCenter = false,
                style = Stroke(strokeWidth, cap = StrokeCap.Round)
            )
            drawArc(   //画进度弧形
                Color.White,
                startAngle = startAngle, //起始角度
                sweepAngle = percentAngle(),  //结束角度
                useCenter = false,  //空心
                style = Stroke(strokeWidth, cap = StrokeCap.Round) //笔画宽度为30，圆形
            )
        }
    }
}

@Preview
@Composable
fun CircleRingPreview() {
    CircleRing(200) { 0.5f }
}