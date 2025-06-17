package com.infinitysoftware.rockpaperscissors

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun Game() {

    val choiceList = listOf("Rock", "Paper", "Scissors")
    var cpuChoice by remember { mutableStateOf(choiceList.random()) }
    var playerChoice by remember { mutableStateOf("") }

    var showRockButton by remember { mutableStateOf(true) }
    var showPaperButton by remember { mutableStateOf(true) }
    var showScissorsButton by remember { mutableStateOf(true) }

    Box(modifier = Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) {

        Column {
            Text(modifier = Modifier, text = cpuChoice, fontSize = 50.sp, color = Color.Blue)

            if (showRockButton) {
                Button(onClick = {
                    playerChoice = "Rock"

                    showRockButton = false
                    showPaperButton = false
                    showScissorsButton = false
                },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF000033),
                        contentColor = Color.Blue
                    )) {

                    Text(modifier = Modifier, text = "Rock", fontSize = 50.sp, color = Color.Blue)
                }
            }

            Button(onClick = { playerChoice = "Paper" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF000033),
                    contentColor = Color.Blue
                )) {

                Text(modifier = Modifier, text = "Paper", fontSize = 50.sp, color = Color.Blue)
            }

            Button(onClick = { playerChoice = "Scissors" },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF000033),
                    contentColor = Color.Blue
                )) {

                Text(modifier = Modifier, text = "Scissors", fontSize = 50.sp, color = Color.Blue)
            }

            // Game Logic Section.
            // Game Logic For The CPU-s Choice 'Rock'.
            if (playerChoice == "Rock" && cpuChoice == "Rock") {
                Text(modifier = Modifier, text = itsTieMessage, fontSize = 50.sp, color = Color.Blue)
            } else if (playerChoice == "Paper" && cpuChoice == "Rock") {
                Text(modifier = Modifier, text = playerWinMessage, fontSize = 50.sp, color = Color.Blue)
            } else if (playerChoice == "Scissors" && cpuChoice == "Rock") {
                Text(modifier = Modifier, text = cpuWinMessage, fontSize = 50.sp, color = Color.Blue)

                // Game Logic For The CPU-s Choice 'Paper'.
            } else if (playerChoice == "Rock" && cpuChoice == "Paper") {
                Text(modifier = Modifier, text = cpuWinMessage, fontSize = 50.sp, color = Color.Blue)
            } else if (playerChoice == "Paper" && cpuChoice == "Paper") {
                Text(modifier = Modifier, text = itsTieMessage, fontSize = 50.sp, color = Color.Blue)
            } else if (playerChoice == "Scissors" && cpuChoice == "Paper") {
                Text(modifier = Modifier, text = playerWinMessage, fontSize = 50.sp, color = Color.Blue)

                // Game Logic For The CPU-s Choice 'Scissros'.
            } else if (playerChoice == "Rock" && cpuChoice == "Scissors") {
                Text(modifier = Modifier, text = playerWinMessage, fontSize = 50.sp, color = Color.Blue)
            } else if (playerChoice == "Paper" && cpuChoice == "Scissors") {
                Text(modifier = Modifier, text = cpuWinMessage, fontSize = 50.sp, color = Color.Blue)
            } else if (playerChoice == "Scissors" && cpuChoice == "Scissors") {
                Text(modifier = Modifier, text = itsTieMessage, fontSize = 50.sp, color = Color.Blue)
            }
        }
    }
}