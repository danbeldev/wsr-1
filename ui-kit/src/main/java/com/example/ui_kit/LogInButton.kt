package com.example.ui_kit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class LogInButtonState(
    val text: String,
    val iconRes: Int
) {
    VK("Войти с VK", R.drawable.vk),
    Yandex("Войти с Yandex", R.drawable.yandex)
}

@Composable
fun LogInButton(
    state: LogInButtonState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier.width(335.dp).height(60.dp),
        shape = AbsoluteRoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, InputStroke),
        colors = ButtonDefaults.buttonColors(
            containerColor = White
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(state.iconRes),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )

            Spacer(Modifier.width(16.dp))

            Text(
                text = state.text,
                color = Black,
                fontWeight = FontWeight.W500,
                fontSize = 17.sp
            )
        }
    }
}

@Preview
@Composable
private fun LogInButtonPrev() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LogInButtonState.entries.forEach {
            LogInButton(it)

            Spacer(Modifier.height(12.dp))
        }
    }
}