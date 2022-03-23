package com.stslex.splashgallery.ui.detail_photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.data.model.ui.DownloadModel
import com.stslex.splashgallery.data.model.ui.image.ImageModel
import com.stslex.splashgallery.data.photo.DownloadDataMapper
import com.stslex.splashgallery.data.photo.PhotoDataMapper
import com.stslex.splashgallery.data.photo.PhotoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PhotoDetailsViewModel @Inject constructor(
    private val repository: PhotoRepository,
    private val photoMapper: PhotoDataMapper,
    private val downloadMapper: DownloadDataMapper,
    private val downloadImageUseCase: DownloadImageUseCase
) : ViewModel() {

    @ExperimentalCoroutinesApi
    suspend fun getCurrentPhoto(id: String): StateFlow<com.stslex.core.Resource<ImageModel>> =
        repository.getCurrentPhoto(id).flatMapLatest {
            flowOf(it.map(photoMapper))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = com.stslex.core.Resource.Loading
        )

    @ExperimentalCoroutinesApi
    suspend fun downloadImageUrl(id: String): StateFlow<com.stslex.core.Resource<DownloadModel>> =
        repository.downloadPhoto(id).flatMapLatest {
            flowOf(it.map(downloadMapper))
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = com.stslex.core.Resource.Loading
        )

    suspend fun downloadImage(
        url: String,
        fileName: String
    ): StateFlow<com.stslex.core.Resource<Nothing?>> = flow {
        emit(downloadImageUseCase.download(url, fileName))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = com.stslex.core.Resource.Loading
    )
}