package dev.yasan.kiwi.presentation.ui.compose.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import dev.yasan.kiwi.presentation.ui.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class KiwiTitleKtTest {

    private val title = "title"

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            KiwiTitle(title = title)
        }
    }

    @Test
    fun errorIsDisplayed() {
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }

}