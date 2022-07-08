package dev.yasan.kiwi.data.entity

import com.google.common.truth.Truth
import dev.yasan.kiwi.ValidTestObjectHolder
import org.junit.Before
import org.junit.Test

class FlightResponseRouteTest {

    private lateinit var validFlightRoute: FlightRoute

    @Before
    fun setUp() {
        validFlightRoute = ValidTestObjectHolder.flightRoute
    }

    @Test
    fun valid_flight_route_passes_validation() {
        Truth.assertThat(validFlightRoute.isValid()).isTrue()
    }

    @Test
    fun blank_flight_route_id_fails_validation() {
        val invalidFlightRoute = validFlightRoute.copy(id = "")
        Truth.assertThat(invalidFlightRoute.isValid()).isFalse()
    }

    @Test
    fun blank_flight_route_cityfrom_fails_validation() {
        val invalidFlightRoute = validFlightRoute.copy(cityFrom = "")
        Truth.assertThat(invalidFlightRoute.isValid()).isFalse()
    }

    @Test
    fun blank_flight_route_cityto_fails_validation() {
        val invalidFlightRoute = validFlightRoute.copy(cityTo = "")
        Truth.assertThat(invalidFlightRoute.isValid()).isFalse()
    }

    @Test
    fun blank_flight_route_mapIdfrom_fails_validation() {
        val invalidFlightRoute = validFlightRoute.copy(mapIdfrom = "")
        Truth.assertThat(invalidFlightRoute.isValid()).isFalse()
    }

    @Test
    fun blank_flight_route_mapIdto_fails_validation() {
        val invalidFlightRoute = validFlightRoute.copy(mapIdto = "")
        Truth.assertThat(invalidFlightRoute.isValid()).isFalse()
    }

}