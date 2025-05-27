package com.example.ui_kit

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dropdown(
    value: String,
    onValueChange: (String) -> Unit,
    items: List<String>,
    modifier: Modifier = Modifier,
    modifierOutlinedTextField: Modifier = Modifier,
    placeholder: String = "",
    label: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    onClickClose: (() -> Unit)? = null
) {
    var modal by remember { mutableStateOf(false) }

    Column(
        modifier = modifier,
    ) {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                color = Description
            )

            Spacer(Modifier.height(4.dp))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = value,
                onValueChange = {},
                readOnly = true,
                modifier = modifierOutlinedTextField,
                shape = AbsoluteRoundedCornerShape(10.dp),
                textStyle = TextStyle(
                    color = Black,
                    fontWeight = FontWeight.W400,
                    fontSize = 16.sp
                ),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = InputStroke,
                    unfocusedIndicatorColor = InputStroke,
                    focusedContainerColor = InputBg,
                    unfocusedContainerColor = InputBg,
                ),
                leadingIcon = leadingIcon,
                placeholder = {
                    Text(
                        text = placeholder,
                        fontWeight = FontWeight.W400,
                        fontSize = 16.sp,
                        color = Caption
                    )
                },
                trailingIcon = {
                    Image(
                        painter = painterResource(R.drawable.bot),
                        contentDescription = null,
                        modifier = Modifier.testTag("mat-cl").clickable {
                            modal = !modal
                        }
                    )
                }
            )

            onClickClose?.let {
                Spacer(Modifier.width(16.dp))

                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            onClickClose()
                        },
                    tint = com.example.ui_kit.Icons,
                )
            }
        }
    }

    if (modal) {
        ModalBottomSheet(
            onDismissRequest = { modal = false }
        ) {
            LazyColumn {
                items(items) {
                    Text(
                        text = it,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                modal = false
                                onValueChange(it)
                            }
                    )
                    HorizontalDivider()
                    Spacer(Modifier.height(5.dp))
                }
            }
        }
    }
}

@Preview
@Composable
private fun DropdownPrev() {
    var value by remember { mutableStateOf("1") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(20.dp))

        Dropdown(
            value = value,
            items = listOf(
                "1",
                "2",
                "3"
            ),
            onValueChange = {
                value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp)
        )

        Spacer(Modifier.height(10.dp))

        Dropdown(
            value = value,
            items = listOf(
                "1",
                "2",
                "3"
            ),
            onValueChange = {
                value = it
            },
            onClickClose = {
                value = ""
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp)
        )

        Spacer(Modifier.height(10.dp))

        Dropdown(
            value = value,
            items = listOf(
                "1",
                "2",
                "3"
            ),
            label = "Дата",
            onValueChange = {
                value = it
            },
            onClickClose = {
                value = ""
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp)
        )

        Spacer(Modifier.height(10.dp))

        Dropdown(
            value = value,
            items = getDateListLegacy(365).mapIndexed { index, it ->
                when(index) {
                    0 -> "Сегодня"
                    1 -> "Вчера"
                    else -> SimpleDateFormat("d MMMM", Locale.getDefault()).format(it)
                }
            },
            label = "Дата",
            onValueChange = {
                value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp)
        )
    }
}

fun getDateListLegacy(daysCount: Int): List<Date> {
    require(daysCount >= 0) { "daysCount не может быть отрицательным" }

    val calendar = Calendar.getInstance()
    val dates = mutableListOf<Date>()

    (0 until daysCount).forEach { _ ->
        dates.add(calendar.time)
        calendar.add(Calendar.DAY_OF_YEAR, -1)
    }

    return dates
}
