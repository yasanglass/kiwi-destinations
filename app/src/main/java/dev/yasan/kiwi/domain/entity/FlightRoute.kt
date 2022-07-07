package dev.yasan.kiwi.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FlightRoute(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "cityFrom") val cityFrom: String,
    @field:Json(name = "cityTo") val cityTo: String,
    @field:Json(name = "mapIdfrom") val mapIdfrom: String,
    @field:Json(name = "mapIdto") val mapIdto: String,
) {

    fun isValid() = id.isNotBlank()
            && cityFrom.isNotBlank()
            && cityTo.isNotBlank()
            && mapIdfrom.isNotBlank()
            && mapIdto.isNotBlank()

}