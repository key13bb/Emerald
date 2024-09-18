package star.kiko13bb.emerald.scripts

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import star.kiko13bb.emerald.UserSettings
import java.io.File

fun downloadFileToCache(context: Context, url: String, fileName: String) {
    var onMetered = UserSettings.getDefaultInstance().onMetered
    val request = DownloadManager.Request(Uri.parse(url))
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
        .setDestinationInExternalFilesDir(context, File(context.filesDir.path).path, fileName)
        .setAllowedOverMetered(onMetered)

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    downloadManager.enqueue(request)
}