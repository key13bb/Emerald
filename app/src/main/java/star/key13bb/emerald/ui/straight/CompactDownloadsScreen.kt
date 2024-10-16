package star.key13bb.emerald.ui.straight

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import star.key13bb.emerald.ui.components.DownloadFAB
import star.key13bb.emerald.ui.theme.EmeraldTheme


@Composable
fun CompactDownloadsScreen() {
    EmeraldTheme {
        Box(
            modifier = Modifier.padding(bottom = 80.dp).safeDrawingPadding().fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            DownloadFAB()
        }
    }
}