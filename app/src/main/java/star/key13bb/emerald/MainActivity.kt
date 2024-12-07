package star.key13bb.emerald

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import star.key13bb.emerald.scripts.updater
import star.key13bb.emerald.ui.screens.*
import star.key13bb.emerald.ui.theme.EmeraldTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch { async { updater() } }
        setContent {
            EmeraldTheme {
                val windowSizeClass = calculateWindowSizeClass(this@MainActivity)
                MainScreen(windowSizeClass)
            }
        }
    }
}

@Composable
fun MainScreen(windowSizeClass: WindowSizeClass) {
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

    fun getIcon(flag: Boolean) = if (flag) filledIcons else outlineIcons

    val navController = rememberNavController()
    var screen by rememberSaveable { mutableIntStateOf(0) }

    Surface {
        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                CompactLayout(navController, items, ids, ::getIcon, screen, windowSizeClass) { screen = it }
            }
            WindowWidthSizeClass.Expanded -> {
                ExpandedLayout(navController, items, ids, ::getIcon, screen, windowSizeClass) { screen = it }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CompactLayout(
    navController: NavHostController,
    items: List<Int>,
    ids: List<String>,
    getIcon: (Boolean) -> List<Int>,
    screen: Int,
    windowSizeClass: WindowSizeClass,
    onScreenChange: (Int) -> Unit
) {
    Scaffold(bottomBar = {
        NavigationBar {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = getIcon(screen == index)[index]),
                            contentDescription = null
                        )
                    },
                    label = { Text(text = stringResource(item)) },
                    selected = screen == index,
                    alwaysShowLabel = false,
                    onClick = {
                        onScreenChange(index)
                        navController.popBackStack()
                        navController.navigate(ids[index])
                    }
                )
            }
        }
    }) {
        Column(modifier = Modifier.padding(15.dp).safeDrawingPadding()) {
            Title(stringResource(items[screen]))
            Box(modifier = Modifier.fillMaxSize().safeDrawingPadding()) {
                NavHost(navController = navController, startDestination = "transport") {
                    composable("transport") { TransportChooser(windowSizeClass) }
                    composable("maps") { MapsChooser(windowSizeClass) }
                    composable("download") { DownloadsChooser(windowSizeClass) }
                    composable("settings") { SettingsChooser(windowSizeClass) }
                }
            }
        }
    }
}

@Composable
fun ExpandedLayout(
    navController: NavHostController,
    items: List<Int>,
    ids: List<String>,
    getIcon: (Boolean) -> List<Int>,
    screen: Int,
    windowSizeClass: WindowSizeClass,
    onScreenChange: (Int) -> Unit
) {
    Row(modifier = Modifier.safeContentPadding().fillMaxSize()) {
        NavigationRail(modifier = Modifier.align(Alignment.CenterVertically)) {
            items.forEachIndexed { index, item ->
                NavigationRailItem(
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = getIcon(screen == index)[index]),
                            contentDescription = null
                        )
                    },
                    label = { Text(text = stringResource(item)) },
                    selected = screen == index,
                    alwaysShowLabel = false,
                    onClick = {
                        onScreenChange(index)
                        navController.popBackStack()
                        navController.navigate(ids[index])
                    }
                )
            }
        }
        Column(modifier = Modifier.padding(15.dp).safeDrawingPadding()) {
            Title(stringResource(items[screen]))
            Box(modifier = Modifier.fillMaxSize()) {
                NavHost(navController = navController, startDestination = "transport") {
                    composable("transport") { TransportChooser(windowSizeClass) }
                    composable("maps") { MapsChooser(windowSizeClass) }
                    composable("download") { DownloadsChooser(windowSizeClass) }
                    composable("settings") { SettingsChooser(windowSizeClass) }
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