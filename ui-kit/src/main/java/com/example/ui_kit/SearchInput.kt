package com.example.ui_kit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
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
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifierRow: Modifier = Modifier,
    modifierTextField: Modifier = Modifier,
    placeholder: String = "",
    cancelBt: Boolean = false,
    onCancelClick: () -> Unit = {},
    onCloseClick: () -> Unit = {}
) {
    var isFocused by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifierRow
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifierTextField.onFocusChanged {
                isFocused = it.isFocused
            },
            placeholder = {
                Text(
                    text = placeholder,
                    color = Caption,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W400
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Description,
                    modifier = Modifier.size(20.dp)
                )
            },
            trailingIcon = {
                if (isFocused) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Description,
                        modifier = Modifier.size(20.dp)
                            .clickable { onCloseClick() }
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = InputStroke,
                unfocusedIndicatorColor = InputStroke,
                focusedContainerColor = InputBg,
                unfocusedContainerColor = InputBg,
                focusedTextColor = Black,
                unfocusedTextColor = Black,
                cursorColor = Accent
            ),
            shape = AbsoluteRoundedCornerShape(10.dp)
        )

        if (cancelBt) {
            Spacer(Modifier.width(16.dp))

            Text(
                text = "Отменить",
                color = Accent,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                modifier = Modifier.clickable {
                    onCancelClick()
                }
            )
        }
    }
}

@Preview
@Composable
private fun SearchInputPrev() {
    var value by remember { mutableStateOf("") }

    Column {
        SearchInput(
            value = value,
            onValueChange = {
                value = it
            },
            placeholder = "Искать описание",
            modifierRow = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 20.dp),
            onCloseClick = {
                value = ""
            }
        )

        SearchInput(
            value = value,
            onValueChange = {
                value = it
            },
            placeholder = "Искать описание",
            modifierRow = Modifier.fillMaxWidth().padding(horizontal = 10.dp, vertical = 20.dp),
            modifierTextField = Modifier.width(257.dp),
            cancelBt = true,
            onCancelClick = {
                value = ""
            }
        )
    }
}