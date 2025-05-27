package com.example.ui_kit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class InputTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun test() {
        composeRule.setContent {
            var text by remember { mutableStateOf("") }

            Input(
                value = text,
                errorText = "",
                modifier = Modifier.testTag("input"),
                disabled = false,
                onValueChance = {
                    text = it
                }
            )
        }

        composeRule.onNodeWithTag("input").assertIsEnabled()
    }

    @Test
    fun test1() {
        var selectedValue by mutableStateOf("")

        composeRule.setContent {
            Dropdown(
                value = "",
                items = listOf(
                    "test-1",
                ),
                onValueChange = {
                    selectedValue = it
                }
            )
        }

        composeRule.onNodeWithTag("mat-cl").performClick()
        composeRule.onNodeWithText("test-1").performClick()

        composeRule.waitForIdle()

        Assert.assertEquals("test-1", selectedValue)
    }
}