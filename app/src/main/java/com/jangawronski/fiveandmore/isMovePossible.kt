package com.jangawronski.fiveandmore

import androidx.compose.runtime.MutableIntState
import java.util.LinkedList
import java.util.Queue

fun isMovePossible(start: Int, end: Int, nRows: Int, nColumns: Int, cellColors: Array<MutableIntState>): Boolean {
    val visited = BooleanArray(cellColors.size) { false }
    val queue: Queue<Int> = LinkedList()

    queue.add(start)
    visited[start] = true

    val directions = listOf(-1, 1, -nColumns, nColumns)

    while (queue.isNotEmpty()) {
        val v = queue.remove()

        if (v == end) {
            return true
        }

        for (direction in directions) {
            val next = v + direction


            if (next >= 0 && next < cellColors.size &&
                !visited[next] && cellColors[next].intValue == -1
            ) {
                if (direction == -1 && v % nColumns == 0) continue
                if (direction == 1 && (v + 1) % nColumns == 0) continue

                queue.add(next)
                visited[next] = true
            }
        }
    }

    return false
}