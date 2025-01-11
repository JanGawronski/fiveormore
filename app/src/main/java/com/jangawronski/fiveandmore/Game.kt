package com.jangawronski.fiveandmore

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.MutableIntState
import kotlin.random.Random
import androidx.compose.ui.platform.LocalContext


@Composable
fun GameGrid(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val rows = remember { mutableIntStateOf(9) }
    val columns = remember { mutableIntStateOf(9) }
    val winLength = remember { mutableIntStateOf(5) }
    val nColors = remember { mutableIntStateOf(6) }
    val nNextColors = remember { mutableIntStateOf(3) }
    val cellColors = remember { Array(rows.intValue * columns.intValue) { mutableIntStateOf(-1) } }
    val nextColors = remember { Array(nNextColors.intValue) { mutableIntStateOf(-1) } }
    val score = remember { mutableIntStateOf(0) }
    val chosen1 = remember { mutableIntStateOf(-1) }
    val chosen2 = remember { mutableIntStateOf(-1) }
    val isWin = remember { mutableStateOf(false) }
    val showEndDialog = remember { mutableStateOf(false) }
    val leaderBoard = remember { Array(10) { mutableIntStateOf(-1) } }
    val nGames = remember { mutableIntStateOf(0) }
    val nWins = remember { mutableIntStateOf(0) }
    val nLosses = remember { mutableIntStateOf(0) }
    val pointsScored = remember { mutableIntStateOf(0) }
    val stats = Stats(leaderBoard, nGames, nWins, nLosses, pointsScored)
    stats.Display()
    val menu = Menu(rows, columns, winLength, nColors, nNextColors)
    menu.Display()
    val gameGrid = GameGrid(rows, columns, modifier, cellColors, chosen1, chosen2)


    LaunchedEffect(Unit) {
        restoreGameState(context, cellColors, nextColors, score, rows, columns, winLength, nColors, nNextColors, leaderBoard, nGames, nWins, nLosses, pointsScored)
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
            pointsScored.intValue += score.intValue - previousScore
        }

        val result = isGameIsOver(cellColors, rows.intValue, columns.intValue, score)
        if (result == -1) {
            nLosses.intValue += 1
            isWin.value = false
            showEndDialog.value = true
        } else if (result == 1) {
            nWins.intValue += 1
            isWin.value = true
            showEndDialog.value = true
        }


        chosen1.intValue = -1
        chosen2.intValue = -1
        saveGameState(context, cellColors, nextColors, score.intValue, rows.intValue, columns.intValue, winLength.intValue, nColors.intValue, nNextColors.intValue, leaderBoard, nGames.intValue, nWins.intValue, nLosses.intValue, pointsScored.intValue)
    }



    EndScreen(showEndDialog, score.intValue, isWin.value) {
        showEndDialog.value = false
        if (leaderBoard[9].intValue < score.intValue) {
            leaderBoard[9].intValue = score.intValue
            leaderBoard.sortByDescending { it.intValue }
        }

        for (item in cellColors)
            item.intValue = -1
        for (item in nextColors)
            item.intValue = -1
        chosen1.intValue = -1
        chosen2.intValue= -1
        score.intValue = 0
        saveGameState(context, cellColors, nextColors, score.intValue, rows.intValue, columns.intValue, winLength.intValue, nColors.intValue, nNextColors.intValue, leaderBoard, nGames.intValue, nWins.intValue, nLosses.intValue, pointsScored.intValue)
    }


    TopBar(nextColors, score, onStart = {
        nGames.intValue += 1
        changeColors(nColors.intValue, nextColors)
        addColors(cellColors, nextColors)
        changeColors(nColors.intValue, nextColors)
        val previousScore = score.intValue
        checkInRow(cellColors, rows.intValue, columns.intValue, score, winLength.intValue)
        pointsScored.intValue += score.intValue - previousScore
        val result = isGameIsOver(cellColors, rows.intValue, columns.intValue, score)
        if (result == -1) {
            isWin.value = false
            showEndDialog.value = true
        } else if (result == 1) {
            isWin.value = true
            showEndDialog.value = true
        } },
        onMenuClick = {
            menu.show()
        },
        onRetry = {
            for (item in cellColors)
                item.intValue = -1
            for (item in nextColors)
                item.intValue = -1
            chosen1.intValue = -1
            chosen2.intValue= -1
            score.intValue = 0
            saveGameState(context, cellColors, nextColors, score.intValue, rows.intValue, columns.intValue, winLength.intValue, nColors.intValue, nNextColors.intValue, leaderBoard, nGames.intValue, nWins.intValue, nLosses.intValue, pointsScored.intValue)
        },
        onStatsClick = {
            stats.show()
        })

    gameGrid.Display()


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


