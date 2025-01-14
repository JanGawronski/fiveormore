package com.jangawronski.fiveandmore

import androidx.compose.runtime.MutableIntState

fun checkInRow(cellColors: Array<MutableIntState>, rows: Int, columns: Int, score: MutableIntState, winLength: Int) {
    val grid = Array(rows) { BooleanArray(columns) }

    for (i in 0 until rows) {
        var j = 0
        while (j < columns) {
            var k = j
            while (k < columns && cellColors[i * columns + j].intValue == cellColors[i * columns + k].intValue) {
                k += 1
            }
            if (k - j >= winLength && cellColors[i * columns + j].intValue != -1) {
                for (l in j until k) {
                    grid[i][l] = true
                }
            }
            j = k
        }
    }
    for (i in 0 until columns) {
        var j = 0
        while (j < rows) {
            var k = j
            while (k < rows && cellColors[j * columns + i].intValue == cellColors[k * columns + i].intValue) {
                k += 1
            }
            if (k - j >= winLength && cellColors[j * columns + i].intValue != -1) {
                for (l in j until k) {
                    grid[l][i] = true
                }
            }
            j = k
        }
    }
    // Check diagonals from top-left to bottom-right
    for (i in 0 until rows) {
        for (j in 0 until columns) {
            var k = 0
            while (i + k < rows && j + k < columns && cellColors[i * columns + j].intValue == cellColors[(i + k) * columns + (j + k)].intValue) {
                k += 1
            }
            if (k >= winLength && cellColors[i * columns + j].intValue != -1) {
                for (l in 0 until k) {
                    grid[i + l][j + l] = true
                }
            }
        }
    }

    // Check diagonals from top-right to bottom-left
    for (i in 0 until rows) {
        for (j in 0 until columns) {
            var k = 0
            while (i + k < rows && j - k >= 0 && cellColors[i * columns + j].intValue == cellColors[(i + k) * columns + (j - k)].intValue) {
                k += 1
            }
            if (k >= winLength && cellColors[i * columns + j].intValue != -1) {
                for (l in 0 until k) {
                    grid[i + l][j - l] = true
                }
            }
        }
    }

    for (i in 0 until rows) {
        for (j in 0 until columns) {
            if (grid[i][j]) {
                score.intValue += 1
                cellColors[i * columns + j].intValue = -1
            }
        }
    }
}