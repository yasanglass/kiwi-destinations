package dev.yasan.kiwi

import dev.yasan.kiwi.data.entity.Availability
import dev.yasan.kiwi.data.entity.Country
import dev.yasan.kiwi.data.entity.FlightResponse
import dev.yasan.kiwi.data.entity.FlightRoute

/**
 * Holds a set of valid objects to ease testing.
 */
object ValidTestObjectHolder {

    val country = Country(
        code = "US",
        name = "United states"
    )

    val availability = Availability(seats = 1)

    val flightRoute = FlightRoute(
        id = "routeId",
        cityFrom = "city1",
        cityTo = "city2",
        mapIdfrom = "idFrom",
        mapIdto = "idTo"
    )

    val flightResponse = FlightResponse(
        id = "id",
        countryFrom = country,
        countryTo = country,
        flyDuration = "2 Hours",
        price = 100f,
        availability = availability,
        deepLink = "deeplink",
        route = listOf(
            flightRoute,
            flightRoute
        ),
        hashtags = listOf(
            "hashtag1", "hashtag2"
        ),
    )


}