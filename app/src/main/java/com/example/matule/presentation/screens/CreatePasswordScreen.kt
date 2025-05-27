package com.example.matule.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ui_kit.Accent
import com.example.ui_kit.Black
import com.example.ui_kit.Caption
import com.example.ui_kit.InputBg
import com.example.ui_kit.White
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.matule.R

@Composable
fun CreatePasswordScreen(
    navController: NavController
) {
    var password by remember { mutableStateOf("") }

    LaunchedEffect(password) {
        if (password.length == 4) {
            navController.navigate("CreateProfileScreen")
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(Modifier.height(40.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Пропустить",
                    color = Accent,
                    fontWeight = FontWeight.W400,
                    fontSize = 15.sp
                )
            }

            Spacer(Modifier.height(40.dp))

            Text(
                text = "Создайте пароль",
                color = Black,
                fontWeight = FontWeight.W700,
                fontSize = 24.sp
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Для защиты ваших персональных данных",
                color = Caption,
                fontWeight = FontWeight.W400,
                fontSize = 15.sp
            )

            Spacer(Modifier.height(56.dp))

            Row {
                repeat(4) {
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .background(
                                if (password.length >= it + 1) Accent else White,
                                AbsoluteRoundedCornerShape(90.dp)
                            )
                            .clip(AbsoluteRoundedCornerShape(90.dp))
                            .border(1.dp, if (password.length >= it + 1) Color.Transparent else Accent, AbsoluteRoundedCornerShape(90.dp))
                    )

                    Spacer(Modifier.width(12.dp))
                }
            }

            Spacer(Modifier.height(60.dp))

            RowNumbers(
                pl = 1
            ) {
                password += it
            }

            Spacer(Modifier.height(24.dp))

            RowNumbers(
                pl = 4
            ) {
                password += it
            }

            Spacer(Modifier.height(24.dp))

            RowNumbers(
                pl = 7
            ) {
                password += it
            }

            Spacer(Modifier.height(24.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                NumberInput(
                    value = "0",
                    isTransparent = true
                ) {}

                Spacer(Modifier.width(5.dp))

                NumberInput(
                    value = "0"
                ) {
                    password += 0
                }

                Spacer(Modifier.width(46.dp))

                Image(
                    painter = painterResource(R.drawable.del_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 34.5.dp, height = 24.dp)
                        .clickable {
                            if (password.isNotEmpty()) {
                                password = password.dropLast(1)
                            }
                        }
                )
            }
        }
    }
}

@Composable
private fun RowNumbers(
    pl: Int,
    onClick: (String) -> Unit
) {
    Row {
        repeat(3) {
            NumberInput(
                value = (it + pl).toString()
            ) {
                onClick((it + pl).toString())
            }

            if (it != 2) {
                Spacer(Modifier.width(24.dp))
            }
        }
    }
}

@Composable
private fun NumberInput(
    value: String,
    isTransparent: Boolean = false,
    onClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    var isClick by remember { mutableStateOf(false) }

    Color.Transparent

    Box(
        modifier = Modifier
            .size(80.dp)
            .background(
                if (isTransparent) Color.Transparent else if (isClick) Accent else InputBg,
                AbsoluteRoundedCornerShape(90.dp)
            )
            .clip(AbsoluteRoundedCornerShape(90.dp))
            .clickable {
                if (!isTransparent) {
                    scope.launch {
                        isClick = true
                        delay(200L)
                        isClick = false
                    }

                    onClick()
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            fontWeight = FontWeight.W600,
            color = if (isTransparent) Color.Transparent else if (isClick) White else Black,
            fontSize = 24.sp
        )
    }
}