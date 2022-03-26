package com.fvink.mobilebanking.features.accounts

import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.util.Log
import androidx.annotation.ColorInt
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.NativeCanvas
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastForEachIndexed
import kotlin.math.ceil


@Composable
fun AccountBalanceChartView(
    balanceHistory: AccountBalanceHistory,
    modifier: Modifier = Modifier
) {
    val labels = calculateYAxisValues(balanceHistory.balanceList)

    val backgroundColor = Color(0xFF1C1E21)
    val graphLineColor = Color(0xFF34BFFF)
    val transparentColor = Color(0x00000000)

    val gradientColors = listOf(
        Color(0xFF34BFFF),
        Color(0x5E34BFFF),
        Color(0x2C34BFFF),
        Color(0x00000000)
    )

    Canvas(
        modifier = modifier
            .background(backgroundColor)
    ) {
        val height = size.height
        val width = size.width

        val graphTop = 70f
        val graphBottom = height - 70f
        val graphMiddle = (graphBottom - graphTop) / 2 + 70f

        val graphBounds = Rect(0, graphTop.toInt(), width.toInt(), graphBottom.toInt())

        val indicatorLineColor = Color(0xFF797979)
        val textPaint = Paint().apply {
            textSize = 30f
            color = android.graphics.Color.parseColor("#797979")
            textAlign = Paint.Align.CENTER
        }
        val textBounds = Rect()
        textPaint.getTextBounds("0", 0, 1, textBounds)

        drawYAxisIndicatorLine(graphTop, width, indicatorLineColor)
        drawYAxisIndicatorLine(graphMiddle, width, indicatorLineColor)
        drawYAxisIndicatorLine(graphBottom, width, indicatorLineColor)

        drawYAxisLabel(
            text = labels.maxValue.toString(),
            x = 70f,
            y = graphTop - 20,
            paint = textPaint,
            backgroundColor = backgroundColor
        )
        drawYAxisLabel(
            text = labels.minValue.toString(),
            x = 70f,
            y = graphBottom + textBounds.height() + 20,
            paint = textPaint,
            backgroundColor = backgroundColor
        )

        val points = balanceHistory.balanceList.mapIndexed { index, value ->
            getGraphCoordsForValue(
                value = value,
                minValue = labels.minValue,
                maxValue = labels.maxValue,
                indexOnXAxis = index,
                xIndicesCount = balanceHistory.balanceList.size,
                graphBounds = graphBounds
            )
        }
        val startingPoint = points.first()
        val linePath = Path().apply {
            moveTo(startingPoint.first, startingPoint.second)
        }

        for (index in 1..points.lastIndex) {
            val point = points[index]
            linePath.lineTo(point.first, point.second)
        }

        val lastPoint = points.last()
        linePath.lineTo(lastPoint.first, lastPoint.second)
        linePath.lineTo(graphBounds.right.toFloat(), graphBounds.bottom.toFloat())
        linePath.lineTo(graphBounds.left.toFloat(), graphBounds.bottom.toFloat())

        drawPath(
            path = linePath,
            brush = Brush.verticalGradient(
                colors = gradientColors
            )
        )

        for (index in 0 until points.lastIndex) {
            val point1 = points[index]
            val point2 = points[index + 1]

            drawLine(
                color = graphLineColor,
                start = Offset(point1.first, point1.second),
                end = Offset(point2.first, point2.second),
                strokeWidth = 5f
            )
        }
    }

}

fun DrawScope.drawYAxisLabel(
    text: String,
    x: Float,
    y: Float,
    paint: Paint,
    backgroundColor: Color
) {
    val textBounds = Rect()
    paint.getTextBounds(text, 0, text.length, textBounds)

    drawRect(
        color = backgroundColor,
        topLeft = Offset(x - textBounds.width() / 2 - 5, y - textBounds.height()),
        size = Size(
            width = textBounds.width().toFloat() + 5,
            height = textBounds.height().toFloat()
        )
    )
    drawContext.canvas.nativeCanvas.apply {
        drawText(text, x, y, paint)
    }
}

fun DrawScope.drawYAxisIndicatorLine(y: Float, endX: Float, color: Color) {
    drawLine(
        color = color,
        strokeWidth = 2f,
        start = Offset(0f, y),
        end = Offset(endX, y),
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(7f, 12f), 0f)
    )
}

private fun getGraphCoordsForValue(
    value: Double,
    minValue: Int,
    maxValue: Int,
    indexOnXAxis: Int,
    xIndicesCount: Int,
    graphBounds: Rect
): Pair<Float, Float> {
    // val percentageOnXAxis = if (indexOnXAxis == 0) 0.0 else (value * 100) / (maxValue)
    val percentageOnXAxis = if (indexOnXAxis == 0) 0 else ((indexOnXAxis) * 100) / (xIndicesCount - 1)
    val percentageOnYAxis = ((value - minValue) * 100) / (maxValue - minValue)

    Log.d("AAA", "percentageOnXAxis: $percentageOnXAxis")
    Log.d("AAA", "percentageOnYAxis: $percentageOnYAxis")

    val x = (percentageOnXAxis * (graphBounds.right - graphBounds.left) / 100) + graphBounds.left
    val y = (percentageOnYAxis * (graphBounds.top - graphBounds.bottom) / 100) + graphBounds.bottom

    Log.d("AAA", "x: $x, y: $y")

    return Pair(x.toFloat(), y.toFloat())
}

private fun calculateYAxisValues(values: List<Double>): YAxisLabels {
    val minValue = roundDown(values.minOrNull() ?: 0.0)
    val midValue = roundUp(values.sum() / values.size)
    val maxValue = roundUp(values.maxOrNull() ?: 0.0)

    return YAxisLabels(minValue, midValue, maxValue)
}

private fun roundDown(value: Double): Int {
    return when {
        value <= 100 -> 0
        value <= 1000 -> value.toInt() / 100 * 100
        else -> value.toInt() / 1000 * 1000
    }
}

private fun roundUp(value: Double): Int {
    return when {
        value <= 10 -> 10
        value <= 100 -> 100
        value <= 10000 -> (ceil(value / 100f) * 100).toInt()
        else -> (ceil(value / 1000f) * 1000).toInt()
    }
}

@Preview
@Composable
fun AccountBalanceChartPreview() {
    AccountBalanceChartView(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        balanceHistory = AccountBalanceHistory(
            balanceList = listOf(
                0.0,
                100.0,
                450.0,
                600.0,
                550.0,
                500.0,
                700.0,
                800.0,
                400.0,
                500.0,
                600.0,
                700.0,
                1000.0,
                800.0
            )
        )
    )
}

data class AccountBalanceHistory(
    val balanceList: List<Double>
)

data class YAxisLabels(
    val minValue: Int,
    val midValue: Int,
    val maxValue: Int,
)