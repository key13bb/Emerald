package star.kiko13bb.emerald.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.perf.ktx.performance
import star.kiko13bb.emerald.R
import star.kiko13bb.emerald.editor
import star.kiko13bb.emerald.sharedPreferences

// Define firebase's sub settings
val firebaseSubKeys = listOf("Analytics", "Crashlytics", "Performance")
val firebaseSubNames = listOf(R.string.settings_alert_firebase_analytics, R.string.settings_alert_firebase_crashlytics, R.string.settings_alert_firebase_performance)
val firebaseSubInfos = listOf(R.string.settings_alert_info_firebase_analytics, R.string.settings_alert_info_firebase_crashlytics, R.string.settings_alert_info_firebase_performance)

// Define list of settings available in app
val icons = listOf(R.drawable.outline_logo_firebase)
val titles = listOf(R.string.settings_title_firebase)
val subtitles = listOf(R.string.settings_subtitle_firebase)
val infos = listOf(R.string.settings_info_firebase)
val functions = listOf(switchStateFirebase())
val keys = listOf("Firebase")
val subkeys = listOf(firebaseSubKeys)
val subnames = listOf(firebaseSubNames)
val subinfos = listOf(firebaseSubInfos)
var dialog by mutableStateOf(false)

fun switchStateFirebase() {
    val state = sharedPreferences?.getBoolean("Firebase", false)
    if (state != null) {
        firebaseSubKeys.forEachIndexed {
            index, _ ->
            editor?.putBoolean(firebaseSubKeys[index], state)
        }
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
        verticalAlignment = Alignment.Top
    ) {
        titles.forEachIndexed { index, _ ->
            SettingItem(
                icons[index],
                stringResource(titles[index]),
                stringResource(subtitles[index]),
                stringResource(infos[index]),
                keys[index],
            ) { functions[index] }
            if (dialog) {
                SettingDialog(
                    icons[index],
                    stringResource(titles[index]),
                    stringResource(infos[index]),
                    subkeys[index],
                    subnames[index],
                    subinfos[index]
                )
            }
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}

@Preview
@Composable
private fun FirebaseSettingDialogPreview() {
    SettingDialog(
        R.drawable.outline_logo_firebase,
        "Firebase",
        "Habilitar recolección de datos",
        subkeys[0],
        subnames[0],
        subinfos[0]
    )
}