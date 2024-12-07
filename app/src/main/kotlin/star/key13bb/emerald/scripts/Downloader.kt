package star.key13bb.emerald.scripts

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import star.key13bb.emerald.UserSettings

fun downloadFileToCache(context: Context, url: String, fileName: String) {
    var onMetered = UserSettings.getDefaultInstance().onMetered
    val request = DownloadManager.Request(Uri.parse(url))
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
        .setDestinationInExternalFilesDir(context, null, fileName)
        .setAllowedOverMetered(onMetered)

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    downloadManager.enqueue(request)
}