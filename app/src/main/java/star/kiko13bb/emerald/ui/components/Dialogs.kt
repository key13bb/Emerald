package star.kiko13bb.emerald.ui.components

import android.content.Context
import android.net.ConnectivityManager
import android.os.Environment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import star.kiko13bb.emerald.R
import star.kiko13bb.emerald.UserSettings
import star.kiko13bb.emerald.context
import star.kiko13bb.emerald.gtfs.GTFSManager

val manager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
var isMetered = manager.isActiveNetworkMetered

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
        2) {
        val state = UserSettings.getDefaultInstance().firebaseAnalytics
        Firebase.analytics.setAnalyticsCollectionEnabled(!state)
    }
    HorizontalDivider()
    SettingSwitch(stringResource(R.string.settings_alert_firebase_crashlytics),
        stringResource(R.string.settings_alert_info_firebase_crashlytics),
        3) {
        val state = UserSettings.getDefaultInstance().firebaseCrashlytics
        Firebase.crashlytics.isCrashlyticsCollectionEnabled = !state
    }
}

@Composable
fun AgencyDialog() {
    val manager = GTFSManager("https://api.ctan.es/v1/datos/UNIFICADO/gtfs.zip")
    val agencies = manager.getAgenciesList()
    CommonDialog(manager.getIt(), R.drawable.settings_outline_agency) {
        Column {
            agencies.forEach {
                Text(text = it)
                HorizontalDivider()
            }
        }
    }
}

@Composable
fun MeteredDialog() {
    SettingSwitch(stringResource(R.string.settings_title_metered),
        stringResource(R.string.settings_alert_info_metered),
        4) {}
    var expanded by rememberSaveable { mutableStateOf(isMetered) }
    if (expanded) {
        HorizontalDivider()
        Spacer(modifier = Modifier.size(4.dp))
        Row {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.settings_outline_info),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text("Esta red es de uso medido")
        }
    }
}

@Composable
fun DownloadPrimaryDialog() {
    Column {
        Text(Environment.DIRECTORY_DOWNLOADS)
        Text(context!!.filesDir.path)
        Text(context!!.cacheDir.path)
    }
}

@Preview
@Composable
fun CommonDialogPreview() {
    CommonDialog("Firebase", R.drawable.settings_outline_firebase) {}
}

@Preview
@Composable
fun DownloadPrimaryDialogPreview() {
    DownloadPrimaryDialog()
}