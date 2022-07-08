package dev.yasan.kiwi.presentation.ui.compose.screen.home.states

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import dev.yasan.kit.compose.parts.branding.YasanBrandingFooter
import dev.yasan.kiwi.domain.entity.Flight

fun LazyListScope.homeScreenSuccess(flights: List<Flight>) {

    items(items = flights) {
        Text(text = it.countryFrom + " -> " + it.countryTo)
        Divider()
    }

    item {
        YasanBrandingFooter()
    }

}