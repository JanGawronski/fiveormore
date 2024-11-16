package com.jangawronski.fiveandmore

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.foundation.layout.Column

@Composable
fun Stats(showDialog: MutableState<Boolean>, leaderboard: Array<MutableIntState> , nGames: MutableIntState, nWins: MutableIntState, nLosses: MutableIntState, pointsScored: MutableIntState, onClick: () -> Unit) {
    if (showDialog.value) {
        AlertDialog(
            title = { Text("Statistics") },
            onDismissRequest = onClick,
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
                Button(onClick = onClick) {
                    Text("OK")
                }
            },
            dismissButton = {
                Button(onClick = {
                    nGames.intValue = 0
                    nWins.intValue = 0
                    nLosses.intValue = 0
                    pointsScored.intValue = 0
                    leaderboard.forEach() { element ->
                        element.intValue = 0
                    }
                }) {
                    Text("Reset")
                }
            }
        )
    }
}