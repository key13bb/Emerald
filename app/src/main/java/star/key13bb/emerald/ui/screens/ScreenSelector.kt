package star.key13bb.emerald.ui.screens

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import star.key13bb.emerald.ui.portrait.*
import star.key13bb.emerald.ui.straight.*

@Composable
fun TransportChooser(windowSizeClass: WindowSizeClass) {
    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
        CompactTransportScreen()
    } else {
        ExpandTransportScreen()
    }
}

@Composable
fun MapsChooser(windowSizeClass: WindowSizeClass) {
    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
        CompactMapsScreen()
    } else {
        ExpandMapsScreen()
    }
}

@Composable
fun DownloadsChooser(windowSizeClass: WindowSizeClass) {
    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
        CompactDownloadsScreen()
    } else {
        ExpandDownloadsScreen()
    }
}

@Composable
fun SettingsChooser(windowSizeClass: WindowSizeClass) {
    if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
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