package dev.yasan.kiwi.presentation.ui.compose.screen.home.states

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import dev.yasan.kit.compose.foundation.grid
import dev.yasan.kit.compose.parts.branding.YasanBrandingFooter
import dev.yasan.kiwi.R
import dev.yasan.kiwi.domain.entity.Flight
import dev.yasan.kiwi.presentation.ui.compose.screen.home.modules.FlightItem

fun LazyListScope.homeScreenSuccess(flights: List<Flight>) {

    item {
        val topText = if (flights.isEmpty()) {
            stringResource(id = R.string.no_offers_available)
        } else {
            stringResource(id = R.string.todays_offers)
        }

        Text(
            modifier = Modifier
                .padding(top = grid(2))
                .padding(bottom = grid(0.5f)),
            text = topText,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
    }

    itemsIndexed(items = flights) { index, flight ->
        FlightItem(flight = flight, mostPopular = index == 0)
    }

    item {
        YasanBrandingFooter()
    }

}