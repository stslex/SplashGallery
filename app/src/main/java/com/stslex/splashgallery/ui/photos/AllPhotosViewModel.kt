package com.stslex.splashgallery.ui.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.domain.PhotosDomain
import com.stslex.splashgallery.domain.PhotosDomainMapper
import com.stslex.splashgallery.domain.PhotosInteractor
import com.stslex.splashgallery.domain.core.DomainResult
import com.stslex.splashgallery.ui.core.UIResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AllPhotosViewModel @Inject constructor(
    private val interactor: PhotosInteractor,
    private val mapper: PhotosDomainMapper<UIResult<List<PhotosUI>>>
) : ViewModel() {

    suspend fun getAllPhotos(page: Int): StateFlow<UIResult<List<PhotosUI>>> =
        interactor.getAllPhotos(page).mapIt().stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UIResult.Loading
        )

    suspend fun getUserPhotos(
        username: String,
        page: Int
    ): StateFlow<UIResult<List<PhotosUI>>> =
        interactor.getUserPhotos(username, page).mapIt().stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UIResult.Loading
        )

    suspend fun getUserLikes(
        username: String,
        page: Int
    ): StateFlow<UIResult<List<PhotosUI>>> =
        interactor.getUserLikes(username, page).mapIt().stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UIResult.Loading
        )

    suspend fun getCollectionPhotos(
        id: String,
        page: Int
    ): StateFlow<UIResult<List<PhotosUI>>> =
        interactor.getCollectionPhotos(id, page).mapIt().stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = UIResult.Loading
        )

    private suspend fun Flow<DomainResult<List<PhotosDomain>>>.mapIt(): Flow<UIResult<List<PhotosUI>>> =
        callbackFlow {
            response {
                trySendBlocking(it)
            }
            awaitClose { }
        }

    private suspend inline fun Flow<DomainResult<List<PhotosDomain>>>.response(
        crossinline function: (UIResult<List<PhotosUI>>) -> Unit
    ) = try {
        this@response.collect {
            function(it.map(mapper))
        }
    } catch (exception: Exception) {
        UIResult.Failure(exception.toString())
    }

}