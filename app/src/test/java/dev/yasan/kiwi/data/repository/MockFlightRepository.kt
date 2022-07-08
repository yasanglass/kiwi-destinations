package dev.yasan.kiwi.data.repository

import dev.yasan.kit.core.Resource
import dev.yasan.kiwi.ValidTestObjectHolder
import dev.yasan.kiwi.data.entity.FlightSearchResultResponse
import dev.yasan.kiwi.domain.entity.Flight
import dev.yasan.kiwi.domain.repository.FlightRepository

class MockFlightRepository : FlightRepository {

    private val localFlights = arrayListOf<Flight>()

    private var shouldFail = false

    fun setShouldFail(value: Boolean) {
        shouldFail = value
    }

    override suspend fun searchRemoteFlights(
        sort: String,
        ascending: Int,
        flyFrom: String,
        flyTo: String,
        limit: Int
    ): Resource<FlightSearchResultResponse> {
        return if (shouldFail) {
            Resource.Error(messageResourceId = null)
        } else {
            Resource.Success(data = ValidTestObjectHolder.flightSearchResultResponse)
        }
    }

    override suspend fun getLocalFlights(): List<Flight> {
        if (shouldFail) {
            throw Exception("Should fail")
        } else {
            return ValidTestObjectHolder.flightList
        }
    }

    override suspend fun updateLocalFlights(newFlights: List<Flight>) {
        if (shouldFail) {
            throw Exception("Should fail")
        } else {
            localFlights.clear()
            localFlights.addAll(newFlights)
        }
    }

    private var updatedToday = false

    fun setUpdatedToday(value: Boolean) {
        updatedToday = value
    }

    override suspend fun shouldFetchFreshData(): Boolean {
        if (shouldFail) {
            throw Exception("Should fail")
        } else {
            val noLocalFlightsAvailable = getLocalFlights().isEmpty()
            return !updatedToday || noLocalFlightsAvailable
        }
    }

}