package dev.yasan.kiwi

import dev.yasan.kiwi.data.entity.*

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
        popularity = 100,
        cityTo = "city1",
        cityFrom = "city2"
    )

    val flight = flightResponse.toFlight(currency = "EUR")

    val flightList = listOf(
        flight.copy(id = "1"),
        flight.copy(id = "2"),
        flight.copy(id = "3"),
    )

    val flightSearchResultResponse = FlightSearchResultResponse(
        currency = "EUR",
        flightResponses = listOf(
            flightResponse.copy(id = "1"),
            flightResponse.copy(id = "2"),
            flightResponse.copy(id = "3"),
        )
    )

}