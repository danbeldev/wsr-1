package com.example.matule.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.matule.R
import com.example.ui_kit.BigButton
import com.example.ui_kit.Black
import com.example.ui_kit.Input

@Composable
fun PasswordScreen(
    navController: NavController
) {
    var password by remember { mutableStateOf("") }
    var password1 by remember { mutableStateOf("") }

    var passwordVis by remember { mutableStateOf(false) }
    var passwordVis1 by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(Modifier.height(61.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.hello),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )

                Spacer(Modifier.width(16.dp))

                Text(
                    text = "Создание пароля",
                    color = Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.W700
                )
            }

            Spacer(Modifier.height(25.dp))

            Text(
                text = "Введите новый пароль",
                color = Black,
                fontWeight = FontWeight.W400,
                fontSize = 15.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(90.dp))

            Input(
                value = password,
                onValueChance = { password = it },
                topText = "Новый Пароль",
                visualTransformation = if (passwordVis) VisualTransformation.None else PasswordVisualTransformation(mask = '*'),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    if (password.isNotEmpty()) {
                        Image(
                            painter = painterResource(id = if (passwordVis) R.drawable.vector else R.drawable.open_your_eyes___iconsvg_co),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp).clickable {
                                passwordVis = !passwordVis
                            }
                        )
                    }
                }
            )

            Spacer(Modifier.height(12.dp))

            Input(
                value = password1,
                onValueChance = { password1 = it },
                topText = "Повторите пароль",
                visualTransformation = if (passwordVis1) VisualTransformation.None else PasswordVisualTransformation(mask = '*'),
                modifier = Modifier.fillMaxWidth(),
                trailingIcon = {
                    if (password.isNotEmpty()) {
                        Image(
                            painter = painterResource(id = if (passwordVis1) R.drawable.vector else R.drawable.open_your_eyes___iconsvg_co),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp).clickable {
                                passwordVis1 = !passwordVis1
                            }
                        )
                    }
                }
            )

            Spacer(Modifier.height(10.dp))

            BigButton(
                modifier = Modifier.fillMaxWidth(),
                text = "Далее",
                onClick = {
                    navController.navigate("MainScreen")
                }
            )
        }
    }
}