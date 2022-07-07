package dev.yasan.kiwi.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Availability(
    @field:Json(name = "seats") val seats: Int,
) {

    fun isValid() = seats >= 0

}
