package star.kiko13bb.emerald.scripts

import android.app.DownloadManager
import android.content.Context
import android.net.Uri

fun downloadFileToCache(context: Context, url: String, fileName: String, metered: Boolean) {
    val request = DownloadManager.Request(Uri.parse(url))
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
        .setDestinationInExternalFilesDir(context, null, fileName)
        .setAllowedOverMetered(metered)

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    downloadManager.enqueue(request)
}