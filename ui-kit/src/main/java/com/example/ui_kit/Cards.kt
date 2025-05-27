package com.example.ui_kit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardBackground(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(AbsoluteRoundedCornerShape(12.dp))
            .background(White, AbsoluteRoundedCornerShape(12.dp))
            .shadow(
                elevation = 10.dp,
                shape = AbsoluteRoundedCornerShape(12.dp),
                ambientColor = Color(0x99E4E8F5),
                spotColor = Color(0x99E4E8F5)
            )
            .clickable {
                onClick()
            },
        content = {
            Column {
                content()
            }
        }
    )
}

enum class PrimaryCardState(
    val text: String,
    val smallButtonState: SmallButtonState
) {
    Add("Добавить", SmallButtonState.Primary),
    Delete("Убрать", SmallButtonState.Secondary),
}

@Composable
fun PrimaryCard(
    title: String,
    category: String,
    price: String,
    state: PrimaryCardState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    CardBackground(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        content = {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.W500,
                    fontSize = 16.sp,
                    color = Black
                )

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = category,
                            color = Caption,
                            fontWeight = FontWeight.W600,
                            fontSize = 14.sp
                        )

                        Spacer(Modifier.height(4.dp))

                        Text(
                            text = price,
                            fontWeight = FontWeight.W600,
                            fontSize = 17.sp,
                            color = Black
                        )
                    }

                    SmallButton(
                        text = state.text,
                        state = state.smallButtonState
                    )
                }
            }
        }
    )
}

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    name: String,
    price: String,
    count: String,
    onClickCountPlus: () -> Unit = {},
    onClickCountMinus: () -> Unit = {},
) {
    CardBackground(
        modifier = modifier,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = name,
                    color = Black,
                    fontWeight = FontWeight.W500,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1.0f)
                )

                Image(
                    painter = painterResource(R.drawable.delete_1),
                    contentDescription = null
                )
            }

            Spacer(Modifier.height(34.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$price ₽",
                    color = Black,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.W500
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$count штук",
                        color = Black,
                        fontWeight = FontWeight.W400,
                        fontSize = 15.sp
                    )

                    Spacer(Modifier.width(42.dp))

                    Card(
                        modifier = Modifier
                            .width(64.dp)
                            .height(32.dp),
                        shape = AbsoluteRoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = InputBg
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Image(
                                painter = painterResource(R.drawable.minus),
                                contentDescription = null,
                                modifier = Modifier.clickable {
                                    onClickCountMinus()
                                }
                            )

                            VerticalDivider(
                                color = InputStroke
                            )

                            Image(
                                painter = painterResource(R.drawable.plus),
                                contentDescription = null,
                                modifier = Modifier.clickable {
                                    onClickCountPlus()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProjectCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    name: String,
    startDate: String
) {
    CardBackground(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = name,
                color = Black,
                fontWeight = FontWeight.W500,
                fontSize = 16.sp
            )

            Spacer(Modifier.height(44.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = startDate,
                    color = Caption,
                    fontWeight = FontWeight.W600,
                    fontSize = 14.sp
                )

                SmallButton(
                    text = "Открыть",
                    onClick = onClick
                )
            }
        }
    }
}

@Preview
@Composable
private fun PrimaryCardPrv() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(20.dp))

        PrimaryCard(
            title = "Рубашка Воскресенье для машинного вязания",
            category = "Мужская одежда",
            price = "300 ₽",
            state = PrimaryCardState.Add
        )

        PrimaryCard(
            title = "Рубашка Воскресенье для машинного вязания",
            category = "Мужская одежда",
            price = "300 ₽",
            state = PrimaryCardState.Delete
        )
    }
}