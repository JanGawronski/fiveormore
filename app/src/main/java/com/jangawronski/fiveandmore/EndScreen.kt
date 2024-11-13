package com.jangawronski.fiveandmore

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun EndScreen(showDialog: MutableState<Boolean>,  message: Int, win: Boolean, onClick: () -> Unit) {
    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = onClick,
            text = {
                if (win) {
                    Text("You won! Your score: $message")
                } else {
                    Text("You lost! Your score: $message")
                } },
            confirmButton = {
                Button(onClick = onClick) {
                    Text("OK")
                }
            }
        )
    }
}