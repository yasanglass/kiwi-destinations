package dev.yasan.kiwi.presentation.ui.compose.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.yasan.kiwi.presentation.ui.MainActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class KiwiErrorKtTest {

    private val errorMessage = "error"

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            KiwiError(message = errorMessage)
        }
    }

    @Test
    fun errorIsDisplayed() {
        composeTestRule.onNodeWithText(errorMessage).assertIsDisplayed()
    }

}