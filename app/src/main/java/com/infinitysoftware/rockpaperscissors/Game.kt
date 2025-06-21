package com.infinitysoftware.rockpaperscissors

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Messages.
val itsTieMessage: String = "ITS TIE!"
val playerWinMessage: String = "PLAYER WIN!"
val cpuWinMessage: String = "CPU WIN!"

@Preview(showBackground = true)
@Composable
fun FunctionPreviewer() {
    Game()
}

@Composable
fun Game(
    defaultFontSize: TextUnit = 30.sp,
) {
    val choiceList = listOf(R.drawable.rock, R.drawable.paper, R.drawable.scissors)
    var cpuChoice by remember { mutableStateOf(choiceList.random()) }
    var playerChoice by remember { mutableStateOf("") }

    var showRockButton by remember { mutableStateOf(true) }
    var showPaperButton by remember { mutableStateOf(true) }
    var showScissorsButton by remember { mutableStateOf(true) }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val CPUImageSize = screenWidth / 2
    val PlayerImageSize = screenWidth / 4

    // Inner/Local Functions Section.
    fun resetGame() {
        playerChoice = ""
        cpuChoice = choiceList.random()
        showRockButton = true
        showPaperButton = true
        showScissorsButton = true
    }

    Column(modifier = Modifier.fillMaxSize()) {

        // Top
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Red)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val imageId = if (playerChoice == "") R.drawable.background else cpuChoice
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = "CPU's Choice",
                    modifier = Modifier.size(CPUImageSize)
                )
            }
        }

        // Middle Text Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            if (playerChoice == "") {
                Text(text = "UNDEFINED WINNER!", fontSize = defaultFontSize, color = Color.White)
            } else {
                val resultText: String
                val resultColor: Color

                if (
                    (playerChoice == "Rock" && cpuChoice == R.drawable.rock) ||
                    (playerChoice == "Paper" && cpuChoice == R.drawable.paper) ||
                    (playerChoice == "Scissors" && cpuChoice == R.drawable.scissors)
                ) {
                    resultText = itsTieMessage
                    resultColor = Color(0xFFFAC400)

                } else if (
                    (playerChoice == "Rock" && cpuChoice == R.drawable.scissors) ||
                    (playerChoice == "Paper" && cpuChoice == R.drawable.rock) ||
                    (playerChoice == "Scissors" && cpuChoice == R.drawable.paper)
                ) {
                    resultText = playerWinMessage
                    resultColor = Color.Blue
                } else {
                    resultText = cpuWinMessage
                    resultColor = Color.Red
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = resultText, fontSize = defaultFontSize, color = resultColor)
                }
            }
        }

        // Bottom
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Blue)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (showRockButton) {
                    Image(
                        painter = painterResource(id = R.drawable.rock),
                        contentDescription = "Rock",
                        modifier = Modifier
                            .size(PlayerImageSize)
                            .clickable {
                                if (playerChoice != "") {
                                    resetGame()
                                } else {
                                    playerChoice = "Rock"
                                    showRockButton = true
                                    showPaperButton = false
                                    showScissorsButton = false
                                }
                            }
                    )
                }

                if (showPaperButton) {
                    Image(
                        painter = painterResource(id = R.drawable.paper),
                        contentDescription = "Paper",
                        modifier = Modifier
                            .size(PlayerImageSize)
                            .clickable {
                                if (playerChoice != "") {
                                    resetGame()
                                } else {
                                    playerChoice = "Paper"
                                    showRockButton = false
                                    showPaperButton = true
                                    showScissorsButton = false
                                }
                            }
                    )
                }

                if (showScissorsButton) {
                    Image(
                        painter = painterResource(id = R.drawable.scissors),
                        contentDescription = "Scissors",
                        modifier = Modifier
                            .size(PlayerImageSize)
                            .clickable {
                                if (playerChoice != "") {
                                    resetGame()
                                } else {
                                    playerChoice = "Scissors"
                                    showRockButton = false
                                    showPaperButton = false
                                    showScissorsButton = true
                                }
                            }
                    )
                }
            }
        }
    }
}
