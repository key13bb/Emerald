package star.kiko13bb.emerald.ui.portrait

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import star.kiko13bb.emerald.ui.components.ChipSlider

@Composable
fun ExpandTransportScreen() {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        ChipSlider()
    }
}