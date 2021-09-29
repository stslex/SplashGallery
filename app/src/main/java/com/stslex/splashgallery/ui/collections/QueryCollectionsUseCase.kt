package com.stslex.splashgallery.ui.collections

import androidx.paging.PagingSource
import com.stslex.splashgallery.data.collections.CollectionsRepository
import com.stslex.splashgallery.ui.model.collection.CollectionModel
import javax.inject.Inject

class QueryCollectionsUseCase @Inject constructor(
    private val repository: CollectionsRepository
) {

    operator fun invoke(query: List<String>): PagingSource<Int, CollectionModel> {
        return repository.queryAll(query)
    }
}