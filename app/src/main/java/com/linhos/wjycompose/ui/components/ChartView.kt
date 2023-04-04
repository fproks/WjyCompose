package com.linhos.wjycompose.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * 折线图
 */
@Composable
fun ChartView(points: List<Double>,modifier: Modifier=Modifier) {
    val canvasWidth = LocalConfiguration.current.screenWidthDp - 8 * 2
    val heightForRow = 24  //单行高度
    val countForRow = 5  //行数
    val circleRadius = 2.5
    val canvasHeight = heightForRow * countForRow + circleRadius * 2

    Canvas(modifier = modifier.size(width = canvasWidth.dp, height = canvasHeight.dp)) {
        for (index in 0..countForRow) {
            val strtY = (heightForRow * index + circleRadius).dp.toPx()
            drawLine(Color(0xffEEEEEE), start = Offset(0f, strtY), end = Offset(size.width, strtY), strokeWidth = 2.5f)
        }


        val averageOfWIdth = canvasWidth / 7
        val preY = points.max() - points.min()
        for (index in 0 until points.count()) {
            val percentOfPoint = 1 - points[index] / (preY)
            val center = Offset(
                x = (averageOfWIdth * index + averageOfWIdth / 2).dp.toPx(),
                y = (heightForRow * countForRow * percentOfPoint + circleRadius).dp.toPx()
            )
            drawCircle(
                Color(0xFF149EE7),
                radius = circleRadius.dp.toPx(),
                center = center,
                style = Stroke(width = 5f)
            )
            if(index<points.size-1){
                val nextPercentOfPoint = 1 - points[index+1] / (preY)
                val startCenter = Offset(
                    x = (averageOfWIdth * index + averageOfWIdth / 2+circleRadius).dp.toPx(),
                    y = (heightForRow * countForRow * percentOfPoint + circleRadius*0.5).dp.toPx()
                )
                val nextCenter =Offset(
                    x = (averageOfWIdth * (index+1) + averageOfWIdth / 2-circleRadius).dp.toPx(),
                    y = (heightForRow * countForRow * nextPercentOfPoint + circleRadius*1.5).dp.toPx()
                )
                drawLine(Color(0xFF149EE7), start = startCenter, end = nextCenter, strokeWidth = 5f)
            }
        }
    }
}

@Preview
@Composable
fun ChartViewPreivew() {
    ChartView(listOf(0.0, 2.0, 6.0, 9.0, 10.0, 15.0, 5.0))
}