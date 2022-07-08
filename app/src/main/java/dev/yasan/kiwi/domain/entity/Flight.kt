package dev.yasan.kiwi.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.yasan.kiwi.data.entity.FlightResponse

/**
 * A flattened version of [FlightResponse] for easier use.
 */
@Entity(tableName = "flights")
data class Flight(
    @PrimaryKey val id: String,
    val countryFrom: String,
    val countryTo: String,
    val flyDuration: String,
    val popularity: Int,
    val price: Float,
    val currency: String,
    val seatsAvailable: Int,
    val deepLink: String,
)