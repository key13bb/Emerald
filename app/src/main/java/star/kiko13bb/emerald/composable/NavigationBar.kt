package star.kiko13bb.emerald.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import star.kiko13bb.emerald.*

val items = listOf(R.string.navbar_explore, R.string.navbar_map, R.string.navbar_download, R.string.navbar_card, R.string.navbar_settings)
val outlineIcons = listOf(R.drawable.navbar_outline_explore, R.drawable.navbar_outline_map, R.drawable.navbar_outline_download, R.drawable.navbar_outline_card, R.drawable.navbar_outline_settings)
val filledIcons = listOf(R.drawable.navbar_filled_explore, R.drawable.navbar_filled_map, R.drawable.navbar_filled_download, R.drawable.navbar_filled_card, R.drawable.navbar_filled_settings)
var selectedItem by mutableIntStateOf(0)

fun getIcon(flag: Boolean): List<Int> {
    return if (flag) {
        filledIcons
    } else {
        outlineIcons
    }
}

@Composable
fun MainBottomNavBar() {
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(imageVector = ImageVector.vectorResource(id = getIcon(selectedItem==index)[index]), contentDescription = null) },
                label = { Text(text = stringResource(item)) },
                selected = selectedItem == index,
                alwaysShowLabel = false,
                onClick = { selectedItem = index }
            )
        }
    }
}

@Composable
fun MainRailNavBar(modifier: Modifier = Modifier) {
    NavigationRail {
        Column(
            modifier = modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items.forEachIndexed { index, item ->
                NavigationRailItem(
                    icon = { Icon(imageVector = ImageVector.vectorResource(id = getIcon(selectedItem==index)[index]), contentDescription = null) },
                    label = { Text(text = stringResource(item)) },
                    selected = selectedItem == index,
                    alwaysShowLabel = false,
                    onClick = { selectedItem = index }
                )
            }
        }
    }
}