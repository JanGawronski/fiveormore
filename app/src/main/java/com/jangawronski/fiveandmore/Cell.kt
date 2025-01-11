package com.jangawronski.fiveandmore

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.platform.LocalConfiguration

class Cell(
    private val nColumns: MutableState<Int>,
    private val cellColor: MutableState<Int>,
    private val clicked: Boolean,
    private val onClick: () -> Unit
) {
    @Composable
    fun Display() {
        val screenWidth = LocalConfiguration.current.screenWidthDp.dp
        Box(
            modifier = Modifier
                .size(screenWidth / nColumns.value)
                .background(if (clicked) Color(0xFF336699) else Color(0xFF99CCEE))
                .border(1.dp, Color(0xFF5599CC))
                .padding(4.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    onClick()
                }
        ) {
            if (cellColor.value != -1) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = getColorByIndex(cellColor.value),
                        radius = size.minDimension / 2
                    )
                }
            }
        }
    }
    companion object {
        fun getColorByIndex(index: Int): Color {
            return when (index) {
                0 -> Color.Red
                1 -> Color.Green
                2 -> Color.Blue
                3 -> Color.Yellow
                4 -> Color.Cyan
                5 -> Color.Magenta
                6 -> Color.Black
                7 -> Color.White
                8 -> Color.Gray
                9 -> Color.LightGray
                10 -> Color.DarkGray
                11 -> Color(0xFFFFC0CB) // Pink
                12 -> Color(0xFFFFA500) // Orange
                13 -> Color(0xFFA52A2A) // Brown
                14 -> Color(0xFF800080) // Purple
                15 -> Color(0xFFEE82EE) // Violet
                16 -> Color(0xFF4B0082) // Indigo
                17 -> Color(0xFF00FF00) // Lime
                18 -> Color(0xFF008080) // Teal
                19 -> Color(0xFFFFBF00) // Amber
                20 -> Color(0xFFFF4500) // DeepOrange
                21 -> Color(0xFF673AB7) // DeepPurple
                22 -> Color(0xFFADD8E6) // LightBlue
                23 -> Color(0xFF90EE90) // LightGreen
                24 -> Color(0xFFFF1493) // DeepPink
                25 -> Color(0xFFFFD700) // Gold
                else -> Color.Gray
            }
        }
    }
}