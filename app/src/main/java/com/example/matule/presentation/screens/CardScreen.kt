package com.example.matule.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.matule.LocalNetwork
import com.example.ui_kit.InputBg
import com.example.matule.R
import com.example.network.models.Card
import com.example.ui_kit.BigButton
import com.example.ui_kit.Black
import com.example.ui_kit.CartItem
import com.example.ui_kit.White

@Composable
fun CardScreen() {
    val network = LocalNetwork.current

    val cards = remember { mutableStateListOf<Card>() }

    LaunchedEffect(Unit) {
        cards.clear()
        cards.addAll(network.getCard())
    }

    Scaffold(
        containerColor = White,
        bottomBar = {
            BigButton(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = "Перейти к оформлению заказа"
            )
        }
    ) { p ->
        Box(
            modifier = Modifier.padding(p)
        ){
            LazyColumn(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                item {
                    Spacer(Modifier.height(16.dp))

                    Box(
                        modifier = Modifier.size(32.dp)
                            .background(InputBg, AbsoluteRoundedCornerShape(8.dp))
                            .clip(AbsoluteRoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(R.drawable.back),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Корзина",
                            color = Black,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.W700
                        )

                        Image(
                            painter = painterResource(com.example.ui_kit.R.drawable.delete),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(Modifier.height(32.dp))
                }

                items(cards) {
                    CartItem(
                        name = it.catalog.name,
                        price = it.catalog.price.toString(),
                        count = it.count.toString(),
                        modifier = Modifier.padding(
                            vertical = 16.dp
                        )
                    )
                }

                item {
                    Spacer(Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Сумма",
                            color = Black,
                            fontWeight = FontWeight.W600,
                            fontSize = 20.sp
                        )

                        Text(
                            text = "${cards.sumOf { it.count * it.catalog.price }} ₽",
                            color = Black,
                            fontWeight = FontWeight.W600,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}