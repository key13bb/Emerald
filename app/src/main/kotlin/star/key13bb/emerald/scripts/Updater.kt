package star.key13bb.emerald.scripts

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import star.key13bb.emerald.ui.components.isMetered
import star.key13bb.emerald.ui.components.manager

suspend fun updater() {
    withContext(Dispatchers.Default) {
        while (true) {
            isMetered = manager.isActiveNetworkMetered
            delay(500)
        }
    }
}