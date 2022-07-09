package dev.yasan.kiwi.presentation.ui.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.DestinationsNavHost
import dev.yasan.kiwi.presentation.ui.compose.screen.home.NavGraphs
import dev.yasan.kiwi.presentation.ui.compose.screen.home.HomeScreen

/**
 * Top-level composable of the app.
 *
 * The navigation graph is generated automatically by [Compose-Destinations](https://github.com/raamcosta/compose-destinations) library.
 * The navigation root is [HomeScreen].
 */
@Composable
fun KiwiApp() {

    Surface(modifier = Modifier.fillMaxSize()) {
        DestinationsNavHost(navGraph = NavGraphs.root)
    }

}