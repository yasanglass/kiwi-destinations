package dev.yasan.kiwi.presentation.ui.compose.screen.home.states

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.yasan.kiwi.presentation.ui.compose.component.KiwiError

@Composable
fun HomeScreenError(modifier: Modifier = Modifier, message: String, onRetry: () -> Unit) {
    KiwiError(modifier = modifier, message = message, onRetry = onRetry)
}