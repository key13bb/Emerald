package star.kiko13bb.emerald.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.perf.ktx.performance
import star.kiko13bb.emerald.R
import star.kiko13bb.emerald.sharedPreferences
import star.kiko13bb.emerald.ui.screens.DefaultScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonDialog(
    title: String,
    icon: Int,
    content: @Composable () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            modifier = Modifier.size(42.dp),
            imageVector = ImageVector.vectorResource(icon),
            contentDescription = null
        )
        Text(text = title, fontSize = 24.sp)
        HorizontalDivider()
        content()
    }
}

@Composable
fun FirebaseDialog() {
    SettingSwitch(stringResource(R.string.settings_alert_firebase_analytics),
        stringResource(R.string.settings_alert_info_firebase_analytics),
        "Analytics") {
        val state = sharedPreferences?.getBoolean("Analytics", false)
        Firebase.analytics.setAnalyticsCollectionEnabled(!state!!)
    }
    SettingSwitch(stringResource(R.string.settings_alert_firebase_crashlytics),
        stringResource(R.string.settings_alert_info_firebase_crashlytics),
        "Crashlytics") {
        val state = sharedPreferences?.getBoolean("Crashlytics", false)
        Firebase.crashlytics.setCrashlyticsCollectionEnabled(!state!!)
    }
    SettingSwitch(stringResource(R.string.settings_alert_firebase_performance),
        stringResource(R.string.settings_alert_info_firebase_performance),
        "Performance") {
        val state = sharedPreferences?.getBoolean("Performance", false)
        state?.let { Firebase.performance.isPerformanceCollectionEnabled = !it }
    }
}

@Composable
fun AgencyDialog() {
    DefaultScreen()
}

@Composable
fun DownloadPrimaryDialog() {
    DefaultScreen()
}

@Preview
@Composable
fun CommonDialogPreview() {
    CommonDialog("Firebase", R.drawable.settings_outline_firebase) {}
}