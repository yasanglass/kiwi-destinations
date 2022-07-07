package dev.yasan.kiwi.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Flight(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "countryFrom") val countryFrom: Country,
    @field:Json(name = "countryTo") val countryTo: Country,
    @field:Json(name = "fly_duration") val flyDuration: String,
    @field:Json(name = "price") val price: Float,
    @field:Json(name = "availability") val availability: Availability,
    @field:Json(name = "deep_link") val deepLink: String,
    @field:Json(name = "route") val route: List<FlightRoute>,
    @field:Json(name = "hashtags") val hashtags: List<String>,
) {

    fun isValid(): Boolean = id.isNotBlank()
            && countryFrom.isValid()
            && countryTo.isValid()
            && flyDuration.isNotBlank()
            && price > 0
            && availability.isValid()
            && deepLink.isNotBlank()
            && route.isNotEmpty()
            && route.all { it.isValid() }

}