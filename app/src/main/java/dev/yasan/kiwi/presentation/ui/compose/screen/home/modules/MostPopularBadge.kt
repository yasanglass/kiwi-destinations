package dev.yasan.kiwi.presentation.ui.compose.screen.home.modules

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yasan.kit.compose.foundation.grid
import dev.yasan.kiwi.R
import dev.yasan.kiwi.presentation.ui.compose.theme.MyAppIcons
import dev.yasan.kiwi.domain.entity.Flight

/**
 * Simple composable that shows a [Flight] is the most popular offer.
 */
@Preview
@Composable
fun MostPopularBadge() {
    Row(modifier = Modifier.padding(top = grid(1.5f))) {
        Icon(
            modifier = Modifier.requiredSize(16.dp),
            imageVector = MyAppIcons.Favorite,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.tertiary
        )
        Spacer(modifier = Modifier.requiredWidth(grid(0.5f)))
        Text(
            text = stringResource(id = R.string.most_popular_offer).uppercase(),
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}