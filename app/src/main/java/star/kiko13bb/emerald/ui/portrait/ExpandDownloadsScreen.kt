package star.kiko13bb.emerald.ui.portrait

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import star.kiko13bb.emerald.ui.components.DownloadFAB
import star.kiko13bb.emerald.ui.theme.EmeraldTheme


@Composable
fun ExpandDownloadsScreen() {
    EmeraldTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            DownloadFAB()
        }
    }
}