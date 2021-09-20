package com.stslex.splashgallery.ui.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.domain.photos.PhotosInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AllPhotosViewModel @Inject constructor(
    private val interactor: PhotosInteractor,
    private val response: PhotosUIResponse
) : ViewModel() {

    suspend fun getAllPhotos(page: Int): StateFlow<PhotosUIResult> =
        response.mapIt(interactor.getAllPhotos(page)).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = PhotosUIResult.Loading
        )

    suspend fun getUserPhotos(
        username: String,
        page: Int
    ): StateFlow<PhotosUIResult> =
        response.mapIt(interactor.getUserPhotos(username, page)).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = PhotosUIResult.Loading
        )

    suspend fun getUserLikes(
        username: String,
        page: Int
    ): StateFlow<PhotosUIResult> =
        response.mapIt(interactor.getUserLikes(username, page)).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = PhotosUIResult.Loading
        )

    suspend fun getCollectionPhotos(
        id: String,
        page: Int
    ): StateFlow<PhotosUIResult> =
        response.mapIt(interactor.getCollectionPhotos(id, page)).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = PhotosUIResult.Loading
        )

}