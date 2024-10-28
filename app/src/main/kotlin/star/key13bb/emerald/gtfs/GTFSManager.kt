package star.key13bb.emerald.gtfs

import org.onebusaway.gtfs.serialization.GtfsReader
import star.key13bb.emerald.MainActivity
import star.key13bb.emerald.scripts.downloadFileToCache
import java.io.File

const val DEFAULT_NAME = "temp.zip"
val context = MainActivity()

class GTFSManager(source: String) {
    var title: String
    val reader = GtfsReader()
    init {
        downloadFileToCache(context, source, DEFAULT_NAME)
        reader.setInputLocation(File(context.filesDir.path + "/" + DEFAULT_NAME))
        reader.run()
        title = reader.defaultAgencyId
    }

    fun getAgenciesList(): List<String> {
        return reader.agencies.map { it.id }
    }
}