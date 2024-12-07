package star.kiko13bb.emerald

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import star.kiko13bb.emerald.ui.theme.EmeraldTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmeraldTheme {}
            val windowSizeClass = calculateWindowSizeClass(this)
            EmeraldApp(windowSizeClass)
        }
    }
}

@Composable
fun MainScreen (modifier: Modifier = Modifier) {
    //TODO: Develop Main Screens//
}

@Composable
fun MainBottomNavBat (modifier: Modifier = Modifier) {
    NavigationBar (
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        NavigationBarItem (
            icon = {Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.logo_metro_malaga),
                contentDescription = null
            )},
            label = {Text(text = "Metro")},
            selected = true,
            onClick = {}
        )
        NavigationBarItem (
            icon = {Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.logo_cercanias_renfe),
                contentDescription = null
            )},
            label = {Text(text = "Cercanías")},
            selected = false,
            onClick = {}
        )
    }
}

@Composable
fun MainPortrait () {
    EmeraldTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Scaffold(
                bottomBar = { MainBottomNavBat() }
            ) { padding ->
                MainScreen(Modifier.padding(padding))
            }
        }
    }
}

@Composable
fun MainRailNavBar(modifier: Modifier = Modifier) {
    NavigationRail(
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
    ) {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.logo_metro_malaga),
                        contentDescription = null
                    )
                },
                label = { Text(text = "Metro") },
                selected = true,
                onClick = {}
            )
            NavigationRailItem(
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.logo_cercanias_renfe),
                        contentDescription = null
                    )
                },
                label = { Text(text = "Cercanías") },
                selected = false,
                onClick = {}
            )
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