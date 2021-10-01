package com.stslex.splashgallery.ui.detail_photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.data.model.download.RemoteDownloadModel
import com.stslex.splashgallery.data.model.image.RemoteImageModel
import com.stslex.splashgallery.data.photo.DataResult
import com.stslex.splashgallery.data.photo.PhotoRepository
import com.stslex.splashgallery.ui.model.DownloadModel
import com.stslex.splashgallery.ui.model.image.ImageModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class PhotoDetailsViewModel @Inject constructor(
    private val repository: PhotoRepository,
    private val photoMapper: Abstract.Mapper.DataToUI<RemoteImageModel, UIResult<ImageModel>>,
    private val downloadMapper: Abstract.Mapper.DataToUI<RemoteDownloadModel, UIResult<DownloadModel>>
) : ViewModel() {

    suspend fun getCurrentPhoto(id: String): StateFlow<UIResult<ImageModel>> =
        createImage(repository.getCurrentPhoto(id)).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UIResult.Loading
        )

    suspend fun downloadPhoto(id: String): StateFlow<UIResult<DownloadModel>> =
        createDownload(repository.downloadPhoto(id)).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UIResult.Loading
        )

    private fun createImage(flow: Flow<DataResult<RemoteImageModel>>): Flow<UIResult<ImageModel>> =
        callbackFlow {
            flow.startCollecting(photoMapper) {
                trySendBlocking(it)
            }
            awaitClose { }
        }

    private fun createDownload(flow: Flow<DataResult<RemoteDownloadModel>>): Flow<UIResult<DownloadModel>> =
        callbackFlow {
            flow.startCollecting(downloadMapper) {
                trySendBlocking(it)
            }
            awaitClose { }
        }

    private suspend inline fun <D, T> Flow<DataResult<D>>.startCollecting(
        mapper: Abstract.Mapper.DataToUI<D, UIResult<T>>,
        crossinline function: (UIResult<T>) -> Unit,
    ) = try {
        collect {
            function(it.map(mapper))
        }
    } catch (exception: Exception) {
        function(UIResult.Failure(exception))
    }
}