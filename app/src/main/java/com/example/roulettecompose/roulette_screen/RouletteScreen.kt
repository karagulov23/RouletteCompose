package com.example.roulettecompose.roulette_screen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.input.pointer.PointerIconDefaults.Text
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roulettecompose.R
import com.example.roulettecompose.utils.NumberUtil
import kotlin.math.roundToInt

@Composable
fun RouletteScreen() {

    var rotationValue by remember {
        mutableStateOf(0f)
    }
    var number by remember {
        mutableStateOf(0)
    }
    val angle: Float by animateFloatAsState(
        targetValue = rotationValue,
        animationSpec = tween(durationMillis = 2000),
        finishedListener = {
            val index = (360 - (it % 360)) / (360f / NumberUtil.NumberList.size)
            number = NumberUtil.NumberList[index.roundToInt()]
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
        ) {
        Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .wrapContentHeight()
                    .wrapContentWidth(),
                text = number.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
                color = Color.White
            )
        Box(modifier = Modifier
            .weight(1f)
            .fillMaxSize()){
            Image(
                painter = painterResource(id = R.drawable.img_roulette),
                contentDescription = "Roulette",
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(angle)
            )
            Image(
                painter = painterResource(id = R.drawable.img_arrow),
                contentDescription = "Arrow",
                modifier = Modifier.fillMaxSize()
            )
        }
        Button(onClick = {
                rotationValue = (720..1080).random().toFloat() + angle
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Red),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = "Start",
                color = White
            )

        }

    }
}