package dev.yasan.kiwi.domain.repository

import dev.yasan.kit.core.Resource
import dev.yasan.kiwi.data.entity.FlightSearchResultResponse
import dev.yasan.kiwi.data.entity.SearchSortMode
import dev.yasan.kiwi.domain.entity.Flight

/**
 * Handles all data transfers for flight-related data.
 *
 * @see Flight
 * @see FlightSearchResultResponse
 */
interface FlightRepository {

    /**
     * Searches for flights using the sent params in the remotely available data.
     */
    suspend fun searchRemoteFlights(
        sort: SearchSortMode,
        ascending: Int,
        flyFrom: String,
        flyTo: String,
        limit: Int,
    ): Resource<FlightSearchResultResponse>

    /**
     * @return The list of all locally available [Flight]s.
     */
    suspend fun getLocalFlights(): List<Flight>

    /**
     * Replaces the local [Flight]s list with [newFlights].
     */
    suspend fun updateLocalFlights(newFlights: List<Flight>)

    /**
     * @return If fresh data should be fetched due to local data not being available or being stale
     */
    suspend fun shouldFetchFreshData(): Boolean

}