package star.kiko13bb.emerald.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.sp
import star.kiko13bb.emerald.R


@Composable
fun DefaultScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = modifier.align(Alignment.Center)) {
            Text(
                modifier = modifier,
                text = stringResource(R.string.under_development),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
            )
            OutlinedButton(
                modifier = modifier.align(Alignment.CenterHorizontally),
                onClick = { throw RuntimeException("Test Crash") },
            ) {
                Row {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.outline_warning),
                        contentDescription = null
                    )
                    Text(text = "  Make it crash!")
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultScreenPreview() {
    DefaultScreen()
}