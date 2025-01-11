package com.jangawronski.fiveandmore

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.MutableIntState


class GameGrid(
    private val rows: MutableIntState,
    private val columns: MutableIntState,
    private val modifier: Modifier = Modifier,
    private val cellColors: Array<MutableIntState>,
    private val chosen1: MutableIntState,
    private val chosen2: MutableIntState
) {
    @Composable
    fun Display() {
        Column(modifier = modifier.fillMaxWidth()) {
            for (i in 0 until rows.intValue) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    for (j in 0 until columns.intValue) {
                        val index = i * columns.intValue + j

                        Cell(columns, cellColors[index], chosen1.intValue == index) {
                            if (chosen1.intValue == -1) {
                                if (cellColors[index].intValue != -1)
                                    chosen1.intValue = index
                            } else if (cellColors[index].intValue == -1)
                                chosen2.intValue = index
                            else {
                                chosen1.intValue = -1
                                chosen2.intValue = -1
                            }
                        }.Display()
                    }
                }
            }
        }
    }
}