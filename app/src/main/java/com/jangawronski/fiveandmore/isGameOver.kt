package com.jangawronski.fiveandmore

import androidx.compose.runtime.MutableIntState

fun isGameIsOver(cellColors: Array<MutableIntState>, rows: Int, columns: Int, score: MutableIntState): Int {
    var isWin = true
    for (i in 0 until rows) {
        for (j in 0 until columns) {
            if (cellColors[i * columns + j].intValue != -1) {
                isWin = false
            }
        }
    }
    var isLoss = true
    for (i in 0 until rows) {
        for (j in 0 until columns) {
            if (cellColors[i * columns + j].intValue == -1) {
                isLoss = false
            }
        }
    }

    if (isWin)
        return 1
    if (isLoss)
        return -1
    return 0
}