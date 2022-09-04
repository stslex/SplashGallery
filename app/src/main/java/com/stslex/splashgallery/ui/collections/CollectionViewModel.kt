package com.stslex.splashgallery.ui.collections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.stslex.splashgallery.data.collections.QueryCollections
import com.stslex.splashgallery.data.model.data.collection.CollectionModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Provider

class CollectionViewModel @Inject constructor(
    private val queryCollectionsUseCaseProvider: Provider<QueryCollectionsUseCase>
) : ViewModel() {

    private val _query: MutableStateFlow<QueryCollections> =
        MutableStateFlow(QueryCollections.EmptyQuery)
    val query: StateFlow<QueryCollections> = _query.asStateFlow()

    private var newPagingSource: PagingSource<*, *>? = null

    private val pagingConfig: PagingConfig by lazy {
        PagingConfig(PAGE_SIZE, enablePlaceholders = false)
    }

    @ExperimentalCoroutinesApi
    val collections: StateFlow<PagingData<CollectionModel>> = query
        .map(::newPager)
        .flatMapLatest { pager -> pager.flow }
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private fun newPager(query: QueryCollections): Pager<Int, CollectionModel> {
        return Pager(pagingConfig) {
            newPagingSource?.invalidate()
            val queryPhotosUseCase = queryCollectionsUseCaseProvider.get()
            queryPhotosUseCase(query).also { newPagingSource = it }
        }
    }

    fun setQuery(query: QueryCollections) {
        _query.tryEmit(query)
    }

    companion object {
        private const val PAGE_SIZE: Int = 10
    }
}