package com.jangawronski.fiveandmore

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.MutableIntState
import kotlin.random.Random
import androidx.compose.ui.platform.LocalContext


@Composable
fun GameGrid(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val rows = remember { mutableIntStateOf(9) }
    val columns = remember { mutableIntStateOf(9) }
    val winLength = remember { mutableIntStateOf(5) }
    val nColors = remember { mutableIntStateOf(6) }
    val nNextColors = remember { mutableIntStateOf(3) }
    val screenWidth = configuration.screenWidthDp.dp
    val cellSize = remember { mutableStateOf(screenWidth / columns.intValue) }
    var cellColors = remember { Array(rows.intValue * columns.intValue) { mutableIntStateOf(-1) } }
    var nextColors = remember { Array(nNextColors.intValue) { mutableIntStateOf(-1) } }
    val score = remember { mutableIntStateOf(0) }
    val chosen1 = remember { mutableIntStateOf(-1) }
    val chosen2 = remember { mutableIntStateOf(-1) }
    val isWin = remember { mutableStateOf(false) }
    val showMenuDialog = remember { mutableStateOf(false) }
    val showEndDialog = remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        restoreGameState(context, cellColors, nextColors, score, rows, columns, winLength, nColors, nNextColors)
    }

    DisposableEffect(Unit) {
        onDispose {
            saveGameState(context, cellColors, nextColors, score.intValue, rows.intValue, columns.intValue, winLength.intValue, nColors.intValue, nNextColors.intValue)
        }
    }



    if (chosen1.intValue != -1 && chosen2.intValue != -1) {
        if (isMovePossible(chosen1.intValue, chosen2.intValue, rows.intValue, columns.intValue, cellColors)) {
            cellColors[chosen2.intValue].intValue = cellColors[chosen1.intValue].intValue
            cellColors[chosen1.intValue].intValue = -1

            val previousScore = score.intValue
            checkInRow(cellColors, rows.intValue, columns.intValue, score, winLength.intValue)
            if (previousScore == score.intValue) {
                addColors(cellColors, nextColors)
                changeColors(nColors.intValue, nextColors)
            }
            checkInRow(cellColors, rows.intValue, columns.intValue, score, winLength.intValue)
        }

        val result = isGameIsOver(cellColors, rows.intValue, columns.intValue, score)
        if (result == -1) {
            isWin.value = false
            showEndDialog.value = true
        } else if (result == 1) {
            isWin.value = true
            showEndDialog.value = true
        }


        chosen1.intValue = -1
        chosen2.intValue = -1

    }


    Menu(showMenuDialog, rows, columns, winLength, nColors, nNextColors) {
        showMenuDialog.value = false
        //cellSize.value = screenWidth / columns.intValue
        //cellColors = Array(rows.intValue * columns.intValue) { mutableIntStateOf(-1) }
        //nextColors = Array(nNextColors.intValue) { mutableIntStateOf(-1) }
    }




    EndScreen(showEndDialog, score.intValue, isWin.value) {
        showEndDialog.value = false
        for (item in cellColors)
            item.intValue = -1
        for (item in nextColors)
            item.intValue = -1
        chosen1.intValue = -1
        chosen2.intValue= -1
        score.intValue = 0
    }


    TopBar(nextColors, score, onStart = {
        changeColors(nColors.intValue, nextColors)
        addColors(cellColors, nextColors)
        changeColors(nColors.intValue, nextColors)
        checkInRow(cellColors, rows.intValue, columns.intValue, score, winLength.intValue)
        val result = isGameIsOver(cellColors, rows.intValue, columns.intValue, score)
        if (result == -1) {
            isWin.value = false
            showEndDialog.value = true
        } else if (result == 1) {
            isWin.value = true
            showEndDialog.value = true
        } },
        onMenuClick = {
            showMenuDialog.value = true
        },
        onRetry = {
            for (item in cellColors)
                item.intValue = -1
            for (item in nextColors)
                item.intValue = -1
            chosen1.intValue = -1
            chosen2.intValue= -1
            score.intValue = 0
        })

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


fun addColors(cellColors: Array<MutableIntState>, nextColors: Array<MutableIntState>) {
    var i = 0
    for (item in cellColors) {
        if (item.intValue == -1)
            i += 1
    }

    i = if (nextColors.size < i) nextColors.size - 1 else i - 1
    while (i >= 0) {
        val randomPosition = Random.nextInt(cellColors.size)
        if (cellColors[randomPosition].intValue == -1) {
            cellColors[randomPosition].intValue = nextColors[i].intValue
            i -= 1
        }
    }
}

fun changeColors(nColors: Int, cellColors: Array<MutableIntState>) {
    for (item in cellColors)
        item.intValue = Random.nextInt(nColors)
}


