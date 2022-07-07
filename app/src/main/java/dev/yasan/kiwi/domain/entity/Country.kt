package dev.yasan.kiwi.domain.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Country(
    @field:Json(name = "code") val code: String,
    @field:Json(name = "name") val name: String
) {

    fun isValid(): Boolean = code.isNotBlank() && name.isNotBlank()

}
