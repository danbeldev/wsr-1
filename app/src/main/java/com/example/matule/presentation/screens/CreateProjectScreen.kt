package com.example.matule.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.matule.LocalNetwork
import com.example.network.models.Category
import com.example.network.models.CreateProject
import com.example.network.models.ProjectType
import com.example.ui_kit.BigButton
import com.example.ui_kit.Black
import com.example.ui_kit.BottomTabBar
import com.example.ui_kit.Dropdown
import com.example.ui_kit.Input
import com.example.ui_kit.White
import com.example.ui_kit.defaultBarItems
import kotlinx.coroutines.launch

@Composable
fun CreateProjectScreen(
    navController: NavController
) {
    val network = LocalNetwork.current
    val scope = rememberCoroutineScope()

    val currentItem = remember { mutableStateOf(defaultBarItems[2]) }
    val categories = remember { mutableStateListOf<Category>() }

    var type by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    var to by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        categories.clear()
        categories.addAll(network.getAllCategories())
    }

    LaunchedEffect(currentItem) {
        if (currentItem.value.title != "Проекты") {
            navController.navigateUp()
        }
    }

    Scaffold(
        containerColor = White,
        bottomBar = {
            BottomTabBar(
                currentItem = currentItem
            )
        }
    ) { p ->
        Box(
            modifier = Modifier.padding(p)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(start = 21.dp, end = 19.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Spacer(Modifier.height(30.dp))

                    Text(
                        text = "Создать проект",
                        color = Black,
                        fontWeight = FontWeight.W600,
                        fontSize = 20.sp
                    )

                    Spacer(Modifier.height(31.dp))

                    Dropdown(
                        value = type,
                        label = "Тип",
                        onValueChange = {
                            type = it
                        },
                        items = ProjectType.entries.map { it.toString() },
                        modifierOutlinedTextField = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(16.dp))

                    Input(
                        value = name,
                        topText = "Название проекта",
                        placeholder = "Введите имя",
                        onValueChance = {
                            name = it
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(16.dp))

                    Input(
                        value = startDate,
                        topText = "Дата начала",
                        placeholder = "--.--.----",
                        onValueChance = {
                            startDate = it
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(16.dp))

                    Input(
                        value = endDate,
                        topText = "Дата Окончания",
                        placeholder = "--.--.----",
                        onValueChance = {
                            endDate = it
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(16.dp))

                    Dropdown(
                        value = to,
                        label = "Кому",
                        placeholder = "Введите имя",
                        onValueChange = {
                            to = it
                        },
                        items = listOf(
                            "test-1"
                        ),
                        modifierOutlinedTextField = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(16.dp))

                    Input(
                        value = desc,
                        topText = "Источник описания",
                        placeholder = "example.com",
                        onValueChance = {
                            desc = it
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(16.dp))

                    Dropdown(
                        value = category,
                        label = "Категория",
                        placeholder = "Выберите  категорию",
                        onValueChange = {
                            category = it
                        },
                        items = categories.map { it.name },
                        modifierOutlinedTextField = Modifier.fillMaxWidth()
                    )

                    Spacer(Modifier.height(16.dp))

                    BigButton(
                        text = "Сохранить",
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            scope.launch {
                                network.createProject(
                                    params = CreateProject(
                                        name = name,
                                        type = ProjectType.valueOf(type),
                                        startDate = startDate,
                                        endDate = endDate,
                                        to = to,
                                        desc = desc,
                                        categoryId = categories.first { it.name == category }.id
                                    )
                                )

                                navController.navigateUp()
                            }
                        }
                    )
                }

                item {
                    Spacer(Modifier.height(90.dp))
                }
            }
        }
    }
}