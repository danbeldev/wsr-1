package com.example.matule.presentation.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ui_kit.BigButton
import com.example.ui_kit.BigButtonState
import com.example.ui_kit.Black
import com.example.ui_kit.Caption
import com.example.ui_kit.Dropdown
import com.example.ui_kit.Input

@Composable
fun CreateProfileScreen(
    navController: NavController
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var middleName by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf("") }
    var pol by remember { mutableStateOf("") }
    var telegram by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier.padding(horizontal = 20.dp)
    ) {
        item {
            Spacer(Modifier.height(32.dp))

            Text(
                text = "Создание Профиля",
                fontWeight = FontWeight.W700,
                fontSize = 24.sp,
                color = Black
            )

            Spacer(Modifier.height(44.dp))

            Text(
                text = "Без профиля вы не сможете создавать проекты.",
                color = Caption,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "В профиле будут храниться результаты проектов и ваши описания.",
                color = Caption,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp
            )

            Spacer(Modifier.height(32.dp))

            Input(
                value = firstName,
                onValueChance = { firstName = it },
                placeholder = "Имя",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            Input(
                value = middleName,
                onValueChance = { middleName = it },
                placeholder = "Отчество",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            Input(
                value = lastName,
                onValueChance = { lastName = it },
                placeholder = "Фамилия",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            Input(
                value = birthday,
                onValueChance = { birthday = it },
                placeholder = "Дата рождения",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            Dropdown(
                value = pol,
                onValueChange = { pol = it },
                items = listOf("Мужской", "Женский"),
                placeholder = "Пол",
                modifierOutlinedTextField = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            Input(
                value = telegram,
                onValueChance = { telegram = it },
                placeholder = "Telegram",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(68.dp))

            BigButton(
                text = "Создать",
                modifier = Modifier.fillMaxWidth(),
                state = if (
                    firstName.isNotEmpty() && lastName.isNotEmpty() && middleName.isNotEmpty()
                    && birthday.isNotEmpty() && pol.isNotEmpty() && telegram.isNotEmpty()
                )
                    BigButtonState.Primary
                else
                    BigButtonState.Inactive,
                onClick = {
                    navController.navigate("TelegramOTPScreen")
                }
            )
        }
    }
}