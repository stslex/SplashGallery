package com.stslex.splashgallery.ui.detail_photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.data.model.domain.DownloadModel
import com.stslex.splashgallery.data.repository.interf.DownloadRepository
import com.stslex.splashgallery.domain.photo.PhotoInteractor
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@ExperimentalCoroutinesApi
class PhotoDetailsViewModel @Inject constructor(
    private val interactor: PhotoInteractor,
    private val downloadRepository: DownloadRepository,
    private val response: PhotoUIResponse
) : ViewModel() {

    suspend fun getCurrentPhoto(id: String): StateFlow<PhotoUIResult> =
        response.mapIt(interactor.getCurrentPhoto(id)).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = PhotoUIResult.Loading
        )

    suspend fun downloadPhoto(id: String): StateFlow<Result<DownloadModel>> =
        downloadRepository.downloadPhoto(id).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Result.Loading
        )


}