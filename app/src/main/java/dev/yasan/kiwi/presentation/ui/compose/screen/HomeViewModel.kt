package dev.yasan.kiwi.presentation.ui.compose.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yasan.kit.core.DispatcherProvider
import dev.yasan.kit.core.Resource
import dev.yasan.kiwi.data.entity.SearchResultResponse
import dev.yasan.kiwi.domain.usecase.GetPopularFlightsUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [ViewModel] for [HomeScreen].
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val getPopularFlightsUseCase: GetPopularFlightsUseCase
) : ViewModel() {

    companion object {
        private const val TAG = "HomeViewModel"
    }

    private var _popularFlights =
        MutableStateFlow<Resource<SearchResultResponse>>(Resource.Initial())
    val popularFlights: StateFlow<Resource<SearchResultResponse>> get() = _popularFlights

    private var loadPopularFlightsJob: Job? = null

    fun loadPopularFlights() {
        loadPopularFlightsJob?.cancel()
        loadPopularFlightsJob = viewModelScope.launch(dispatchers.io) {
            _popularFlights.value = Resource.Loading()
            val data = getPopularFlightsUseCase()
            _popularFlights.value = data
        }
    }

}