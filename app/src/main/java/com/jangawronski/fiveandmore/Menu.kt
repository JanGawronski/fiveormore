package com.jangawronski.fiveandmore

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.material3.TextField
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableFloatStateOf

class Menu(
    private val width: MutableState<Int>,
    private val height: MutableState<Int>,
    private val winLength: MutableState<Int>,
    private val nColors: MutableState<Int>,
    private val nNextColors: MutableState<Int>
) {
    private val showDialog: MutableState<Boolean> = mutableStateOf(false)

    @Composable
    fun Display() {
        if (showDialog.value) {
            val widthState = remember { mutableFloatStateOf(width.value.toFloat()) }
            val heightState = remember { mutableFloatStateOf(height.value.toFloat()) }
            val winLengthState = remember { mutableFloatStateOf(winLength.value.toFloat()) }
            val nColorsState = remember { mutableFloatStateOf(nColors.value.toFloat()) }
            val nNextColorsState = remember { mutableFloatStateOf(nNextColors.value.toFloat()) }

            AlertDialog(
                onDismissRequest = { hide() },
                title = { Text("Settings") },
                text = {
                    Column {
                        Text("Width")
                        Slider(
                            value = widthState.floatValue,
                            onValueChange = { widthState.floatValue = it },
                            valueRange = 1f..20f
                        )
                        TextField(
                            value = widthState.floatValue.toInt().toString(),
                            onValueChange = { widthState.floatValue = it.toFloatOrNull() ?: widthState.floatValue }
                        )

                        Text("Height")
                        Slider(
                            value = heightState.floatValue,
                            onValueChange = { heightState.floatValue = it },
                            valueRange = 1f..20f
                        )
                        TextField(
                            value = heightState.floatValue.toInt().toString(),
                            onValueChange = { heightState.floatValue = it.toFloatOrNull() ?: heightState.floatValue }
                        )

                        Text("Win Length")
                        Slider(
                            value = winLengthState.floatValue,
                            onValueChange = { winLengthState.floatValue = it },
                            valueRange = 1f..9f
                        )
                        TextField(
                            value = winLengthState.floatValue.toInt().toString(),
                            onValueChange = { winLengthState.floatValue = it.toFloatOrNull() ?: winLengthState.floatValue }
                        )

                        Text("Number of Colors")
                        Slider(
                            value = nColorsState.floatValue,
                            onValueChange = { nColorsState.floatValue = it },
                            valueRange = 1f..26f
                        )
                        TextField(
                            value = nColorsState.floatValue.toInt().toString(),
                            onValueChange = { nColorsState.floatValue = it.toFloatOrNull() ?: nColorsState.floatValue }
                        )

                        Text("Number of Next Colors")
                        Slider(
                            value = nNextColorsState.floatValue,
                            onValueChange = { nNextColorsState.floatValue = it },
                            valueRange = 1f..10f
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
                        hide()
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    Button(onClick = { hide() }) {
                        Text("Cancel")
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