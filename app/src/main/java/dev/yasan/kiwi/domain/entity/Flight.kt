package dev.yasan.kiwi.domain.entity

import android.content.Context
import androidx.compose.material.icons.sharp.ConnectingAirports
import androidx.compose.material.icons.sharp.EventSeat
import androidx.compose.material.icons.sharp.Flight
import androidx.compose.material.icons.sharp.LocalOffer
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.yasan.kiwi.R
import dev.yasan.kiwi.data.entity.FlightResponse
import dev.yasan.kiwi.presentation.ui.compose.theme.MyAppIcons

/**
 * A flattened version of [FlightResponse] for easier use.
 */
@Entity(tableName = "flights")
data class Flight(
    @PrimaryKey val id: String,
    val countryFrom: String,
    val countryTo: String,
    val cityFrom: String,
    val cityTo: String,
    val flyDuration: String,
    val popularity: Int,
    val price: Float,
    val currency: String,
    val seatsAvailable: Int,
    val deepLink: String,
    val routeSize: Int,
    val destinationId: String?
) {

    private val priceText get() = "${price.toInt()} $currency"

    val destinationImageUrl get() = "https://images.kiwi.com/photos/600x330/${destinationId}.jpg".takeIf { destinationId != null }

    val soldOut get() = seatsAvailable < 1

    /**
     * Converts the data available into a list of [FlightData]s for easier use.
     */
    fun getData(context: Context) =
        listOf(
            FlightData(
                icon = MyAppIcons.LocalOffer,
                text = priceText
            ),
            FlightData(
                icon = MyAppIcons.ConnectingAirports,
                text = context.resources.getQuantityString(
                    R.plurals.flight_count,
                    routeSize,
                    routeSize
                )
            ),
            FlightData(
                icon = MyAppIcons.Flight,
                text = flyDuration
            ),
            FlightData(
                icon = MyAppIcons.EventSeat,
                text = "$seatsAvailable ${context.getString(R.string.seats_available)}"
            ),
        )

}


