package star.kiko13bb.emerald.scripts

import android.content.Context
import android.net.ConnectivityManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import star.kiko13bb.emerald.context
import star.kiko13bb.emerald.ui.components.isMetered

val manager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

suspend fun updater() {
    withContext(Dispatchers.Default) {
        while (true) {
            isMetered = manager.isActiveNetworkMetered
            delay(1000)
        }
    }
}