package com.jangawronski.fiveandmore

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.MutableIntState
import android.util.Log

fun saveGameState(context: Context, cellColors: Array<MutableIntState>, nextColors: Array<MutableIntState>, score: Int, rows: Int, columns: Int, winLength: Int, nColors: Int, nNextColors: Int) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("GameState", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    editor.putInt("score", score)
    editor.putInt("rows", rows)
    editor.putInt("columns", columns)
    editor.putInt("winLength", winLength)
    editor.putInt("nColors", nColors)
    editor.putInt("nNextColors", nNextColors)

    cellColors.forEachIndexed { index, state ->
        editor.putInt("cellColor_$index", state.intValue)
    }

    nextColors.forEachIndexed { index, state ->
        editor.putInt("nextColor_$index", state.intValue)
    }

    editor.commit();
}

fun restoreGameState(context: Context, cellColors: Array<MutableIntState>, nextColors: Array<MutableIntState>, score: MutableIntState, rows: MutableIntState, columns: MutableIntState, winLength: MutableIntState, nColors: MutableIntState, nNextColors: MutableIntState) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("GameState", Context.MODE_PRIVATE)

    score.intValue = sharedPreferences.getInt("score", 0)
    rows.intValue = sharedPreferences.getInt("rows", 9)
    columns.intValue = sharedPreferences.getInt("columns", 9)
    winLength.intValue = sharedPreferences.getInt("winLength", 5)
    nColors.intValue = sharedPreferences.getInt("nColors", 6)
    nNextColors.intValue = sharedPreferences.getInt("nNextColors", 3)

    cellColors.forEachIndexed { index, state ->
        state.intValue = sharedPreferences.getInt("cellColor_$index", -1)
    }

    nextColors.forEachIndexed { index, state ->
        state.intValue = sharedPreferences.getInt("nextColor_$index", -1)
    }

}

