package dev.yasan.kiwi.domain.entity

import com.google.common.truth.Truth
import dev.yasan.kiwi.ValidTestObjectHolder
import org.junit.Before
import org.junit.Test

class CountryTest {

    private lateinit var validCountry: Country

    @Before
    fun setUp() {
        validCountry = ValidTestObjectHolder.country
    }

    @Test
    fun valid_validCountry_passes_validation() {
        Truth.assertThat(validCountry.isValid()).isTrue()
    }

    @Test
    fun blank_country_code_fails_validation() {
        val invalidCountry = validCountry.copy(code = "")
        Truth.assertThat(invalidCountry.isValid()).isFalse()
    }

    @Test
    fun blank_country_name_fails_validation() {
        val invalidCountry = validCountry.copy(name = "")
        Truth.assertThat(invalidCountry.isValid()).isFalse()
    }

}