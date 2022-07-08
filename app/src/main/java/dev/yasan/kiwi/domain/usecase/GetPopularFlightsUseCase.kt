package dev.yasan.kiwi.domain.usecase

import dev.yasan.kit.core.DispatcherProvider
import dev.yasan.kit.core.Resource
import dev.yasan.kiwi.data.entity.SearchResultResponse
import dev.yasan.kiwi.domain.repository.FlightRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Fetches the most popular flights sorted by popularity.
 * The result is wrapped inside a [Resource] as it handles the error states internally.
 */
class GetPopularFlightsUseCase @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val flightRepository: FlightRepository
) {

    suspend operator fun invoke(limit: Int = 5): Resource<SearchResultResponse> =
        withContext(dispatchers.io) {
            flightRepository.searchFlights(
                sort = "popularity",
                ascending = 0,
                flyFrom = "49.2-16.61-250km",
                flyTo = "anywhere",
                limit = limit,
            )
        }

}