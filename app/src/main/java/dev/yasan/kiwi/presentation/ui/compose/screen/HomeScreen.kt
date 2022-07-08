package dev.yasan.kiwi.presentation.ui.compose.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.font.FontStyle
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import dev.yasan.kit.core.Resource

@Composable
@Destination
@RootNavGraph(start = true)
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val popularFlights = viewModel.popularFlights.collectAsState(initial = Resource.Initial())

    LaunchedEffect(key1 = popularFlights.value) {
        if (popularFlights.value is Resource.Initial) {
            viewModel.loadPopularFlights()
        }
    }

    LazyColumn {

        item {

            val text = when (popularFlights.value) {
                is Resource.Initial ->  "Initial"
                is Resource.Loading ->  "Loading"
                is Resource.Success ->  "Success"
                is Resource.Error ->  "Error"
                else -> "?"
            }

            Text(text = text, fontStyle = FontStyle.Italic)

        }

        val flights = popularFlights.value.data?.flightResponses ?: emptyList()

        items(items = flights) {
            it.route.forEach { route ->
                Text(text = route.cityFrom + " -> " + route.cityTo)
            }
            Divider()
        }

    }

}