package dev.yasan.kiwi.data.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.yasan.kiwi.domain.entity.Flight

@JsonClass(generateAdapter = true)
data class FlightResponse(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "countryFrom") val countryFrom: Country,
    @field:Json(name = "countryTo") val countryTo: Country,
    @field:Json(name = "cityFrom") val cityFrom: String,
    @field:Json(name = "cityTo") val cityTo: String,
    @field:Json(name = "fly_duration") val flyDuration: String,
    @field:Json(name = "popularity") val popularity: Int,
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

    fun toFlight(currency: String) = Flight(
        id = id,
        countryFrom = countryFrom.name,
        countryTo = countryTo.name,
        cityFrom = cityFrom,
        cityTo = cityTo,
        flyDuration = flyDuration,
        popularity = popularity,
        price = price,
        currency = currency,
        seatsAvailable = availability.seats.takeIf { it != null } ?: 0,
        deepLink = deepLink,
        routeSize = route.size,
        destinationId = route.lastOrNull()?.mapIdto
    )

}