package dev.yasan.kiwi.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import dev.yasan.kit.core.Resource
import dev.yasan.kiwi.MainCoroutineRule
import dev.yasan.kiwi.TestDispatcherProvider
import dev.yasan.kiwi.data.repository.MockFlightRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetPopularFlightsUseCaseTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var getPopularFlightsUseCase: GetPopularFlightsUseCase
    private lateinit var flightRepository: MockFlightRepository

    @Before
    fun setup() {
        flightRepository = MockFlightRepository()
        getPopularFlightsUseCase = GetPopularFlightsUseCase(
            dispatchers = TestDispatcherProvider,
            flightRepository = flightRepository
        )
    }

    @Test
    fun `get popular flights, success`() = runTest {
        flightRepository.setShouldFail(false)
        val popularFlights = getPopularFlightsUseCase()
        Truth.assertThat(popularFlights).isInstanceOf(Resource.Success::class.java)
    }

    @Test
    fun `get popular flights, failure`() = runTest {
        flightRepository.setShouldFail(true)
        val popularFlights = getPopularFlightsUseCase()
        Truth.assertThat(popularFlights).isInstanceOf(Resource.Error::class.java)
    }

}