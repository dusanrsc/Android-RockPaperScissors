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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infinitysoftware.rockpaperscissors.ui.theme.TieYellow

// Game Messages.
val itsTieMessage = "ITS TIE!"
val playerWinMessage = "PLAYER WIN!"
val cpuWinMessage = "CPU WIN!"

@Preview(showBackground = true)
@Composable
fun FunctionPreviewer() {
    Game()
}

@Composable
fun Game(defaultFontSize: TextUnit = 30.sp) {

    // Game State Variables.
    val choiceList = listOf(R.drawable.rock, R.drawable.paper, R.drawable.scissors)
    var cpuChoice by remember { mutableStateOf(choiceList.random()) }
    var playerChoice by remember { mutableStateOf("") }

    var showRockButton by remember { mutableStateOf(true) }
    var showPaperButton by remember { mutableStateOf(true) }
    var showScissorsButton by remember { mutableStateOf(true) }

    var playerWinCounter by remember { mutableStateOf(0) }
    var cpuWinCounter by remember { mutableStateOf(0) }

    var allowCounter by remember { mutableStateOf(false) }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val CPUImageSize = screenWidth / 2
    val PlayerImageSize = screenWidth / 4

    // Function To Reset The Game State.
    fun resetGame() {
        playerChoice = ""
        cpuChoice = choiceList.random()
        showRockButton = true
        showPaperButton = true
        showScissorsButton = true
        allowCounter = false
    }

    Column(modifier = Modifier.fillMaxSize()) {

        // Top Section. Shows CPU Image And Score.
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Red)
        ) {
            Text(
                text = "Win: $cpuWinCounter",
                fontSize = defaultFontSize,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val imageId = if (playerChoice.isEmpty()) R.drawable.background else cpuChoice
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = "CPU's Choice",
                    modifier = Modifier.size(CPUImageSize)
                )
            }
        }

        // Middle Section. Displays Result Text Based On Choices.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            if (playerChoice.isEmpty()) {
                Text("UNDEFINED WINNER!", fontSize = defaultFontSize, color = Color.White)
            } else {
                val resultText: String
                val resultColor: Color

                // Check For Game Outcome.
                if (
                    (playerChoice == "Rock" && cpuChoice == R.drawable.rock) ||
                    (playerChoice == "Paper" && cpuChoice == R.drawable.paper) ||
                    (playerChoice == "Scissors" && cpuChoice == R.drawable.scissors)
                ) {
                    resultText = itsTieMessage
                    resultColor = TieYellow
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

                // Add Points Only Once Per Game.
                if (!allowCounter && playerChoice.isNotEmpty()) {
                    if (
                        (playerChoice == "Rock" && cpuChoice == R.drawable.scissors) ||
                        (playerChoice == "Paper" && cpuChoice == R.drawable.rock) ||
                        (playerChoice == "Scissors" && cpuChoice == R.drawable.paper)
                    ) {
                        playerWinCounter++
                    } else if (
                        (playerChoice == "Rock" && cpuChoice == R.drawable.paper) ||
                        (playerChoice == "Paper" && cpuChoice == R.drawable.scissors) ||
                        (playerChoice == "Scissors" && cpuChoice == R.drawable.rock)
                    ) {
                        cpuWinCounter++
                    }
                    allowCounter = true
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(resultText, fontSize = defaultFontSize, color = resultColor)
                }
            }
        }

        // Bottom Section. Shows Player Choices And Player Score.
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .background(Color.Blue)
        ) {
            Text(
                text = "Win: $playerWinCounter",
                fontSize = defaultFontSize,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(8.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (showRockButton) {
                    ChoiceImage(
                        id = R.drawable.rock,
                        label = "Rock",
                        size = PlayerImageSize,
                        currentChoice = playerChoice,
                        onSelect = {
                            playerChoice = "Rock"
                            showPaperButton = false
                            showScissorsButton = false
                        },
                        onReset = {
                            resetGame()
                        }
                    )
                }

                if (showPaperButton) {
                    ChoiceImage(
                        id = R.drawable.paper,
                        label = "Paper",
                        size = PlayerImageSize,
                        currentChoice = playerChoice,
                        onSelect = {
                            playerChoice = "Paper"
                            showRockButton = false
                            showScissorsButton = false
                        },
                        onReset = {
                            resetGame()
                        }
                    )
                }

                if (showScissorsButton) {
                    ChoiceImage(
                        id = R.drawable.scissors,
                        label = "Scissors",
                        size = PlayerImageSize,
                        currentChoice = playerChoice,
                        onSelect = {
                            playerChoice = "Scissors"
                            showRockButton = false
                            showPaperButton = false
                        },
                        onReset = {
                            resetGame()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ChoiceImage(
    id: Int,
    label: String,
    size: Dp,
    currentChoice: String,
    onSelect: () -> Unit,
    onReset: () -> Unit
) {
    // Image Button For Player Choice.
    Image(
        painter = painterResource(id = id),
        contentDescription = label,
        modifier = Modifier
            .size(size)
            .clickable {
                if (currentChoice.isNotEmpty()) {
                    onReset()
                } else {
                    onSelect()
                }
            }
    )
}
