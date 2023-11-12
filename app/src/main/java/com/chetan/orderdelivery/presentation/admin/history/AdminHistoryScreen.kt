package com.chetan.orderdelivery.presentation.admin.history

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.chetan.orderdelivery.ui.theme.OrderDeliveryTheme


//@Composable
//fun AdminHistoryScreen() {
//    val pointsData: List<Point> =
//        listOf(
//            Point(0f,10f),
//            Point(1f, 50f),
//            Point(2f, 45f),
//            Point(3f, 40f),
//            Point(4f, 20f))
//
//    val xAxisData = AxisData.Builder()
//        .axisStepSize(25.dp)
//        .steps(pointsData.size)
//        .labelData { i -> i.toString() }
//        .labelAndAxisLinePadding(15.dp)
//        .build()
//
//    val yAxisData = AxisData.Builder()
//        .steps(10)
//        .labelAndAxisLinePadding(15.dp)
//        .labelData { i ->
//            val yScale = 50 / 10
//            (i * yScale).toString()
//        }.build()
//
//
//    val lineChartData = LineChartData(
//        linePlotData = LinePlotData(
//            lines = listOf(
//                Line(
//                    dataPoints = pointsData,
//                    LineStyle(),
//                    IntersectionPoint(),
//                    SelectionHighlightPoint(),
//                    ShadowUnderLine(),
//                    SelectionHighlightPopUp()
//                )
//            ),
//        ),
//        xAxisData = xAxisData,
//        yAxisData = yAxisData,
//        gridLines = GridLines(),
//        backgroundColor = Color.White
//    )
//
//    LineChart(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(300.dp),
//        lineChartData = lineChartData
//    )
////
////    LineGraph(
////        xAxisData = listOf("Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat").map {
////            GraphData.String(it)
////        }, // xAxisData : List<GraphData>, and GraphData accepts both Number and String types
////        yAxisData = listOf(200, 40, 60, 450, 700, 30, 50, 40, 60, 450, 700, 30, 50),
////        onPointClicked = {
////
////        },
////        header = {
////            Text(text = "Hello")
////        }
////    )
//
//
//}


//@Preview
//@Composable
//fun s() {
//    OrderDeliveryTheme {
//        AdminHistoryScreen()
//    }
//}