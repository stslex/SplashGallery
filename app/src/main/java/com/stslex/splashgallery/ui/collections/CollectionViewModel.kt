package com.stslex.splashgallery.ui.collections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.data.repository.interf.CollectionRepository
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class CollectionViewModel @Inject constructor(
    private val repository: CollectionRepository
) : ViewModel() {

    suspend fun getAllCollections(page: Int): StateFlow<Result<List<CollectionModel>>> =
        repository.getAllCollections(page).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Result.Loading
        )

    suspend fun getUserCollections(
        username: String,
        page: Int
    ): StateFlow<Result<List<CollectionModel>>> =
        repository.getUserCollections(username, page).stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = Result.Loading
        )
}