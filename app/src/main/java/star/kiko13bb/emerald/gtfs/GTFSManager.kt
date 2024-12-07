package star.kiko13bb.emerald.gtfs

import kotlinx.coroutines.launch
import org.onebusaway.gtfs.impl.GtfsDaoImpl
import org.onebusaway.gtfs.serialization.GtfsReader
import star.kiko13bb.emerald.context
import star.kiko13bb.emerald.scripts.downloadFileToCache
import java.io.File

const val DEFAULT_NAME = "temp.zip"

class GTFSManager(source: String) {
    var store: GtfsDaoImpl = GtfsDaoImpl()
    val reader = GtfsReader()
    init {
        val coroutineScope = kotlinx.coroutines.MainScope()
        coroutineScope.launch {
            downloadFileToCache(context!!, source, DEFAULT_NAME)
            reader.setInputLocation(File(context!!.filesDir, DEFAULT_NAME))
            reader.entityStore = store
            reader.run()
        }
    }

    fun getAgenciesList(): List<String> {
        return store.allAgencies.map { it.name }
    }
}