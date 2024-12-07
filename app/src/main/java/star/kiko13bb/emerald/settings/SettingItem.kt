package star.kiko13bb.emerald.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import star.kiko13bb.emerald.R
import star.kiko13bb.emerald.editor
import star.kiko13bb.emerald.sharedPreferences

@Composable
fun SettingItem(
    icon: Int? = null,
    title: String,
    subtitle: String,
    info: String? = null,
    key: String,
    function: () -> Unit? = {}
) {
    //Remember switch state from shared preferences
    var checkedState by remember { mutableStateOf(sharedPreferences?.getBoolean(key, false)) }

    //Row with paddings to make it look better
    Row(
        modifier = Modifier.padding(vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        icon?.let { ImageVector.vectorResource(it) }?.let {
            Icon(
                imageVector = it,
                contentDescription = null,
                modifier = Modifier.size(28.dp).align(Alignment.CenterVertically)
            )
        }
        Column(modifier = Modifier.weight(weight = 1f)) {
            Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Medium)
            Text(text = subtitle, fontSize = 14.8.sp)
        }

        icon?.let { ImageVector.vectorResource(it) }?.let {
            //Button to show info
            FilledIconButton(
                content = {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = info,
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.CenterVertically)
                    )
                },
                onClick = {
                    dialog = true
                }
            )
        }

        //Switch to change state, save it to shared preferences and call an optional function
        Switch(checked = checkedState == true,
            onCheckedChange = {
                editor?.putBoolean(key, it)?.apply()
                checkedState = it
                function()
            }
        )
    }
}

@Preview
@Composable
private fun SettingItemPreview() {
    SettingItem(
        R.drawable.navbar_outline_settings,
        "Title",
        "Subtitle",
        "Info",
        "SettingPreview"
    )
}

