package dev.yasan.kiwi.presentation.ui.compose.screen.home.modules

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import dev.yasan.kit.compose.foundation.grid
import dev.yasan.kiwi.domain.entity.Flight
import dev.yasan.kiwi.presentation.ui.compose.component.KiwiError

private enum class ImageLoadState {
    SUCCESS, ERROR, LOADING
}

/**
 * Shows the image of a destination using [Flight.destinationId].
 */
@Composable
fun DestinationImage(modifier: Modifier = Modifier, flight: Flight) {
    flight.destinationImageUrl?.let { url ->
        val imageLoadState = remember {
            mutableStateOf(ImageLoadState.LOADING)
        }

        SubcomposeAsyncImage(
            modifier = modifier
                .padding(bottom = grid())
                .clip(RoundedCornerShape(grid()))
                .requiredHeight(grid(24))
                .aspectRatio(ratio = 600f / 330f)
                .placeholder(
                    visible = imageLoadState.value == ImageLoadState.LOADING,
                    highlight = PlaceholderHighlight.fade(highlightColor = Color.LightGray),
                    color = Color.Gray,
                ),
            model = url,
            contentDescription = null,
            contentScale = ContentScale.Fit,
        ) {
            when (painter.state) {
                is AsyncImagePainter.State.Success -> {
                    imageLoadState.value = ImageLoadState.SUCCESS
                    SubcomposeAsyncImageContent()
                }
                is AsyncImagePainter.State.Error -> {
                    imageLoadState.value = ImageLoadState.ERROR
                    KiwiError()
                }
                else -> {
                    imageLoadState.value = ImageLoadState.LOADING
                }
            }
        }
    }
}