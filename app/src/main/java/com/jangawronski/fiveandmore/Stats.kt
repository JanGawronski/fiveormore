package com.jangawronski.fiveandmore

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.Column

class Stats(
    private val leaderboard: Array<MutableIntState>,
    private val nGames: MutableIntState,
    private val nWins: MutableIntState,
    private val nLosses: MutableIntState,
    private val pointsScored: MutableIntState,
) {
    private val showDialog: MutableState<Boolean> = mutableStateOf(false)
    @Composable
    fun Display() {
        if (showDialog.value) {
            AlertDialog(
                title = { Text("Statistics") },
                onDismissRequest = { hide() },
                text = {
                    Column {
                        Text("Games played: ${nGames.intValue}")
                        Text("Games won: ${nWins.intValue}")
                        Text("Games lost: ${nLosses.intValue}")
                        Text("Points scored: ${pointsScored.intValue}")
                        Text("Leaderboard:")
                        leaderboard.forEachIndexed { index, element ->
                            Text("${index + 1}. ${element.intValue}")
                        }
                    }
                },
                confirmButton = {
                    Button(onClick = { hide() }) {
                        Text("OK")
                    }
                },
                dismissButton = { //reset button
                    Button(onClick = {
                        nGames.intValue = 0
                        nWins.intValue = 0
                        nLosses.intValue = 0
                        pointsScored.intValue = 0
                        leaderboard.forEach { element ->
                            element.intValue = 0
                        }
                    }) {
                        Text("Reset")
                    }
                }
            )
        }
    }

    fun show() {
        showDialog.value = true
    }

    private fun hide() {
        showDialog.value = false
    }
}