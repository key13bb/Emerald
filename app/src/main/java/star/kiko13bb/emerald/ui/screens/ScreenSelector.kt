package star.kiko13bb.emerald.ui.screens

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import star.kiko13bb.emerald.ui.portrait.*
import star.kiko13bb.emerald.ui.straight.*
import star.kiko13bb.emerald.windowSizeClass

@Composable
fun TransportChooser() {
    if (windowSizeClass!!.widthSizeClass == WindowWidthSizeClass.Compact) {
        CompactTransportScreen()
    } else {
        ExpandTransportScreen()
    }
}

@Composable
fun MapsChooser() {
    if (windowSizeClass!!.widthSizeClass == WindowWidthSizeClass.Compact) {
        CompactMapsScreen()
    } else {
        ExpandMapsScreen()
    }
}

@Composable
fun DownloadsChooser() {
    if (windowSizeClass!!.widthSizeClass == WindowWidthSizeClass.Compact) {
        CompactDownloadsScreen()
    } else {
        ExpandDownloadsScreen()
    }
}

@Composable
fun SettingsChooser() {
    if (windowSizeClass!!.widthSizeClass == WindowWidthSizeClass.Compact) {
        CompactSettingsScreen()
    } else {
        ExpandSettingsScreen()
    }
}

@Preview
@Composable
fun DownloadsScreenPreview() {
    CompactDownloadsScreen()
}