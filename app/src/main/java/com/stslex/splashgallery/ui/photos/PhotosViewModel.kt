package com.stslex.splashgallery.ui.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.stslex.splashgallery.data.core.QueryPhotos
import com.stslex.splashgallery.ui.model.image.ImageModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Provider

@ExperimentalCoroutinesApi
class PhotosViewModel @Inject constructor(
    private val queryPhotosUseCaseProvider: Provider<QueryPhotosUseCase>
) : ViewModel() {

    private val _query: MutableStateFlow<QueryPhotos> = MutableStateFlow(QueryPhotos.EmptyQuery)
    val query: StateFlow<QueryPhotos> = _query.asStateFlow()

    private var newPagingSource: PagingSource<*, *>? = null

    val photos: StateFlow<PagingData<ImageModel>> = query
        .map(::newPager)
        .flatMapLatest { pager -> pager.flow }
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private fun newPager(query: QueryPhotos): Pager<Int, ImageModel> {
        return Pager(pagingConfig) {
            newPagingSource?.invalidate()
            val queryPhotosUseCase = queryPhotosUseCaseProvider.get()
            queryPhotosUseCase(query).also { newPagingSource = it }
        }
    }

    private val pagingConfig: PagingConfig by lazy {
        PagingConfig(PAGE_SIZE, enablePlaceholders = false)
    }

    fun setQuery(query: QueryPhotos) {
        _query.tryEmit(query)
    }

    companion object {
        private const val PAGE_SIZE: Int = 10
    }
}