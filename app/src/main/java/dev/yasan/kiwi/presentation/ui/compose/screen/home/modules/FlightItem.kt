package dev.yasan.kiwi.presentation.ui.compose.screen.home.modules

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.sharp.TrendingFlat
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yasan.kit.compose.foundation.grid
import dev.yasan.kit.compose.type.rubikFamily
import dev.yasan.kit.core.WebHelper
import dev.yasan.kiwi.R
import dev.yasan.kiwi.domain.entity.Flight
import dev.yasan.kiwi.domain.entity.FlightData
import dev.yasan.kiwi.presentation.ui.compose.theme.MyAppIcons

/**
 * Shows a single [Flight] in the UI.
 */
@Composable
fun FlightItem(
    modifier: Modifier = Modifier,
    flight: Flight,
    mostPopular: Boolean = false
) {

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = grid(1.5f))
            .padding(horizontal = grid(2))
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(grid(1.5f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TravelPathBadge(flight = flight)

        if (mostPopular) {
            MostPopularBadge()
        }

        DestinationImage(
            modifier = Modifier.padding(top = grid(1.5f)),
            flight = flight
        )

        FlightDataList(data = flight.getData(context = context))

        BookButton(modifier = modifier.align(Alignment.End), flight = flight)

    }

}

@Composable
private fun BookButton(modifier: Modifier = Modifier, flight: Flight) {
    val context = LocalContext.current
    val soldOut = flight.soldOut

    val text = if (soldOut) stringResource(id = R.string.sold_out)
    else stringResource(id = R.string.book)

    Button(
        modifier = modifier,
        onClick = { WebHelper.openWebView(context = context, url = flight.deepLink) },
        enabled = !soldOut
    ) {
        Text(
            text = text.uppercase(),
            fontFamily = rubikFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Black
        )
    }
}

@Composable
private fun TravelPathBadge(flight: Flight) {
    Row(
        modifier = Modifier
            .border(
                color = MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.small,
                width = 1.dp
            )
            .padding(horizontal = grid(1.5f))
            .padding(vertical = grid()),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = flight.cityFrom,
            color = MaterialTheme.colorScheme.secondary
        )

        Icon(
            modifier = Modifier.padding(horizontal = grid()),
            imageVector = MyAppIcons.TrendingFlat,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.secondary
        )

        Text(
            text = flight.cityTo,
            color = MaterialTheme.colorScheme.secondary
        )

    }
}

@Composable
private fun FlightDataList(
    modifier: Modifier = Modifier,
    data: List<FlightData>
) {
    Column(
        modifier = modifier.padding(top = grid(1.5f)),
        horizontalAlignment = Alignment.Start
    ) {
        data.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = grid(0.5f))
            ) {
                Icon(
                    modifier = Modifier.requiredSize(16.dp),
                    imageVector = it.icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary
                )
                Spacer(modifier = Modifier.requiredWidth(grid(0.5f)))
                Text(text = it.text)
            }
        }
    }
}
