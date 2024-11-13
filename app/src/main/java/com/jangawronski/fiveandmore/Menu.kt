package com.jangawronski.fiveandmore

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.material3.AlertDialog
import androidx.compose.foundation.layout.Column

import androidx.compose.material3.Slider
import androidx.compose.material3.TextField

import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableFloatStateOf


@Composable
fun Menu(showDialog: MutableState<Boolean>, width: MutableState<Int>, height: MutableState<Int>, winLength: MutableState<Int>, nColors: MutableState<Int>, nNextColors: MutableState<Int>, onClick: () -> Unit) {
    if (!showDialog.value) {
        return
    }

    val widthState = remember { mutableFloatStateOf(width.value.toFloat()) }
    val heightState = remember { mutableFloatStateOf(height.value.toFloat()) }
    val winLengthState = remember { mutableFloatStateOf(winLength.value.toFloat()) }
    val nColorsState = remember { mutableFloatStateOf(nColors.value.toFloat()) }
    val nNextColorsState = remember { mutableFloatStateOf(nNextColors.value.toFloat()) }

    AlertDialog(
        onDismissRequest = { showDialog.value = false },
        title = { Text("Settings") },
        text = {
            Column {
                Text("Width")
                Slider(
                    value = widthState.floatValue,
                    onValueChange = { widthState.floatValue = it },
                    valueRange = 1f..100f
                )
                TextField(
                    value = widthState.floatValue.toInt().toString(),
                    onValueChange = { widthState.floatValue = it.toFloatOrNull() ?: widthState.floatValue }
                )

                Text("Height")
                Slider(
                    value = heightState.floatValue,
                    onValueChange = { heightState.floatValue = it },
                    valueRange = 1f..100f
                )
                TextField(
                    value = heightState.floatValue.toInt().toString(),
                    onValueChange = { heightState.floatValue = it.toFloatOrNull() ?: heightState.floatValue }
                )

                Text("Win Length")
                Slider(
                    value = winLengthState.floatValue,
                    onValueChange = { winLengthState.floatValue = it },
                    valueRange = 1f..100f
                )
                TextField(
                    value = winLengthState.floatValue.toInt().toString(),
                    onValueChange = { winLengthState.floatValue = it.toFloatOrNull() ?: winLengthState.floatValue }
                )

                Text("Number of Colors")
                Slider(
                    value = nColorsState.floatValue,
                    onValueChange = { nColorsState.floatValue = it },
                    valueRange = 1f..100f
                )
                TextField(
                    value = nColorsState.floatValue.toInt().toString(),
                    onValueChange = { nColorsState.floatValue = it.toFloatOrNull() ?: nColorsState.floatValue }
                )

                Text("Number of Next Colors")
                Slider(
                    value = nNextColorsState.floatValue,
                    onValueChange = { nNextColorsState.floatValue = it },
                    valueRange = 1f..100f
                )
                TextField(
                    value = nNextColorsState.floatValue.toInt().toString(),
                    onValueChange = { nNextColorsState.floatValue = it.toFloatOrNull() ?: nNextColorsState.floatValue }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                width.value = widthState.floatValue.toInt()
                height.value = heightState.floatValue.toInt()
                winLength.value = winLengthState.floatValue.toInt()
                nColors.value = nColorsState.floatValue.toInt()
                nNextColors.value = nNextColorsState.floatValue.toInt()
                onClick()
            }) {
                Text("OK")
            }
        },
        dismissButton = {
            Button(onClick = { showDialog.value = false }) {
                Text("Cancel")
            }
        }
    )
}