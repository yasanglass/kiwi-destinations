package dev.yasan.kiwi.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import dev.yasan.kiwi.presentation.ui.compose.KiwiApp
import dev.yasan.kiwi.presentation.ui.compose.theme.KiwiDestinationsTheme

/**
 * The main & only activity of Kiwi Destinations.
 *
 * Anything that can be put inside [KiwiApp] should not be in the activity.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KiwiDestinationsTheme {
                KiwiApp()
            }
        }

    }

}
