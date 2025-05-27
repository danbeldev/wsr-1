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
import com.example.ui_kit.Accent
import com.example.ui_kit.BigButton
import com.example.ui_kit.BigButtonState
import com.example.ui_kit.Black
import com.example.ui_kit.Caption
import com.example.ui_kit.Input
import com.example.ui_kit.LogInButton
import com.example.ui_kit.LogInButtonState

@Composable
fun LoginScreen(
    navController: NavController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVis by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Spacer(Modifier.height(59.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(R.drawable.hello),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )

                Spacer(Modifier.width(16.dp))

                Text(
                    text = "Добро пожаловать!",
                    fontWeight = FontWeight.W700,
                    fontSize = 24.sp,
                    color = Black
                )
            }

            Spacer(Modifier.height(23.dp))

            Text(
                text = "Войдите, чтобы пользоваться функциями приложения",
                fontWeight = FontWeight.W400,
                fontSize = 15.sp,
                color = Black
            )

            Spacer(Modifier.height(64.dp))

            Input(
                value = email,
                topText = "Вход по E-mail",
                placeholder = "example@mail.com",
                modifier = Modifier.fillMaxWidth(),
                onValueChance = {
                    email = it
                }
            )

            Spacer(Modifier.height(14.dp))

            Input(
                value = password,
                topText = "Пароль",
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (passwordVis) VisualTransformation.None else PasswordVisualTransformation(mask = '*'),
                onValueChance = {
                    password = it
                },
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

            Spacer(Modifier.height(14.dp))

            BigButton(
                text = "Далее",
                modifier = Modifier.fillMaxWidth(),
                state = if(email.isNotEmpty() && password.isNotEmpty())
                    BigButtonState.Primary
                else
                    BigButtonState.Inactive,
                onClick = {
                    navController.navigate("CreatePasswordScreen")
                }
            )

            Spacer(Modifier.height(15.dp))

            Text(
                text = "Забыл пароль?",
                color = Accent,
                fontWeight = FontWeight.W400,
                fontSize = 15.sp
            )

            Spacer(Modifier.height(59.dp))

            Text(
                text = "Или войдите с помощью",
                color = Caption,
                fontWeight = FontWeight.W400,
                fontSize = 15.sp
            )

            Spacer(Modifier.height(16.dp))

            LogInButton(
                state = LogInButtonState.VK,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            LogInButton(
                state = LogInButtonState.Yandex,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}