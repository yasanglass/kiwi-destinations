package dev.yasan.kiwi.presentation.ui.compose.screen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import dev.yasan.kit.core.Resource
import dev.yasan.kiwi.MainCoroutineRule
import dev.yasan.kiwi.TestDispatcherProvider
import dev.yasan.kiwi.data.repository.MockFlightRepository
import dev.yasan.kiwi.domain.usecase.GetPopularFlightsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var flightRepository: MockFlightRepository

    @Before
    fun setup() {
        flightRepository = MockFlightRepository()
        viewModel = HomeViewModel(
            dispatchers = TestDispatcherProvider,
            getPopularFlightsUseCase = GetPopularFlightsUseCase(
                dispatchers = TestDispatcherProvider,
                flightRepository = flightRepository
            )
        )
    }

    @Test
    fun `load popular flights, success`() = runTest {
        flightRepository.setShouldFail(false)
        viewModel.loadPopularFlights()
        advanceUntilIdle()
        Truth.assertThat(viewModel.popularFlights.value).isInstanceOf(Resource.Success::class.java)
    }

    @Test
    fun `load popular flights, failure`() = runTest {
        flightRepository.setShouldFail(true)
        viewModel.loadPopularFlights()
        advanceUntilIdle()
        Truth.assertThat(viewModel.popularFlights.value).isInstanceOf(Resource.Error::class.java)
    }

}