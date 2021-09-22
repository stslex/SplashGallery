package com.stslex.splashgallery.ui.collections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.domain.collections.CollectionInteractor
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CollectionViewModel @Inject constructor(
    private val interactor: CollectionInteractor,
    private val response: CollectionUIResponse
) : ViewModel() {

    suspend fun getAllCollections(page: Int): StateFlow<CollectionUIResult> =
        response.create(interactor.getAllCollections(page)).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = CollectionUIResult.Loading
        )

    suspend fun getUserCollections(
        username: String,
        page: Int
    ): StateFlow<CollectionUIResult> =
        response.create(interactor.getUserCollections(username, page)).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = CollectionUIResult.Loading
        )
}