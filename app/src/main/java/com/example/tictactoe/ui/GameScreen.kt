package com.example.tictactoe.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tictactoe.ui.theme.BlueCustom
import com.example.tictactoe.ui.theme.GrayBackground

@Composable
fun GameScreen(
    viewModel: GameViewModel
){
    val state = viewModel.state
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayBackground)
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "Player 'O': 0", fontSize = 16.sp)
            Text(text = "Draw: 0", fontSize = 16.sp)
            Text(text = "Player 'X': 0", fontSize = 16.sp)
        }
        Text(
            text = "Tic Tac Toe",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            color = BlueCustom
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .shadow(
                    elevation = 10.dp,
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp))
                .background(GrayBackground),
            contentAlignment = Alignment.Center
        ){
            BoardBase()
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .aspectRatio(1f),
                columns = GridCells.Fixed(3)
            ){
                viewModel.boardItems.forEach{(cellNo, boardCellValue)->
                    item {
                        Column (
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .clickable {
                                    viewModel.onAction(
                                        UserActions.BoardTapped(cellNo)
                                    )
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ){
                            if(boardCellValue == BoardCellValue.CIRCLE){
                                Circle()
                            }
                            else if(boardCellValue == BoardCellValue.CROSS){
                                Cross()
                            }
                        }
                    }
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Player 'O' turn",
                fontSize = 24.sp,
                fontStyle = FontStyle.Italic
            )
            Button(
                onClick = {},
                shape = RoundedCornerShape(5.dp),
                elevation = ButtonDefaults.elevation(5.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = BlueCustom,
                    contentColor = Color.White
                )
            ){
                Text(text = "Play Again", fontSize = 16.sp)
            }
        }
    }
}

@Preview
@Composable
fun Prev(){
    GameScreen(
        viewModel = GameViewModel()
    )
}