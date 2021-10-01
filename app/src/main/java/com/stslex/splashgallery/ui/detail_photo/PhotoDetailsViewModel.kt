package com.stslex.splashgallery.ui.detail_photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.data.model.download.RemoteDownloadModel
import com.stslex.splashgallery.data.model.image.RemoteImageModel
import com.stslex.splashgallery.data.photo.PhotoRepository
import com.stslex.splashgallery.ui.core.UIResponse
import com.stslex.splashgallery.ui.core.UIResult
import com.stslex.splashgallery.ui.model.DownloadModel
import com.stslex.splashgallery.ui.model.image.ImageModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@ExperimentalCoroutinesApi
class PhotoDetailsViewModel @Inject constructor(
    private val repository: PhotoRepository,
    private val photoMapper: Abstract.Mapper.DataToUI<RemoteImageModel, UIResult<ImageModel>>,
    private val downloadMapper: Abstract.Mapper.DataToUI<RemoteDownloadModel, UIResult<DownloadModel>>,
    private val response: UIResponse
) : ViewModel() {

    suspend fun getCurrentPhoto(id: String): StateFlow<UIResult<ImageModel>> =
        response.getAndMap(repository.getCurrentPhoto(id), photoMapper).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UIResult.Loading
        )

    suspend fun downloadPhoto(id: String): StateFlow<UIResult<DownloadModel>> =
        response.getAndMap(repository.downloadPhoto(id), downloadMapper).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UIResult.Loading
        )
}