package star.kiko13bb.emerald.ui.straight

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import star.kiko13bb.emerald.ui.screens.DefaultScreen

@Composable
fun CompactMapsScreen () {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        DefaultScreen(modifier = Modifier.padding(bottom = 90.dp))
    }
}