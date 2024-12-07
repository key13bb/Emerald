package star.kiko13bb.emerald

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import star.kiko13bb.emerald.composable.MainBottomNavBar
import star.kiko13bb.emerald.composable.MainRailNavBar
import star.kiko13bb.emerald.ui.theme.EmeraldTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmeraldTheme {
                val windowSizeClass = calculateWindowSizeClass(this@MainActivity)
                EmeraldApp(windowSizeClass)
            }
        }
    }
}

@Composable
fun MainScreen (modifier: Modifier = Modifier) {
    EmeraldTheme {
        Box(modifier = Modifier.padding(20.dp).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = modifier) {
                Text(
                    modifier = modifier,
                    text = stringResource(R.string.under_development),
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                )
                OutlinedButton(modifier = modifier,
                    onClick = {throw RuntimeException("Test Crash")},
                    ) {
                    Row {
                        Icon(
                            imageVector = Icons.Outlined.Warning,
                            contentDescription = null
                        )
                        Text(text = "  Make it crash!")
                    }
                }
            }
        }
    }
}

@Composable
fun MainPortrait () {
    EmeraldTheme {
        Surface {
            Scaffold(
                bottomBar = { MainBottomNavBar() }
            ) { padding ->
                MainScreen(Modifier.padding(padding))
            }
        }
    }
}

@Composable
fun MainLandscape() {
    EmeraldTheme {
        Surface {
            Row(modifier = Modifier.padding(WindowInsets.displayCutout.asPaddingValues()).fillMaxSize()) {
                MainRailNavBar()
                MainScreen()
            }
        }
    }
}

@Composable
fun EmeraldApp(windowSize: WindowSizeClass) {
    EmeraldTheme {
        when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                MainPortrait()
            }
            WindowWidthSizeClass.Expanded -> {
                MainLandscape()
            }
        }
    }
}

@Preview
@Composable
fun PortraitPreview() {
    EmeraldTheme {
        MainPortrait()
    }
}

@Preview(widthDp = 640, heightDp = 360)
@Composable
fun LandscapePreview() {
    EmeraldTheme {
        MainLandscape()
    }
}
