package dev.yasan.kiwi.domain.entity

import com.google.common.truth.Truth
import dev.yasan.kiwi.ValidTestObjectHolder
import org.junit.Before
import org.junit.Test

class AvailabilityTest {

    private lateinit var validAvailability: Availability

    @Before
    fun setUp() {
        validAvailability = ValidTestObjectHolder.availability
    }

    @Test
    fun valid_availability_passes_validation() {
        Truth.assertThat(validAvailability.isValid()).isTrue()
    }

    @Test
    fun negative_availability_seat_count_fails_validation() {
        val invalidAvailability = validAvailability.copy(seats = -1)
        Truth.assertThat(invalidAvailability.isValid()).isFalse()
    }

}