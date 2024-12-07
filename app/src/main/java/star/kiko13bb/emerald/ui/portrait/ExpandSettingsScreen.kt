package star.kiko13bb.emerald.ui.portrait

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import star.kiko13bb.emerald.ui.components.CommonDialog
import star.kiko13bb.emerald.ui.components.SettingItem
import star.kiko13bb.emerald.ui.components.dialogs
import star.kiko13bb.emerald.ui.components.icons
import star.kiko13bb.emerald.ui.components.subtitles
import star.kiko13bb.emerald.ui.components.titles

@Composable
fun ExpandSettingsScreen() {
    Column {
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

@Preview(widthDp = 640, heightDp = 360)
@Composable
fun SettingsScreenPreview() {
    ExpandSettingsScreen()
}