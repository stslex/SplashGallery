package com.stslex.splashgallery.ui.detail_photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.domain.download.DownloadInteractor
import com.stslex.splashgallery.domain.photo.PhotoInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@ExperimentalCoroutinesApi
class PhotoDetailsViewModel @Inject constructor(
    private val interactor: PhotoInteractor,
    private val downloadInteractor: DownloadInteractor,
    private val response: PhotoUIResponse,
    private val downloadResponse: DownloadUIResponse
) : ViewModel() {

    suspend fun getCurrentPhoto(id: String): StateFlow<PhotoUIResult> =
        response.create(interactor.getCurrentPhoto(id)).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = PhotoUIResult.Loading
        )

    suspend fun downloadPhoto(id: String): StateFlow<DownloadUIResult> =
        downloadResponse.create(downloadInteractor.downloadPhoto(id)).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = DownloadUIResult.Loading
        )


}