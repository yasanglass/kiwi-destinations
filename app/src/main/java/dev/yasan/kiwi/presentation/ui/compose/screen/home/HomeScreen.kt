package dev.yasan.kiwi.presentation.ui.compose.screen.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import dev.yasan.kit.core.Resource
import dev.yasan.kiwi.R
import dev.yasan.kiwi.presentation.ui.compose.screen.home.states.HomeScreenError
import dev.yasan.kiwi.presentation.ui.compose.screen.home.states.HomeScreenLoading
import dev.yasan.kiwi.presentation.ui.compose.screen.home.states.homeScreenSuccess

@Composable
@Destination
@RootNavGraph(start = true)
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {

    val popularFlightsResource =
        viewModel.popularFlights.collectAsState(initial = Resource.Initial())

    LaunchedEffect(key1 = popularFlightsResource.value) {
        if (popularFlightsResource.value is Resource.Initial) {
            viewModel.loadPopularFlights()
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (val resource = popularFlightsResource.value) {
            is Resource.Success -> {
                homeScreenSuccess(flights = resource.data ?: emptyList())
            }
            is Resource.Error -> {
                item {
                    val messageResourceId = resource.messageResourceId ?: R.string.error_generic
                    HomeScreenError(
                        message = stringResource(id = messageResourceId),
                        onClick = {
                            viewModel.loadPopularFlights()
                        }
                    )
                }
            }
            else -> {
                item {
                    HomeScreenLoading()
                }
            }
        }

    }

}
