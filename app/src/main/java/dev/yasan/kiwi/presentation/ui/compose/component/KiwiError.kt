package dev.yasan.kiwi.presentation.ui.compose.component

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.sharp.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.yasan.kit.compose.foundation.grid
import dev.yasan.kit.compose.type.rubikFamily
import dev.yasan.kiwi.R
import dev.yasan.kiwi.presentation.ui.compose.theme.MyAppIcons

/**
 * Kiwi-themed error composable. All errors should use this composable.
 * If you want it to show an retry button you can pass [onRetry], otherwise the button will not be shown.
 */
@Preview(uiMode = UI_MODE_NIGHT_NO, group = "Without Button")
@Preview(uiMode = UI_MODE_NIGHT_YES, group = "Without Button")
@Composable
fun KiwiError(
    modifier: Modifier = Modifier,
    message: String = stringResource(id = R.string.error_generic),
    onRetry: (() -> Unit)? = null
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.padding(grid(2)),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = MyAppIcons.Warning,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )

            Spacer(modifier = Modifier.requiredWidth(grid()))

            Text(
                text = message,
                color = MaterialTheme.colorScheme.error,
                fontFamily = rubikFamily,
            )

        }

        onRetry?.let {
            Button(onClick = it) {
                Text(
                    text = stringResource(id = R.string.try_again),
                    fontFamily = rubikFamily,
                )
            }
            Spacer(modifier = Modifier.padding(grid()))
        }

        Divider()

    }

}

@Preview(uiMode = UI_MODE_NIGHT_NO, group = "With Button")
@Preview(uiMode = UI_MODE_NIGHT_YES, group = "With Button")
@Composable
private fun KiwiErrorWithButtonPreview() {
    KiwiError {}
}