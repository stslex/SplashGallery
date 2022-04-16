package com.stslex.splashgallery.ui.detail_photo

import android.annotation.SuppressLint
import android.app.Application
import android.app.DownloadManager
import android.app.DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
import android.content.Context.DOWNLOAD_SERVICE
import android.database.Cursor
import android.net.Uri
import android.os.Environment.DIRECTORY_DOWNLOADS
import javax.inject.Inject
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface DownloadImageUseCase {

    suspend fun download(url: String, fileName: String): com.stslex.core.Resource<Nothing?>

    class Base @Inject constructor(
        private val application: Application
    ) : DownloadImageUseCase {

        @SuppressLint("Range")
        override suspend fun download(
            url: String,
            fileName: String
        ): com.stslex.core.Resource<Nothing?> = suspendCoroutine { continuation ->
            val downloadManager = application.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
            val request = DownloadManager
                .Request(Uri.parse(url))
                .setTitle(DOWNLOAD_TITLE)
                .setDescription(DOWNLOAD_DESCRIPTION)
                .setDestinationInExternalFilesDir(application, DIRECTORY_DOWNLOADS, fileName)
                .setNotificationVisibility(VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            downloadManager.enqueue(request)
            val query = DownloadManager.Query()
            query.setFilterByStatus(DownloadManager.STATUS_FAILED)
            query.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL)
            val cursor: Cursor = downloadManager.query(query)
            if (cursor.moveToFirst()) {
                when (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))) {
                    DownloadManager.STATUS_SUCCESSFUL -> {
                        continuation.resumeWith(Result.success(com.stslex.core.Resource.Success(null)))
                    }
                    DownloadManager.STATUS_FAILED -> {
                        continuation.resumeWithException(Exception("Failed"))
                    }
                }
            }
        }

        companion object {
            private const val DOWNLOAD_TITLE: String = "Downloading"
            private const val DOWNLOAD_DESCRIPTION: String = "Downloading image..."
        }
    }
}