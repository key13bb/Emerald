package star.kiko13bb.emerald

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import star.kiko13bb.emerald.ui.CardsScreen
import star.kiko13bb.emerald.ui.DownloadsScreen
import star.kiko13bb.emerald.ui.MapsScreen
import star.kiko13bb.emerald.ui.SettingsScreen
import star.kiko13bb.emerald.ui.TransportScreen
import star.kiko13bb.emerald.ui.theme.EmeraldTheme

var sharedPreferences: SharedPreferences? = null
var editor: SharedPreferences.Editor? = null

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @RequiresApi(Build.VERSION_CODES.Q)
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EmeraldTheme {
                sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)
                editor = sharedPreferences?.edit()
                val windowSizeClass = calculateWindowSizeClass(this@MainActivity)
                val items = listOf(R.string.navbar_transport, R.string.navbar_map, R.string.navbar_download, R.string.navbar_card, R.string.navbar_settings)
                val ids = listOf("transport", "maps", "download", "cards", "settings")
                val outlineIcons = listOf(
                    R.drawable.navbar_outline_explore,
                    R.drawable.navbar_outline_map,
                    R.drawable.navbar_outline_download,
                    R.drawable.navbar_outline_card,
                    R.drawable.navbar_outline_settings
                )
                val filledIcons = listOf(
                    R.drawable.navbar_filled_explore,
                    R.drawable.navbar_filled_map,
                    R.drawable.navbar_filled_download,
                    R.drawable.navbar_filled_card,
                    R.drawable.navbar_filled_settings
                )

                fun getIcon(flag: Boolean): List<Int> {
                    return if (flag) {
                        filledIcons
                    } else {
                        outlineIcons
                    }
                }
                val navController = rememberNavController()
                var screen by rememberSaveable { mutableIntStateOf(0) }

                EmeraldTheme {
                    Surface {
                        when (windowSizeClass.widthSizeClass) {
                            WindowWidthSizeClass.Compact -> {
                                Scaffold(bottomBar = {
                                    NavigationBar {
                                        items.forEachIndexed { index, item ->
                                            NavigationBarItem(
                                                icon = { Icon(imageVector = ImageVector.vectorResource
                                                    (id = getIcon(screen ==index)[index]),
                                                    contentDescription = null) },
                                                label = { Text(text = stringResource(item)) },
                                                selected = screen == index,
                                                alwaysShowLabel = false,
                                                onClick = { screen = index
                                                    navController.navigate(ids[index]) }
                                            )
                                        }
                                    }
                                }) {
                                    Box(modifier = Modifier.safeContentPadding().fillMaxSize()) {
                                        NavHost(navController = navController, startDestination = "transport") {
                                            composable("transport") { TransportScreen() }
                                            composable("maps") { MapsScreen() }
                                            composable("download") { DownloadsScreen() }
                                            composable("cards") { CardsScreen() }
                                            composable("settings") { SettingsScreen() }
                                        }
                                    }
                                }
                            }
                            WindowWidthSizeClass.Expanded -> {
                                Row(modifier = Modifier.safeContentPadding().fillMaxSize()) {
                                    NavigationRail {
                                        Column(
                                            verticalArrangement = (Arrangement.spacedBy(10.dp)),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            items.forEachIndexed { index, item ->
                                                NavigationRailItem(
                                                    icon = { Icon(
                                                        imageVector = ImageVector.vectorResource
                                                            (id = getIcon(screen==index)[index]),
                                                        contentDescription = null) },
                                                    label = { Text(text = stringResource(item)) },
                                                    selected = screen == index,
                                                    alwaysShowLabel = false,
                                                    onClick = { screen = index
                                                        navController.navigate(ids[index]) }
                                                )
                                            }
                                        }
                                    }
                                    Box(modifier = Modifier.safeContentPadding().fillMaxSize()) {
                                        NavHost(navController = navController, startDestination = "transport") {
                                            composable("transport") { TransportScreen() }
                                            composable("maps") { MapsScreen() }
                                            composable("download") { DownloadsScreen() }
                                            composable("cards") { CardsScreen() }
                                            composable("settings") { SettingsScreen() }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

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