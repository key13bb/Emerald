package star.kiko13bb.emerald.gtfs

import org.onebusaway.gtfs.serialization.GtfsReader
import star.kiko13bb.emerald.context
import star.kiko13bb.emerald.scripts.downloadFileToCache
import java.io.File

const val DEFAULT_NAME = "temp.zip"

class GTFSManager(source: String) {
    var title: String
    val reader = GtfsReader()
    init {
        downloadFileToCache(context!!, source, DEFAULT_NAME)
        reader.setInputLocation(File(context!!.filesDir.path + "/" + DEFAULT_NAME))
        reader.run()
        title = reader.defaultAgencyId
    }

    fun getAgenciesList(): String {
        return reader.agencies[0].name
    }

    @Override
    fun getIt(): String {
        return title
    }

}