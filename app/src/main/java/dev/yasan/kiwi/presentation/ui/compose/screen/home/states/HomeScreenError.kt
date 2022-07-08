package dev.yasan.kiwi.presentation.ui.compose.screen.home.states

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.yasan.kiwi.presentation.ui.compose.component.ErrorItem

@Composable
fun HomeScreenError(modifier: Modifier = Modifier, message: String, onClick: () -> Unit) {
    ErrorItem(modifier = modifier, message = message, onClick = onClick)
}