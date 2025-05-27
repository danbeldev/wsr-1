package com.example.ui_kit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Input(
    value: String,
    onValueChance: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    topText: String? = null,
    errorText: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    placeholderFontSize: TextUnit = 15.sp,
    readOnly: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    disabled: Boolean = false
) {
    Column {
        topText?.let {
            Text(
                text = topText,
                color = Description,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400
            )

            Spacer(Modifier.height(8.dp))
        }

        OutlinedTextField(
            value = value,
            onValueChange = onValueChance,
            modifier = modifier,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            textStyle = TextStyle(
                color = Black,
                fontWeight = FontWeight.W400,
                fontSize = 15.sp
            ),
            enabled = !disabled,
            readOnly = readOnly,
            placeholder = {
                Text(
                    text = placeholder ?: "",
                    color = if(readOnly) Icons else Caption,
                    fontWeight = FontWeight.W400,
                    fontSize = placeholderFontSize
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = Black,
                unfocusedTextColor = Black,
                focusedIndicatorColor = Border,
                unfocusedIndicatorColor = if (errorText != null) Error else if(value.isNotEmpty()) Icons else InputStroke,
                focusedContainerColor = InputBg,
                unfocusedContainerColor = if (errorText != null) Error.copy(0.1f) else if (value.isNotEmpty()) InputBg else InputBg,
                focusedPlaceholderColor = if(readOnly) Icons else Caption,
                unfocusedPlaceholderColor = if(readOnly) Icons else Caption,
                cursorColor = Accent
            ),
            shape = AbsoluteRoundedCornerShape(10.dp)
        )

        errorText?.let {

            Spacer(Modifier.height(8.dp))

            Text(
                text = it,
                color = Error,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400
            )
        }
    }
}

@Preview
@Composable
private fun InputPrev1() {

    var value by remember { mutableStateOf("") }

    Input(
        value = value,
        onValueChance = {
            value = it
        },
        placeholder = "Введите имя"
    )
}

@Preview
@Composable
private fun InputPrev2() {
    Input(
        value = "Иван",
        onValueChance = {}
    )
}

@Preview
@Composable
private fun InputPrev4() {
    var value by remember { mutableStateOf("") }

    Input(
        value = value,
        onValueChance = {},
        placeholder = "Имя",
        errorText = "Error test"
    )
}

@Preview
@Composable
private fun InputPrev5() {
    Input(
        value = "",
        onValueChance = {},
        placeholder = "Имя",
        topText = "Name"
    )
}

@Preview
@Composable
private fun InputPrev6() {
    Input(
        value = "",
        onValueChance = {},
        placeholder = "1",
        modifier = Modifier.size(48.dp),
        placeholderFontSize = 20.sp
    )
}

@Preview
@Composable
private fun InputPrev7() {
    Input(
        value = "",
        onValueChance = {},
        placeholder = "Имя",
        readOnly = true
    )
}