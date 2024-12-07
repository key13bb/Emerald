package star.kiko13bb.emerald.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import star.kiko13bb.emerald.editor
import star.kiko13bb.emerald.sharedPreferences

@Composable
fun SettingSwitch(
    title: String,
    info: String,
    key: String,
    function: () -> Unit
) {
    var checked by rememberSaveable { mutableStateOf(false) }
    checked = sharedPreferences?.getBoolean(key, false)!!
    Row(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(weight = 1f)) {
            Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Medium)
            Text(text = info, fontSize = 14.sp)
        }
        Switch(checked = checked, onCheckedChange = {
            function()
            editor?.putBoolean(key, !checked)?.apply()
            checked = sharedPreferences?.getBoolean(key, false)!!
        }
        )
    }
}