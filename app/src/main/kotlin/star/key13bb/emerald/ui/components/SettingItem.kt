package star.key13bb.emerald.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import star.key13bb.emerald.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingItem(
    icon: Int,
    title: String,
    subtitle: String,
    content: @Composable () -> Unit) {
    var dialog by rememberSaveable { mutableStateOf(false) }
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()) {
        Icon(imageVector = ImageVector.vectorResource(icon), contentDescription = null, modifier = Modifier.size(32.dp))
        Column {
            Text(title, fontSize = 20.sp)
            Text(subtitle, fontSize = 12.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
        FilledIconButton(onClick = { dialog = true }) {
            if (title == stringResource(R.string.settings_title_agency)) {
                Icon(ImageVector.vectorResource(R.drawable.outline_warning), contentDescription = null)
            } else {
                Icon(
                    ImageVector.vectorResource(R.drawable.settings_outline_info),
                    contentDescription = null
                )
            }
        }
    }
    if (dialog) {
        BasicAlertDialog(onDismissRequest = { dialog = false }) {
            OutlinedCard(shape = Shapes().extraLarge,
                colors = cardColors(containerColor = colorResource(R.color.settings_card_background)),
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)) {
                Column(modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    content()
                    TextButton(
                        onClick = { dialog = false },
                        modifier = Modifier.padding(2.dp)
                    ) {
                        Text(text = stringResource(R.string.dialog_close))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingItemPreview() {
    SettingItem(R.drawable.navbar_outline_settings, "Settings", "Settings about settings") { }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AlertPreview() {
    BasicAlertDialog(onDismissRequest = {  }) {
        OutlinedCard(shape = Shapes().extraLarge, colors = cardColors(containerColor = colorResource(R.color.settings_card_background))) {
            Column(modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {
                TextButton(
                    onClick = {  },
                    modifier = Modifier.padding(2.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(42.dp),
                            imageVector = ImageVector.vectorResource(R.drawable.settings_outline_firebase),
                            contentDescription = null
                        )
                        Text(text = "Firebase", fontSize = 24.sp)
                        HorizontalDivider()
                        Text(text = stringResource(R.string.dialog_close))
                    }
                }
            }
        }
    }
}