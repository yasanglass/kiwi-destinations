package dev.yasan.kiwi.data.source.remote

import dev.yasan.kiwi.data.entity.SearchResultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KiwiApi {

    @Headers("Accept: application/json")
    @GET("/flights")
    suspend fun searchFlights(
        @Query("sort") sort: String,
        @Query("asc") ascending: Int,
        @Query("fly_from") flyFrom: String,
        @Query("fly_to") flyTo: String,
        @Query("limit") limit: Int,
        @Query("adults") adults: Int = 1,
        @Query("typeFlight") type: String = "oneway",
        @Query("partner") partnerId: String = "skypicker-android",
    ): Response<SearchResultResponse>

}