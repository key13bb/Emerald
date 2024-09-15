package star.kiko13bb.emerald.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Shapes
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import star.kiko13bb.emerald.UserSettings
import star.kiko13bb.emerald.context
import star.kiko13bb.emerald.proto.userPreferencesDataStore

@Composable
fun SettingSwitch(
    title: String,
    info: String,
    key: Int,
    function: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    // Use `LaunchedEffect` to fetch the initial value and observe changes
    var checked by remember { mutableStateOf(settingChooser(key)) }
    LaunchedEffect(key) {
        context!!.userPreferencesDataStore.data.collect { userPreferences ->
            checked = when (key) {
                2 -> userPreferences.firebaseAnalytics
                3 -> userPreferences.firebaseCrashlytics
                4 -> userPreferences.onMetered
                else -> throw Exception("Invalid key. Take a look at scheme.proto")
            }
        }
    }

    val click = {
        function()
        coroutineScope.launch {
            settingWriter(key, !checked) // Update DataStore
        }
    }

    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(shape = Shapes().extraLarge, onClick = { click() }) {
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
            Text(text = title, fontSize = 20.sp)
            Switch(checked = checked, onCheckedChange = { click() })
            }
        }
        Text(text = info, fontSize = 16.sp)
    }
}

fun settingChooser(key: Int): Boolean {
    var setting: Boolean = when(key) {
        2 -> UserSettings.getDefaultInstance().firebaseAnalytics
        3 -> UserSettings.getDefaultInstance().firebaseCrashlytics
        4 -> UserSettings.getDefaultInstance().onMetered
        else -> throw(Exception("Invalid key. Take a look at scheme.proto"))
    }
    return setting
}

suspend fun settingWriter(key: Int, value: Boolean): Boolean {
    when(key) {
        2 -> context!!.userPreferencesDataStore.updateData { preferences -> preferences.toBuilder().setFirebaseAnalytics(value).build() }
        3 -> context!!.userPreferencesDataStore.updateData { preferences -> preferences.toBuilder().setFirebaseCrashlytics(value).build() }
        4 -> context!!.userPreferencesDataStore.updateData { preferences -> preferences.toBuilder().setOnMetered(value).build() }
        else -> throw(Exception("Invalid key. Take a look at scheme.proto"))
    }
    return !value
}

@Preview
@Composable
fun SettingSwitchPreview() {
    SettingSwitch("Firebase", "Info", 1) {}
}