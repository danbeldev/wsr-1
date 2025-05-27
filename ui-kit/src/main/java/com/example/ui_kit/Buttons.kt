package com.example.ui_kit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CardButton(
    price: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(335.dp)
            .height(56.dp),
        shape = AbsoluteRoundedCornerShape(10.dp),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Accent,
        ),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.cart),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(Modifier.width(16.dp))

                Text(
                    text = "В корзину",
                    color = White,
                    fontWeight = FontWeight.W600,
                    fontSize = 17.sp
                )
            }

            Text(
                text = "$price ₽",
                color = White,
                fontWeight = FontWeight.W600,
                fontSize = 17.sp
            )
        }
    }
}

enum class BigButtonState(
    val containerColor: Color,
    val textColor: Color,
    val border: BorderStroke?
) {
    Primary(Accent, White, null),
    Inactive(AccentInactive, White, null),
    Secondary(White, Accent, BorderStroke(1.dp, Accent))
}

@Composable
fun BigButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {},
    state: BigButtonState = BigButtonState.Primary
) {
    Button(
        onClick = {
            if (state != BigButtonState.Inactive) {
                onClick()
            }
        },
        modifier = modifier
            .width(335.dp)
            .height(56.dp),
        shape = AbsoluteRoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = state.containerColor,
        ),
        border = state.border
    ) {
        Text(
            text = text,
            color = state.textColor,
            fontSize = 17.sp,
            fontWeight = FontWeight.W600
        )
    }
}

enum class MediumButtonState(
    val containerColor: Color,
    val textColor: Color,
    val border: BorderStroke?
) {
    Primary(Accent, White, null),
    Secondary(White, Accent, BorderStroke(1.dp, Accent)),
    Tetriary(InputBg, Black, null)
}


@Composable
fun MediumButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {},
    state: MediumButtonState = MediumButtonState.Primary
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(335.dp)
            .height(48.dp),
        shape = AbsoluteRoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = state.containerColor,
        ),
        border = state.border
    ) {
        Text(
            text = text,
            color = state.textColor,
            fontSize = 15.sp,
            fontWeight = FontWeight.W400
        )
    }
}

enum class SmallButtonState(
    val containerColor: Color,
    val textColor: Color,
    val border: BorderStroke?
) {
    Primary(Accent, White, null),
    Inactive(AccentInactive, White, null),
    Secondary(White, Accent, BorderStroke(1.dp, Accent))
}

@Composable
fun SmallButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {},
    state: SmallButtonState = SmallButtonState.Primary
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(96.dp)
            .height(40.dp),
        shape = AbsoluteRoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = state.containerColor,
        ),
        border = state.border,
        contentPadding = PaddingValues()
    ) {
        Text(
            text = text,
            color = state.textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.W600
        )
    }
}

@Preview
@Composable
private fun BigButtonPrev() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BigButtonState.entries.forEach {
            BigButton(
                text = "Подтвердить",
                state = it
            )

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
private fun MediumButtonPrev() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        MediumButtonState.entries.forEach {
            MediumButton(
                text = "Добавить проект",
                state = it
            )

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Preview
@Composable
private fun SmallButtonPrev() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SmallButtonState.entries.forEach {
            SmallButton(
                text = "Добавить",
                state = it
            )

            Spacer(Modifier.height(16.dp))
        }
    }
}