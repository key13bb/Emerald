package star.kiko13bb.emerald.ui.straight

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import star.kiko13bb.emerald.ui.components.CommonDialog
import star.kiko13bb.emerald.ui.components.SettingItem
import star.kiko13bb.emerald.ui.components.dialogs
import star.kiko13bb.emerald.ui.components.icons
import star.kiko13bb.emerald.ui.components.subtitles
import star.kiko13bb.emerald.ui.components.titles

/*
val settingsFlow: Flow<Boolean> = MainActivity().settingsDataStore.data.map { settings ->
    settings.options
}
*/

@Composable
fun CompactSettingsScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
        titles.forEachIndexed { index, _ ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                SettingItem(
                    icons[index],
                    stringResource(titles[index]),
                    stringResource(subtitles[index])) {
                    CommonDialog(stringResource(titles[index]), icons[index]) { dialogs[index].invoke() }
                }
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun SettingsScreenPreview() {
    CompactSettingsScreen()
}