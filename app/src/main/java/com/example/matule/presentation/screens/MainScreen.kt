package com.example.matule.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.matule.LocalNetwork
import com.example.matule.presentation.common.openPdf
import com.example.network.models.Card
import com.example.network.models.Catalog
import com.example.network.models.Category
import com.example.network.models.Project
import com.example.ui_kit.Accent
import com.example.ui_kit.BigButton
import com.example.ui_kit.Black
import com.example.ui_kit.BottomTabBar
import com.example.ui_kit.Caption
import com.example.ui_kit.CardButton
import com.example.ui_kit.Chip
import com.example.ui_kit.ChipState
import com.example.ui_kit.Error
import com.example.ui_kit.Icons
import com.example.ui_kit.InputStroke
import com.example.ui_kit.PrimaryCard
import com.example.ui_kit.PrimaryCardState
import com.example.ui_kit.ProjectCard
import com.example.ui_kit.R
import com.example.ui_kit.SearchInput
import com.example.ui_kit.White
import com.example.ui_kit.defaultBarItems
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController
) {
    val network = LocalNetwork.current
    val scope = rememberCoroutineScope()

    var search = remember { mutableStateOf("") }
    val currentItem = remember { mutableStateOf(defaultBarItems[0]) }

    val categories = remember { mutableStateListOf<Category>() }
    var selectCategory by remember { mutableStateOf<Category?>(null) }
    val catalog = remember { mutableStateListOf<Catalog>() }
    var selectCatalog by remember { mutableStateOf<Catalog?>(null) }

    val card = remember { mutableStateListOf<Card>() }

    val projects = remember { mutableStateListOf<Project>() }

    LaunchedEffect(Unit) {
        categories.clear()
        categories.addAll(network.getAllCategories())

        catalog.clear()
        catalog.addAll(network.getAllCatalog())

        card.clear()
        card.addAll(network.getCard())

        projects.clear()
        projects.addAll(network.getAllProject())
    }

    Scaffold(
        containerColor = White,
        bottomBar = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (currentItem.value.title == "Каталог") {
                    CardButton(
                        price = card.sumOf { it.count * it.catalog.price }.toString(),
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                        onClick = {
                            navController.navigate("CardScreen")
                        }
                    )

                    Spacer(Modifier.height(32.dp))
                }

                BottomTabBar(
                    currentItem = currentItem
                )
            }
        }
    ) { p ->
        selectCatalog?.let {
            ModalBottomSheet(
                onDismissRequest = {
                    selectCatalog = null
                },
                dragHandle = null,
                containerColor = White
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                ) {
                    Spacer(Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = it.name,
                            color = Black,
                            fontWeight = FontWeight.W600,
                            fontSize = 20.sp,
                            modifier = Modifier.weight(1f)
                        )

                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .clip(CircleShape)
                                .background(Icons.copy(0.3f), CircleShape)
                                .clickable {
                                    selectCatalog = null
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(com.example.matule.R.drawable.close),
                                contentDescription = null
                            )
                        }
                    }

                    Spacer(Modifier.height(20.dp))

                    Text(
                        text = "Описание",
                        color = Caption,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp
                    )

                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = it.desc,
                        color = Black,
                        fontWeight = FontWeight.W400,
                        fontSize = 15.sp
                    )

                    Spacer(Modifier.height(89.dp))

                    Text(
                        text = "Примерный расход:",
                        color = Caption,
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp
                    )

                    Spacer(Modifier.height(4.dp))

                    Text(
                        text = "80-90 г",
                        color = Black,
                        fontWeight = FontWeight.W500,
                        fontSize = 16.sp
                    )

                    Spacer(Modifier.height(19.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        BigButton(
                            text = "Добавить за ${it.price} ₽",
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                scope.launch {
                                    network.addCard(it)
                                    card.clear()
                                    card.addAll(network.getCard())
                                }
                            }
                        )
                    }

                    Spacer(Modifier.height(40.dp))
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(p),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (currentItem.value.title == "Главная") {
                homeScreen(
                    search = search,
                    categories = categories,
                    catalog = catalog.filter {
                        catalogFilter(search, it, selectCategory)
                    },
                    selectCategory = selectCategory,
                    onClickCategory = {
                        selectCategory = if (selectCategory == it)
                            null
                        else
                            it
                    },
                    onClickCatalog = {
                        selectCatalog = it
                    }
                )
            } else if (currentItem.value.title == "Каталог") {
                catalogScreen(
                    search = search,
                    categories = categories,
                    catalog = catalog.filter {
                        catalogFilter(search, it, selectCategory)
                    },
                    selectCategory = selectCategory,
                    onClickCategory = {
                        selectCategory = if (selectCategory == it)
                            null
                        else
                            it
                    },
                    onClickCatalog = {
                        selectCatalog = it
                    }
                )
            } else if (currentItem.value.title == "Проекты") {
                projectsScreen(
                    projects = projects,
                    onClickAdd = {
                        navController.navigate("CreateProjectScreen")
                    }
                )
            } else {
                profileScreen()
            }

            item {
                Spacer(Modifier.height(80.dp))
            }
        }
    }
}

