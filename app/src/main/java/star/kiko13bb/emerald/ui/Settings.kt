package star.kiko13bb.emerald.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.perf.ktx.performance
import star.kiko13bb.emerald.R
import star.kiko13bb.emerald.editor
import star.kiko13bb.emerald.sharedPreferences

val icons = listOf(R.drawable.outline_logo_firebase)
val titles = listOf(R.string.settings_title_firebase)
val subtitles = listOf(R.string.settings_subtitle_firebase)
val infos = listOf(R.string.settings_info_firebase)

fun switchStateFirebase() {
    val state = sharedPreferences?.getBoolean("Firebase", false)
    if (state != null) {
        Firebase.performance.isPerformanceCollectionEnabled = state
        Firebase.analytics.setAnalyticsCollectionEnabled(state)
        Firebase.crashlytics.setCrashlyticsCollectionEnabled(state)
    }
}

@Composable
fun SettingsScreen() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        titles.forEachIndexed { index, _ ->
            SettingItem(
                icons[index],
                stringResource(titles[index]),
                stringResource(subtitles[index]),
                stringResource(infos[index]),
                "Firebase"
            ) { switchStateFirebase() }
        }
    }
}

@Composable
fun SettingItem(
    icon: Int,
    title: String,
    subtitle: String,
    info: String,
    key: String,
    function: () -> Unit? = {}
) {
    var checkedState by remember { mutableStateOf(sharedPreferences?.getBoolean(key, false)) }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Medium)
            Text(text = subtitle, fontSize = 14.8.sp)
        }
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = info,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.CenterVertically)
        )
        checkedState?.let { it ->
            Switch(checked = it, onCheckedChange = {
                editor?.putBoolean(key, it)?.apply()
                checkedState = it
                function()
            })
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}