package star.kiko13bb.emerald.proto

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import star.kiko13bb.emerald.UserSettings
import java.io.InputStream
import java.io.OutputStream

object GTFSFilesSerializer : Serializer<UserSettings.GTFSFiles> {
    override val defaultValue: UserSettings.GTFSFiles = UserSettings.GTFSFiles.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserSettings.GTFSFiles {
        try {
            return UserSettings.GTFSFiles.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: UserSettings.GTFSFiles,
        output: OutputStream
    ) {
        t.writeTo(output)
    }
}

val Context.GTFSFilesDataStore: DataStore<UserSettings.GTFSFiles> by dataStore(
    fileName = "gtfsfiles.pb",
    serializer = GTFSFilesSerializer
)