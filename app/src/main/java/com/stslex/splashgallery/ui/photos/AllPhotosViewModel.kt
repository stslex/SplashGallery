package com.stslex.splashgallery.ui.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.domain.PhotosDomainMapper
import com.stslex.splashgallery.domain.PhotosDomainResult
import com.stslex.splashgallery.domain.PhotosInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AllPhotosViewModel @Inject constructor(
    private val interactor: PhotosInteractor,
    private val mapper: PhotosDomainMapper<PhotosUIResult>
) : ViewModel() {

    suspend fun getAllPhotos(page: Int): StateFlow<PhotosUIResult> =
        interactor.getAllPhotos(page).mapIt().stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = PhotosUIResult.Loading
        )

    suspend fun getUserPhotos(
        username: String,
        page: Int
    ): StateFlow<PhotosUIResult> =
        interactor.getUserPhotos(username, page).mapIt().stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = PhotosUIResult.Loading
        )

    suspend fun getUserLikes(
        username: String,
        page: Int
    ): StateFlow<PhotosUIResult> =
        interactor.getUserLikes(username, page).mapIt().stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = PhotosUIResult.Loading
        )

    suspend fun getCollectionPhotos(
        id: String,
        page: Int
    ): StateFlow<PhotosUIResult> =
        interactor.getCollectionPhotos(id, page).mapIt().stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = PhotosUIResult.Loading
        )

    private suspend fun Flow<PhotosDomainResult>.mapIt(): Flow<PhotosUIResult> =
        callbackFlow {
            response {
                trySendBlocking(it)
            }
            awaitClose { }
        }

    private suspend inline fun Flow<PhotosDomainResult>.response(
        crossinline function: (PhotosUIResult) -> Unit
    ) = try {
        this@response.collect {
            function(it.map(mapper))
        }
    } catch (exception: Exception) {
        PhotosUIResult.Failure(exception.toString())
    }

}