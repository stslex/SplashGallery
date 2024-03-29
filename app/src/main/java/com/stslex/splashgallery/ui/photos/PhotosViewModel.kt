package com.stslex.splashgallery.ui.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.stslex.splashgallery.data.photos.QueryPhotos
import com.stslex.splashgallery.ui.model.ImageUIModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Provider

class PhotosViewModel @Inject constructor(
    private val queryPhotosUseCaseProvider: Provider<QueryPhotosUseCase>
) : ViewModel() {

    private val _query: MutableStateFlow<QueryPhotos> = MutableStateFlow(QueryPhotos.EmptyQuery)
    val query: StateFlow<QueryPhotos> = _query.asStateFlow()

    private var newPagingSource: PagingSource<*, *>? = null

    private val pagingConfig: PagingConfig by lazy {
        PagingConfig(PAGE_SIZE, enablePlaceholders = false)
    }

    @ExperimentalCoroutinesApi
    val photos: StateFlow<PagingData<ImageUIModel>> = query
        .map(::newPager)
        .flatMapLatest { pager -> pager.flow }
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private fun newPager(query: QueryPhotos): Pager<Int, ImageUIModel> {
        return Pager(pagingConfig) {
            newPagingSource?.invalidate()
            val queryPhotosUseCase = queryPhotosUseCaseProvider.get()
            queryPhotosUseCase(query).also { newPagingSource = it }
        }
    }

    fun setQuery(query: QueryPhotos) {
        _query.tryEmit(query)
    }

    companion object {
        private const val PAGE_SIZE: Int = 10
    }
}