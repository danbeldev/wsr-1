package com.example.matule.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.matule.R
import com.example.ui_kit.Black
import com.example.ui_kit.Caption
import com.example.ui_kit.Input

@Composable
fun TelegramOTPScreen(
    navController: NavController
) {
    val otp = remember { mutableStateListOf("", "", "", "") }
    var focusedIndex by remember { mutableIntStateOf(0) }
    val focusRequesters = remember { List(4) { FocusRequester() } }

    LaunchedEffect(otp.joinToString("")) {
        if (otp.all { it.isNotEmpty() }) {
            navController.navigate("PasswordScreen")
        }
    }

    LaunchedEffect(focusedIndex) {
        focusRequesters[focusedIndex].requestFocus()
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(Modifier.height(24.dp))

            Row {
                Spacer(Modifier.width(20.dp))

                Box(
                    modifier = Modifier.size(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.back),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp).padding(6.dp)
                    )
                }
            }

            Spacer(Modifier.height(138.dp))

            Text(
                text = "Введите код из Telegram",
                fontWeight = FontWeight.W900,
                fontSize = 17.sp,
                color = Black
            )

            Spacer(Modifier.height(24.dp))

            Row {
                repeat(4) { index ->
                    Input(
                        value = otp[index],
                        onValueChance = { value ->
                            when {
                                value.length == 1 && value[0].isDigit() -> {
                                    otp[index] = value
                                    if (index < 3) {
                                        focusedIndex = index + 1
                                    }
                                }
                                value.isEmpty() -> {
                                    otp[index] = ""
                                    if (index > 0) {
                                        focusedIndex = index - 1
                                    }
                                }
                            }
                        },
                        modifier = Modifier.size(48.dp)
                            .onFocusChanged { focusState ->
                                if (focusState.isFocused) {
                                    focusedIndex = index
                                }
                            }
                            .focusRequester(focusRequesters[index]),
                        placeholderFontSize = 20.sp
                    )

                    if (index != 3) {
                        Spacer(Modifier.width(16.dp))
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Отправить код повторно можно\nбудет через 55 секунд",
                color = Caption,
                textAlign = TextAlign.Center,
                fontSize = 15.sp,
                fontWeight = FontWeight.W400
            )
        }
    }
}