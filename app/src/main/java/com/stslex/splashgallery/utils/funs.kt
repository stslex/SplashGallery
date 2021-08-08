package com.stslex.splashgallery.utils

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.os.Environment
import androidx.fragment.app.Fragment
import com.stslex.splashgallery.GalleryApplication
import com.stslex.splashgallery.R
import com.stslex.splashgallery.di.component.AppComponent

val Context.appComponent: AppComponent
    get() = when (this) {
        is GalleryApplication -> appComponent
        else -> this.applicationContext.appComponent
    }

fun Activity.setResources() {
    photos = getString(R.string.label_photos)
    likes = getString(R.string.label_likes)
    collections = getString(R.string.label_collections)
    cache = cacheDir
}

fun Fragment.startDownload(url: String, fileName: String) {
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
