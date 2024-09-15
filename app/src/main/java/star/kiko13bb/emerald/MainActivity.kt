package star.kiko13bb.emerald

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import star.kiko13bb.emerald.scripts.updater
import star.kiko13bb.emerald.ui.screens.DownloadsChooser
import star.kiko13bb.emerald.ui.screens.MapsChooser
import star.kiko13bb.emerald.ui.screens.SettingsChooser
import star.kiko13bb.emerald.ui.screens.TransportChooser
import star.kiko13bb.emerald.ui.theme.EmeraldTheme

// Make them public to be used in other files
var windowSizeClass: WindowSizeClass? = null
var context: Context? = null

// Main activity class
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        context = this@MainActivity
        lifecycleScope.launch {
            async {
                updater()
            }
        }
        setContent {
            EmeraldTheme {
                // Variable which decides how to draw the screen
                windowSizeClass = calculateWindowSizeClass(this@MainActivity)

                // Navigation bar items information
                val items = listOf(
                    R.string.navbar_transport,
                    R.string.navbar_map,
                    R.string.navbar_download,
                    R.string.navbar_settings
                )
                val ids = listOf("transport", "maps", "download", "settings")
                val outlineIcons = listOf(
                    R.drawable.navbar_outline_transport,
                    R.drawable.navbar_outline_map,
                    R.drawable.navbar_outline_download,
                    R.drawable.navbar_outline_settings
                )
                val filledIcons = listOf(
                    R.drawable.navbar_filled_transport,
                    R.drawable.navbar_filled_map,
                    R.drawable.navbar_filled_download,
                    R.drawable.navbar_filled_settings
                )

                // Function to give a selected filled icon or an unselected outline icon
                fun getIcon(flag: Boolean): List<Int> {
                    return if (flag) {
                        filledIcons
                    } else {
                        outlineIcons
                    }
                }

                // Variables to control screen's content
                val navController = rememberNavController()
                var screen by rememberSaveable { mutableIntStateOf(0) }

                // Main screen with navigation bar
                Surface {
                    when (windowSizeClass!!.widthSizeClass) {

                        // When screen is straight
                        WindowWidthSizeClass.Compact -> {
                            Scaffold(bottomBar = {

                                // Bottom navigation bar definition
                                NavigationBar {
                                    items.forEachIndexed { index, item ->

                                        // Navigation bar items definition from list
                                        NavigationBarItem(
                                            icon = {
                                                Icon(
                                                    imageVector = ImageVector.vectorResource
                                                        (id = getIcon(screen == index)[index]),
                                                    contentDescription = null
                                                )
                                            },
                                            label = { Text(text = stringResource(item)) },
                                            selected = screen == index,
                                            alwaysShowLabel = false,
                                            onClick = {
                                                screen = index
                                                navController.popBackStack()
                                                navController.navigate(ids[index])
                                            }
                                        )
                                    }
                                }
                            }
                            ) {
                                // Rest of screen, without drawing neither notch nor navigation bar

                                Column(modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp).safeDrawingPadding()) {
                                    Title(stringResource(items[screen]))
                                    Box(
                                        modifier = Modifier
                                            .safeDrawingPadding()
                                            .fillMaxSize()
                                    ) {
                                        NavHost(
                                            navController = navController,
                                            startDestination = "transport"
                                        ) {
                                            composable("transport") { TransportChooser() }
                                            composable("maps") { MapsChooser() }
                                            composable("download") { DownloadsChooser() }
                                            composable("settings") { SettingsChooser() }
                                        }
                                    }
                                }
                            }
                        }

                        // When screen is wide or in portrait mode
                        WindowWidthSizeClass.Expanded -> {

                            // Draw on all screen except on notch or bottom gesture bar
                            Row(modifier = Modifier
                                .safeContentPadding()
                                .fillMaxSize()) {

                                // Navigation rail definition
                                NavigationRail(modifier = Modifier.align(Alignment.CenterVertically)) {

                                    items.forEachIndexed { index, item ->

                                        // Navigation rail items definition from list
                                        NavigationRailItem(
                                            icon = {
                                                Icon(
                                                    imageVector = ImageVector.vectorResource
                                                        (id = getIcon(screen == index)[index]),
                                                    contentDescription = null
                                                )
                                            },
                                            label = { Text(text = stringResource(item)) },
                                            selected = screen == index,
                                            alwaysShowLabel = false,
                                            onClick = {
                                                screen = index
                                                navController.popBackStack()
                                                navController.navigate(ids[index])
                                            }
                                        )
                                    }
                                }
                                Column(modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 10.dp, bottom = 10.dp).safeDrawingPadding()) {
                                    Title(stringResource(items[screen]))


                                    // Rest of screen, without drawing neither notch nor bottom gesture bar
                                    Box(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        NavHost(
                                            navController = navController,
                                            startDestination = "transport",
                                        ) {
                                            composable("transport") { TransportChooser() }
                                            composable("maps") { MapsChooser() }
                                            composable("download") { DownloadsChooser() }
                                            composable("settings") { SettingsChooser() }
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
fun Title(title: String) {
    Spacer(modifier = Modifier.size(60.dp))
    Text(text = title, fontSize = 32.sp)
    Spacer(modifier = Modifier.size(20.dp))
}