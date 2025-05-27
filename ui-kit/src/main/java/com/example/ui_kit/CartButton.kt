package com.example.ui_kit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CartButton(
    modifier: Modifier = Modifier,
    text: String = "В корзину",
    price: String = "",
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .width(335.dp)
            .height(56.dp)
            .clip(AbsoluteRoundedCornerShape(10.dp))
            .background(Accent, AbsoluteRoundedCornerShape(10.dp))
            .clickable {
                onClick()
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Image(
                    painter = painterResource(R.drawable.cart),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )

                Spacer(Modifier.width(16.dp))

                Text(
                    text = text,
                    fontWeight = FontWeight.W600,
                    fontSize = 17.sp,
                    color = White
                )
            }

            Text(
                text = price,
                color = White,
                fontWeight = FontWeight.W600,
                fontSize = 17.sp
            )
        }
    }
}

@Preview
@Composable
private fun CartButtonPrev() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CartButton(
            price = "500 ₽"
        )
    }
}