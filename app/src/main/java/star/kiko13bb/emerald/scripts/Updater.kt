package star.kiko13bb.emerald.scripts

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import star.kiko13bb.emerald.ui.components.isMetered
import star.kiko13bb.emerald.ui.components.manager

suspend fun updater() {
    withContext(Dispatchers.Default) {
        while (true) {
            isMetered = manager.isActiveNetworkMetered
            delay(1000)
        }
    }
}