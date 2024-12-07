package star.kiko13bb.emerald.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import star.kiko13bb.emerald.R


@Composable
fun DefaultScreen(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.outline_developing),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )
        Text(
            text = stringResource(R.string.developing_text),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
        )
        OutlinedButton(
            modifier = modifier.align(Alignment.CenterHorizontally),
            onClick = { throw RuntimeException("Test Crash") },
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.outline_warning),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(text = stringResource(R.string.developing_crash))
            }
        }
    }
}

@Preview
@Composable
fun DefaultScreenPreview() {
    DefaultScreen()
}