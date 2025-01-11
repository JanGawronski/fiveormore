package com.jangawronski.fiveandmore

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.foundation.Canvas
import androidx.compose.material.icons.filled.Settings


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(nextColors: Array<MutableIntState>, score: MutableIntState, onStart: () -> Unit, onMenuClick: () -> Unit, onRetry: () -> Unit, onStatsClick: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    if (nextColors[0].intValue != -1) {
                        Row {
                            Text("Score: ${score.intValue}")
                            Spacer(modifier = Modifier.width(8.dp))
                            nextColors.forEach { colorState ->
                                ColorCircle(color = Cell.getColorByIndex(colorState.intValue), size = 24.dp)
                                Spacer(modifier = Modifier.width(4.dp))
                            }
                        }
                    } else {
                        Button(onClick = onStart,
                            modifier = Modifier
                                .width(150.dp)
                                .height(40.dp)) {
                            Text("Start", fontSize = 20.sp)
                        }
                    }
                },
                actions = {
                    if (nextColors[0].intValue != -1)
                    IconButton(onClick = onRetry) {
                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = "Retry"
                        )
                    }
                    else
                        IconButton(onClick = onMenuClick) {
                            Icon(
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Settings"
                            )
                        }
                },
                navigationIcon = {
                    IconButton(onClick = onStatsClick) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Stats"
                        )
                    }
                }
            )
        },
    ) {}
}

@Composable
fun ColorCircle(color: Color, size: Dp) {
    Canvas(modifier = Modifier.size(size)) {
        drawCircle(color = color, radius = size.toPx() / 2)
    }
}