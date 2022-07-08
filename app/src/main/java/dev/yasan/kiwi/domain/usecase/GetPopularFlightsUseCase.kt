package dev.yasan.kiwi.domain.usecase

import dev.yasan.kit.core.DispatcherProvider
import dev.yasan.kit.core.Resource
import dev.yasan.kiwi.R
import dev.yasan.kiwi.domain.entity.Flight
import dev.yasan.kiwi.domain.repository.FlightRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Returns a list of most popular [Flight]s.
 * The source of the data is automatically chosen based on the quality and availability of the local data.
 * As the [Flight] list is wrapped in a [Resource], all error scenarios are handled internally.
 */
class GetPopularFlightsUseCase @Inject constructor(
    private val dispatchers: DispatcherProvider,
    private val flightRepository: FlightRepository
) {

    suspend operator fun invoke(
        limit: Int = 5,
        flyFrom: String = "49.2-16.61-250km"
    ): Resource<List<Flight>> =
        withContext(dispatchers.io) {
            return@withContext try {
                if (flightRepository.shouldFetchFreshData()) {
                    //  fetching fresh data, local data unavailable or stale"
                    val freshResponse = flightRepository.searchRemoteFlights(
                        sort = "popularity",
                        ascending = 0,
                        flyFrom = flyFrom,
                        flyTo = "anywhere",
                        limit = limit,
                    )

                    if (freshResponse.data != null) {
                        val data = freshResponse.data!!
                        val freshFlights = data.flightResponses
                            .map { it.toFlight(currency = data.currency) }
                        flightRepository.updateLocalFlights(newFlights = freshFlights)
                        Resource.Success(data = freshFlights.sortedByDescending { it.popularity })
                    } else {
                        Resource.Error(freshResponse.messageResourceId ?: R.string.error_generic)
                    }

                } else {
                    // invoke: the local data is fresh, no need to fetch new data
                    Resource.Success(data = flightRepository.getLocalFlights().sortedByDescending { it.popularity })
                }
            } catch (e: Exception) {
                Resource.Error(messageResourceId = R.string.error_generic)
            }
        }

}