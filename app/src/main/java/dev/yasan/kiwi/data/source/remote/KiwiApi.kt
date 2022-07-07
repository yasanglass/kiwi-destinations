package dev.yasan.kiwi.data.source.remote

import dev.yasan.kiwi.data.source.entity.SearchResultResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KiwiApi {

    @Headers("Accept: application/json")
    @GET("/flights")
    suspend fun searchFlights(
        @Query("sort") sort: String = "popularity",
        @Query("asc") ascending: Int = 0,
        @Query("fly_from") flyFrom: String = "prague_cz",
        @Query("fly_to") flyTo: String = "anywhere",
        @Query("limit") limit: Int = 5,
        @Query("partner") partnerId: String = "skypicker-android",
    ): Response<SearchResultResponse>

}