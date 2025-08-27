package com.stslex.splashgallery.ui.detail_photo

import com.stslex.splashgallery.core.Resource
import com.stslex.splashgallery.core_coroutines.AppDispatcher
import com.stslex.splashgallery.core_ui.BaseViewModel
import com.stslex.splashgallery.core_model.data.DownloadModel
import com.stslex.splashgallery.core_model.data.image.ImageDataModel
import com.stslex.splashgallery.data.photo.DownloadDataMapper
import com.stslex.splashgallery.data.photo.PhotoDataMapper
import com.stslex.splashgallery.data.photo.PhotoRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class PhotoDetailsViewModel @Inject constructor(
    private val repository: PhotoRepository,
    private val photoMapper: PhotoDataMapper,
    private val downloadMapper: DownloadDataMapper,
    private val downloadImageUseCase: DownloadImageUseCase,
    appDispatcher: AppDispatcher
) : BaseViewModel(appDispatcher = appDispatcher) {

    @ExperimentalCoroutinesApi
    val getCurrentPhoto: (id: String) -> StateFlow<Resource<ImageDataModel>>
        get() = { id ->
            repository.currentPhoto(id).flatMapLatest {
                flowOf(it.map(photoMapper))
            }.getReformat
        }

    @ExperimentalCoroutinesApi
    suspend fun downloadImageUrl(id: String): StateFlow<Resource<DownloadModel>> =
        repository.downloadPhoto(id).flatMapLatest {
            flowOf(it.map(downloadMapper))
        }.getReformat

    suspend fun downloadImage(
        url: String,
        fileName: String
    ): StateFlow<Resource<Nothing?>> = flow {
        emit(downloadImageUseCase.download(url, fileName))
    }.getReformat
}