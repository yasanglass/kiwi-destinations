package dev.yasan.kiwi.domain.entity

import com.google.common.truth.Truth
import dev.yasan.kiwi.ValidTestObjectHolder
import org.junit.Before
import org.junit.Test

class FlightTest {

    private lateinit var validFlight: Flight

    @Before
    fun setUp() {
        validFlight = ValidTestObjectHolder.flight
    }

    @Test
    fun valid_flight_passes_validation() {
        Truth.assertThat(validFlight.isValid()).isTrue()
    }

    @Test
    fun blank_flight_id_fails_validation() {
        val invalidFlight = validFlight.copy(id = "")
        Truth.assertThat(invalidFlight.isValid()).isFalse()
    }

    @Test
    fun invalid_flight_countryFrom_fails_validation() {
        val invalidFlight = validFlight.copy(countryFrom = Country("", ""))
        Truth.assertThat(invalidFlight.isValid()).isFalse()
    }

    @Test
    fun invalid_flight_countryTo_fails_validation() {
        val invalidFlight = validFlight.copy(countryTo = Country("", ""))
        Truth.assertThat(invalidFlight.isValid()).isFalse()
    }

    @Test
    fun blank_flight_duration_fails_validation() {
        val invalidFlight = validFlight.copy(flyDuration = "")
        Truth.assertThat(invalidFlight.isValid()).isFalse()
    }

    @Test
    fun negative_flight_price_fails_validation() {
        val invalidFlight = validFlight.copy(price = -1f)
        Truth.assertThat(invalidFlight.isValid()).isFalse()
    }

    @Test
    fun invalid_flight_availability_fails_validation() {
        val invalidFlight = validFlight.copy(availability = Availability(-1))
        Truth.assertThat(invalidFlight.isValid()).isFalse()
    }

    @Test
    fun blank_flight_deeplink_fails_validation() {
        val invalidFlight = validFlight.copy(deepLink = "")
        Truth.assertThat(invalidFlight.isValid()).isFalse()
    }

    @Test
    fun empty_flight_route_fails_validation() {
        val invalidFlight = validFlight.copy(route = emptyList())
        Truth.assertThat(invalidFlight.isValid()).isFalse()
    }

    @Test
    fun invalid_flight_route_fails_validation() {
        val invalidFlight = validFlight.copy(
            route = listOf(
                ValidTestObjectHolder.flightRoute,
                ValidTestObjectHolder.flightRoute.copy(id = "")
            )
        )
        Truth.assertThat(invalidFlight.isValid()).isFalse()
    }

}