package star.kiko13bb.emerald.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import star.kiko13bb.emerald.R
import star.kiko13bb.emerald.ui.theme.EmeraldTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingDialog(
    icon: Int,
    title: String,
    info: String,
    keys: List<String>? = null,
    names: List<Int>? = null,
    infos: List<Int>? = null ) {
    EmeraldTheme {
        BasicAlertDialog(onDismissRequest = { dialog = false },
            content = {
                Card(border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                ) {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState()).padding(16.dp)) {
                        Icon(
                            modifier = Modifier.size(60.dp).align(Alignment.CenterHorizontally),
                            imageVector = ImageVector.vectorResource(id = icon),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = title,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            fontSize = 22.sp
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = info,
                            modifier = Modifier.align(Alignment.CenterHorizontally),
                            fontSize = 16.sp
                        )

                        Spacer(Modifier.height(8.dp))
                        HorizontalDivider()

                        if (keys != null && names != null && infos != null) {
                            names.forEachIndexed {
                                index, _ ->
                                Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                                    SettingItem(
                                        title = stringResource(id = names[index]),
                                        subtitle = stringResource(id = infos[index]),
                                        key = keys[index]
                                    )
                                }
                            }
                        }

                        TextButton(
                            onClick = { dialog = false },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text(text = "Ok")
                        }
                    }
                }
            }
        )
    }
}

@Preview
@Composable
private fun LoremIpsumDialogPreview() {
    SettingDialog(R.drawable.navbar_outline_settings,
        LoremIpsum(2).values.first(),
        LoremIpsum(19).values.first())
}