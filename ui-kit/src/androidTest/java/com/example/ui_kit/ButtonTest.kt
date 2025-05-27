package com.example.ui_kit

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class ButtonTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun test() {
        var click = false
        composeRule.setContent {
            BigButton(
                state = BigButtonState.Inactive,
                modifier = Modifier.testTag("big_button"),
                text = "test",
                onClick = {
                    click = true
                }
            )
        }

        composeRule.onNodeWithText("test").performClick()
        Assert.assertFalse(click)
    }

    @Test
    fun test1() {
        var click = false
        composeRule.setContent {
            BigButton(
                state = BigButtonState.Primary,
                modifier = Modifier.testTag("big_button"),
                text = "test",
                onClick = {
                    click = true
                }
            )
        }

        composeRule.onNodeWithText("test").performClick()
        Assert.assertTrue(click)
    }
}