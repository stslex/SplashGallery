package com.stslex.splashgallery.utils

import android.app.DownloadManager
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.os.Environment
import androidx.fragment.app.Fragment
import com.stslex.splashgallery.utils.Resources.unknown
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun String?.isNullCheck(): String =
    if (this == null || this == "") {
        unknown
    } else this

suspend fun Fragment.startDownload(url: String, fileName: String) = withContext(Dispatchers.IO) {
    val downloadManager = requireActivity().getSystemService(DOWNLOAD_SERVICE) as DownloadManager
    val request = DownloadManager.Request(Uri.parse(url))
    request.setTitle("Downloading")
        .setDescription("Downloading image...")
        .setDestinationInExternalFilesDir(
            requireContext(),
            Environment.DIRECTORY_DOWNLOADS,
            fileName
        )
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
    downloadManager.enqueue(request)
}
