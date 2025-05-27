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
import com.example.ui_kit.HeaderState.*

enum class HeaderState {
    Big,
    Small
}

@Composable
fun Header(
    modifier: Modifier = Modifier,
    state: HeaderState,
    title: String,
    onBack: () -> Unit = {},
    endIcon: Int? = null,
    onEndClick: () -> Unit = {}
) {
    when(state) {
        Big -> {
            Column(
                modifier = modifier
            ) {
                Back(
                    onClick = onBack
                )

                Spacer(Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        color = Black,
                        fontWeight = FontWeight.W700,
                        fontSize = 24.sp
                    )

                    endIcon?.let {
                        Image(
                            painter = painterResource(endIcon),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                                .clickable { onEndClick() }
                        )
                    }
                }
            }
        }
        Small -> {
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Back(
                    onClick = onBack
                )

                Text(
                    text = title,
                    color = Black,
                    fontWeight = FontWeight.W600,
                    fontSize = 20.sp
                )

                endIcon?.let {
                    Image(
                        painter = painterResource(endIcon),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                            .clickable { onEndClick() }
                    )
                }
            }
        }
    }
}

@Composable
fun Back(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .size(32.dp)
            .clip(AbsoluteRoundedCornerShape(8.dp))
            .background(InputBg)
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.back),
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Preview
@Composable
private fun HeaderSmallPrev() {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 36.dp)
    ) {
        Spacer(Modifier.height(10.dp))

        Header(
            state = Small,
            title = "Корзина",
            endIcon = R.drawable.delete
        )
    }
}

@Preview
@Composable
private fun HeaderBigPrev() {
    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 16.dp, end = 61.dp)
    ) {
        Spacer(Modifier.height(10.dp))

        Header(
            state = Big,
            title = "Корзина",
            endIcon = R.drawable.delete
        )
    }
}