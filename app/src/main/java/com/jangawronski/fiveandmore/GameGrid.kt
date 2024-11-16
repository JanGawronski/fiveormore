package com.jangawronski.fiveandmore

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.unit.Dp

@Composable
fun GameGrid(rows: MutableIntState, columns: MutableIntState, modifier: Modifier = Modifier, cellColors: Array<MutableIntState>, chosen1: MutableIntState, chosen2: MutableIntState, cellSize: MutableState<Dp>) {
    Column(modifier = modifier.fillMaxWidth()) {
        for (i in 0 until rows.intValue) {
            Row(modifier = Modifier.fillMaxWidth()) {
                for (j in 0 until columns.intValue) {
                    val index = i * columns.intValue + j
                    Cell(cellSize, cellColors[index], chosen1.intValue == index) {
                        if (chosen1.intValue == -1) {
                            if (cellColors[index].intValue != -1)
                                chosen1.intValue = index
                        } else if (cellColors[index].intValue == -1)
                            chosen2.intValue = index
                        else {
                            chosen1.intValue = -1
                            chosen2.intValue = -1
                        }

                    }
                }
            }
        }
    }
}