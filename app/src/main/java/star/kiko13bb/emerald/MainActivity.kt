package star.kiko13bb.emerald

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import star.kiko13bb.emerald.composable.*
import star.kiko13bb.emerald.ui.theme.*

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmeraldTheme {}
            val windowSizeClass = calculateWindowSizeClass(this)
            EmeraldApp(windowSizeClass)
        }
    }
}

@Composable
fun MainScreen (modifier: Modifier = Modifier) {
    Text(text = stringResource(R.string.under_development))
}

@Composable
fun MainPortrait () {
    EmeraldTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
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
        Surface(color = MaterialTheme.colorScheme.background) {
            Row {
                MainRailNavBar()
                MainScreen()
            }
        }
    }
}

@Composable
fun EmeraldApp(windowSize: WindowSizeClass) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            MainPortrait()
        }
        WindowWidthSizeClass.Expanded -> {
            MainLandscape()
        }
    }
}

@Preview(widthDp = 360, heightDp = 640)
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
