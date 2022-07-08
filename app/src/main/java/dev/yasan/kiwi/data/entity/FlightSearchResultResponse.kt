package dev.yasan.kiwi.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlightSearchResultResponse(
    @field:Json(name = "currency") val currency: String,
    @field:Json(name = "data") val flightResponses: List<FlightResponse>
)

