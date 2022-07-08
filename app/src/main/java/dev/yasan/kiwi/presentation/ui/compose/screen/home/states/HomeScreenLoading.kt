package dev.yasan.kiwi.presentation.ui.compose.screen.home.states

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.yasan.kit.compose.foundation.grid

@Composable
fun HomeScreenLoading(modifier: Modifier = Modifier) {
    CircularProgressIndicator(modifier = modifier.padding(grid(2)))
}