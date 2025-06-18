package com.infinitysoftware.rockpaperscissors

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

// Custom Made Preview Composable Functions Section.
@Preview(showBackground = true)
@Composable
fun FunctionPreviewer() {
    Game()
}

// Custom Made Composable Functions Section.
@Composable
fun Game(defaultFontSize: TextUnit = 30.sp, defaultCPUChoiceImageSize: Dp = 200.dp, defaultPlayerChoiceImageSize: Dp = 100.dp) {

    // Game Settings Section.
    // Variable/Constants Section.
    val choiceList = listOf(R.drawable.rock, R.drawable.paper, R.drawable.scissors)
    var cpuChoice by remember { mutableStateOf(choiceList.random()) }
    var playerChoice by remember { mutableStateOf("") }

    var showRockButton by remember { mutableStateOf(true) }
    var showPaperButton by remember { mutableStateOf(true) }
    var showScissorsButton by remember { mutableStateOf(true) }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val CPUImageSize = screenWidth / 2
    val PlayerImageSize = screenWidth / 4

    Column(modifier = Modifier.fillMaxSize()) {

        // Top Half Of The Screen.
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
                if (playerChoice == "") {
                    Image(
                        painter = painterResource(id = R.drawable.background),
                        contentDescription = "CPUs Choice!",
                        modifier = Modifier.size(CPUImageSize)
                    )
                } else {
                    Image(
                        painter = painterResource(id = cpuChoice),
                        contentDescription = "CPUs Choice!",
                        modifier = Modifier.size(CPUImageSize)
                    )
                }
            }
        }

        // Bottom Half Of The Screen.
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth().background(Color.Blue)
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
                        modifier = Modifier.size(PlayerImageSize)

                            .clickable{ playerChoice = "Rock"
                                showRockButton = true
                                showPaperButton = false
                                showScissorsButton = false
                            }
                    )
                }

                if (showPaperButton) {
                    Image(
                        painter = painterResource(id = R.drawable.paper),
                        contentDescription = "Paper",
                        modifier = Modifier.size(PlayerImageSize)

                            .clickable{ playerChoice = "Paper"
                                showRockButton = false
                                showPaperButton = true
                                showScissorsButton = false
                            }
                    )
                }

                if (showScissorsButton) {
                    Image(
                        painter = painterResource(id = R.drawable.scissors),
                        contentDescription = "Scissors",
                        modifier = Modifier.size(PlayerImageSize)

                            .clickable{ playerChoice = "Scissors"
                                showRockButton = false
                                showPaperButton = false
                                showScissorsButton = true
                            }
                    )
                }
            }
        }
    }
}