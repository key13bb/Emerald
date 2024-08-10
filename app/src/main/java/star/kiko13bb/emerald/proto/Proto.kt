@file:Suppress("unused")

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

object SettingsSerializer : Serializer<UserSettings> {
    override val defaultValue: UserSettings = UserSettings.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserSettings {
        try {
            return UserSettings.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(
        t: UserSettings,
        output: OutputStream) = t.writeTo(output)
}

val Context.settingsDataStore: DataStore<UserSettings> by dataStore(
    fileName = "usersettings.pb",
    serializer = SettingsSerializer
)