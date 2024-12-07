package star.kiko13bb.emerald.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import star.kiko13bb.emerald.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadFAB() {
    var dialog by rememberSaveable { mutableStateOf(false) }
    ExtendedFloatingActionButton(
        onClick = { dialog = true },
        icon = {
            Icon(imageVector = ImageVector.vectorResource(R.drawable.navbar_outline_download),
                contentDescription = stringResource(R.string.navbar_download))
        },
        text = { Text(text = stringResource(R.string.navbar_download)) }
    )

    if (dialog) {
        BasicAlertDialog(onDismissRequest = { dialog = false }) {
            OutlinedCard(border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)) {
                Column(modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    CommonDialog(stringResource(R.string.navbar_download), R.drawable.navbar_outline_download) {
                        DownloadPrimaryDialog()
                    }
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