package com.jangawronski.fiveandmore

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.MutableIntState

fun saveGameState(
    context: Context,
    cellColors: Array<MutableIntState>,
    nextColors: Array<MutableIntState>,
    score: Int,
    rows: Int,
    columns: Int,
    winLength: Int,
    nColors: Int,
    nNextColors: Int,
    leaderBoard: Array<MutableIntState>,
    nGames: Int,
    nWins: Int,
    nLosses: Int,
    pointsScored: Int
) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("GameState", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    editor.putInt("score", score)
    editor.putInt("rows", rows)
    editor.putInt("columns", columns)
    editor.putInt("winLength", winLength)
    editor.putInt("nColors", nColors)
    editor.putInt("nNextColors", nNextColors)
    editor.putInt("nGames", nGames)
    editor.putInt("nWins", nWins)
    editor.putInt("nLosses", nLosses)
    editor.putInt("pointsScored", pointsScored)

    cellColors.forEachIndexed { index, state ->
        editor.putInt("cellColor_$index", state.intValue)
    }

    nextColors.forEachIndexed { index, state ->
        editor.putInt("nextColor_$index", state.intValue)
    }

    leaderBoard.forEachIndexed { index, scoreI ->
        editor.putInt("leaderBoard_$index", scoreI.intValue)
    }

    editor.apply()
}

fun restoreGameState(
    context: Context,
    cellColors: Array<MutableIntState>,
    nextColors: Array<MutableIntState>,
    score: MutableIntState,
    rows: MutableIntState,
    columns: MutableIntState,
    winLength: MutableIntState,
    nColors: MutableIntState,
    nNextColors: MutableIntState,
    leaderBoard: Array<MutableIntState>,
    nGames: MutableIntState,
    nWins: MutableIntState,
    nLosses: MutableIntState,
    pointsScored: MutableIntState
) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("GameState", Context.MODE_PRIVATE)

    score.intValue = sharedPreferences.getInt("score", 0)
    rows.intValue = sharedPreferences.getInt("rows", 9)
    columns.intValue = sharedPreferences.getInt("columns", 9)
    winLength.intValue = sharedPreferences.getInt("winLength", 5)
    nColors.intValue = sharedPreferences.getInt("nColors", 6)
    nNextColors.intValue = sharedPreferences.getInt("nNextColors", 3)
    nGames.intValue = sharedPreferences.getInt("nGames", 0)
    nWins.intValue = sharedPreferences.getInt("nWins", 0)
    nLosses.intValue = sharedPreferences.getInt("nLosses", 0)
    pointsScored.intValue = sharedPreferences.getInt("pointsScored", 0)

    cellColors.forEachIndexed { index, state ->
        state.intValue = sharedPreferences.getInt("cellColor_$index", -1)
    }

    nextColors.forEachIndexed { index, state ->
        state.intValue = sharedPreferences.getInt("nextColor_$index", -1)
    }

    leaderBoard.forEachIndexed { index, scoreI ->
        scoreI.intValue = sharedPreferences.getInt("leaderBoard_$index", 0)
    }
}