private fun catalogFilter(
    search: MutableState<String>,
    catalog1: Catalog,
    selectCategory: Category?
): Boolean {
    var isValid = true

    if (search.value.isNotEmpty())
        isValid = catalog1.name.contains(search.value)

    if (selectCategory != null) {
        isValid = catalog1.category.id == selectCategory.id
    }

    return isValid
}

private fun LazyListScope.homeScreen(
    search: MutableState<String>,
    categories: List<Category>,
    catalog: List<Catalog>,
    selectCategory: Category?,
    onClickCategory: (Category) -> Unit,
    onClickCatalog: (Catalog) -> Unit
) {
    item {
        Spacer(Modifier.height(24.dp))

        SearchInput(
            value = search.value,
            placeholder = "Искать  описания",
            modifierTextField = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            onValueChange = {
                search.value = it
            }
        )

        Spacer(Modifier.height(32.dp))

        Text(
            text = "Акции и новости",
            color = Caption,
            fontWeight = FontWeight.W600,
            fontSize = 17.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        Spacer(Modifier.height(16.dp))

        LazyRow {
            item {
                Spacer(Modifier.width(20.dp))
            }

            items(10) {
                Image(
                    painter = painterResource(R.drawable.banner),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 270.dp, height = 152.dp)
                        .padding(end = 16.dp)
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        Text(
            text = "Каталог описаний",
            color = Caption,
            fontWeight = FontWeight.W600,
            fontSize = 17.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        Spacer(Modifier.height(15.dp))

        LazyRow {
            item {
                Spacer(Modifier.width(20.dp))
            }

            items(categories) {
                Chip(
                    state = if (selectCategory?.id == it.id) ChipState.On else ChipState.Off,
                    text = it.name,
                    modifier = Modifier.padding(end = 16.dp),
                    onClick = {
                        onClickCategory(it)
                    }
                )
            }
        }

        Spacer(Modifier.height(25.dp))
    }

    items(catalog) {
        PrimaryCard(
            title = it.name,
            category = it.category.name,
            price = "${it.price} ₽",
            state = PrimaryCardState.Add,
            modifier = Modifier.padding(
                bottom = 16.dp,
                start = 20.dp,
                end = 20.dp
            ),
            onClick = {
                onClickCatalog(it)
            }
        )
    }
}

private fun LazyListScope.catalogScreen(
    search: MutableState<String>,
    categories: List<Category>,
    catalog: List<Catalog>,
    selectCategory: Category?,
    onClickCategory: (Category) -> Unit,
    onClickCatalog: (Catalog) -> Unit
) {
    item {
        Spacer(Modifier.height(28.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 20.dp)
        ) {
            SearchInput(
                value = search.value,
                placeholder = "Искать описания",
                onValueChange = { search.value = it }
            )

            Spacer(Modifier.width(38.dp))

            Image(
                painter = painterResource(com.example.matule.R.drawable.user_icon),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(Modifier.height(32.dp))

        LazyRow {
            item {
                Spacer(Modifier.width(20.dp))
            }

            items(categories) {
                Chip(
                    state = if (selectCategory?.id == it.id) ChipState.On else ChipState.Off,
                    text = it.name,
                    modifier = Modifier.padding(end = 16.dp),
                    onClick = {
                        onClickCategory(it)
                    }
                )
            }
        }

        Spacer(Modifier.height(20.dp))
    }

    items(catalog) {
        PrimaryCard(
            title = it.name,
            category = it.category.name,
            price = "${it.price} ₽",
            state = PrimaryCardState.Add,
            modifier = Modifier.padding(
                bottom = 12.dp,
                start = 20.dp,
                end = 20.dp
            ),
            onClick = {
                onClickCatalog(it)
            }
        )
    }
}

private fun LazyListScope.projectsScreen(
    projects: List<Project>,
    onClickAdd: () -> Unit
) {
    item {
        Spacer(Modifier.height(28.dp))

        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(Color.Transparent)
            )

            Text(
                text = "Проекты",
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                color = Black
            )

            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Add,
                contentDescription = null,
                tint = Icons,
                modifier = Modifier.clickable {
                    onClickAdd()
                }
            )
        }

        Spacer(Modifier.height((17 + 18).dp))
    }

    items(projects) {
        ProjectCard(
            name = it.name,
            startDate = it.startDate,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}

private fun LazyListScope.profileScreen(

) {
    item {
        val context = LocalContext.current
        var enabledNotification by remember { mutableStateOf(false) }

        Spacer(Modifier.height(32.dp))

        Text(
            text = "Эдуард",
            color = Black,
            fontWeight = FontWeight.W700,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "+7 967 078-58-37",
            color = Caption,
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 20.dp)
        )

        Spacer(Modifier.height(24.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 20.dp)
        ) {
            Image(
                painter = painterResource(com.example.matule.R.drawable.order),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )

            Spacer(Modifier.width(20.dp))

            Text(
                text = "Мои заказы",
                color = Black,
                fontWeight = FontWeight.W600,
                fontSize = 17.sp
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(com.example.matule.R.drawable.settings),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp)
                )

                Spacer(Modifier.width(20.dp))

                Text(
                    text = "Уведомления",
                    color = Black,
                    fontWeight = FontWeight.W600,
                    fontSize = 17.sp
                )
            }

            Switch(
                checked = enabledNotification,
                modifier = Modifier
                    .width(48.dp)
                    .height(28.dp)
                    .padding(end = 15.dp),
                onCheckedChange = {
                    enabledNotification = it
                },
                thumbContent = {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(White, AbsoluteRoundedCornerShape(90.dp))
                            .clip(AbsoluteRoundedCornerShape(90.dp))
                    )
                },
                colors = SwitchDefaults.colors(
                    uncheckedBorderColor = InputStroke,
                    checkedTrackColor = Accent,
                    uncheckedTrackColor = InputStroke,
                    uncheckedThumbColor = White
                )
            )
        }

        Spacer(Modifier.height(176.dp))

        Text(
            text = "Политика конфиденциальности",
            color = Caption,
            fontWeight = FontWeight.W500,
            fontSize = 15.sp,
            modifier = Modifier.padding(horizontal = 20.dp)
                .clickable {
                    context.openPdf(com.example.matule.R.raw.pol)
                }
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Пользовательское соглашение",
            color = Caption,
            fontWeight = FontWeight.W500,
            fontSize = 15.sp,
            modifier = Modifier.padding(horizontal = 20.dp)
                .clickable {
                    context.openPdf(com.example.matule.R.raw.pol)
                }
        )

        Spacer(Modifier.height(24.dp))

        Text(
            text = "Выход",
            color = Error,
            fontWeight = FontWeight.W500,
            fontSize = 15.sp,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
    }
}