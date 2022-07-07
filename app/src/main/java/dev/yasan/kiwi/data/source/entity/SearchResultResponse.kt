package dev.yasan.kiwi.data.source.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.yasan.kiwi.domain.entity.Flight

@JsonClass(generateAdapter = true)
data class SearchResultResponse(
    @field:Json(name = "currency") val currency: String,
    @field:Json(name = "data") val data: List<Flight>
)

