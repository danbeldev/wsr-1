package com.example.ui_kit

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class BottomTabBarItem(
    val icon: Int,
    val title: String
)

val defaultBarItems = listOf(
    BottomTabBarItem(
        icon = R.drawable.b_1,
        title = "Главная"
    ),
    BottomTabBarItem(
        icon = R.drawable.b_2,
        title = "Каталог"
    ),
    BottomTabBarItem(
        icon = R.drawable.b_3,
        title = "Проекты"
    ),
    BottomTabBarItem(
        icon = R.drawable.b_4,
        title = "Профиль"
    )
)

@Composable
fun BottomTabBar(
    currentItem: MutableState<BottomTabBarItem>,
    modifier: Modifier = Modifier,
    items: List<BottomTabBarItem> = defaultBarItems
) {
    NavigationBar(
        containerColor = White,
        modifier = modifier.border(1.dp, Color(0x4DA0A0A0))
    ) {
        items.forEach {
            NavigationBarItem(
                selected = currentItem.value == it,
                icon = {
                    Icon(
                        painter = painterResource(it.icon),
                        contentDescription = null,
                        tint = if (it == currentItem.value)
                            Accent
                        else
                            Icons
                    )
                },
                label = {
                    Text(
                        text = it.title,
                        fontWeight = FontWeight.W400,
                        fontSize = 12.sp,
                        color = if (it == currentItem.value)
                            Accent
                        else
                            Icons
                    )
                },
                onClick = {
                    currentItem.value = it
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Preview
@Composable
private fun BottomTabBarPrev() {
    val currentItem = remember { mutableStateOf(defaultBarItems[0]) }

    Scaffold(
        bottomBar = {
            BottomTabBar(
                currentItem = currentItem
            )
        }
    ) { p ->
        Box(modifier = Modifier.padding(p)) {

        }
    }
}