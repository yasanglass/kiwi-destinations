package dev.yasan.kiwi.domain.entity

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Holds the data for an [ImageVector] & a [String]. Meant to be used to handle [Flight]s data easily.
 */
data class FlightData(val icon: ImageVector, val text: String)