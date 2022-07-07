package dev.yasan.kiwi.presentation.ui.compose.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
@RootNavGraph(start = true)
fun HomeScreen(navigator: DestinationsNavigator) {

    Text(text = "Hello Kiwi")

}