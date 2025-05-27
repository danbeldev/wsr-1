package com.example.ui_kit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class ChipState(
    val containerColor: Color,
    val textColor: Color,
    val border: BorderStroke?
) {
    On(Accent, White, null),
    Off(InputBg, Description, null)
}

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {},
    state: ChipState
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(48.dp)
            .width(128.dp),
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
            fontSize = 15.sp,
            fontWeight = FontWeight.W500
        )
    }
}

@Preview
@Composable
private fun ChipPrev() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ChipState.entries.forEach {
            Chip(
                text = "Популярные",
                state = it
            )

            Spacer(Modifier.height(16.dp))
        }
    }
}