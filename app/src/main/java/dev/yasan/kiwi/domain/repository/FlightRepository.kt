package dev.yasan.kiwi.domain.repository

import dev.yasan.kit.core.Resource
import dev.yasan.kiwi.data.entity.SearchResultResponse

interface FlightRepository {

    suspend fun searchFlights(
        sort: String,
        ascending: Int,
        flyFrom: String,
        flyTo: String,
        limit: Int,
    ): Resource<SearchResultResponse>

}