package com.stslex.feature_collections.ui

import androidx.paging.PagingSource
import com.stslex.core_model.data.collection.CollectionModel
import com.stslex.feature_collections.data.CollectionsRepository
import com.stslex.feature_collections.data.QueryCollections
import javax.inject.Inject

class QueryCollectionsUseCase @Inject constructor(
    private val repository: CollectionsRepository
) {

    operator fun invoke(query: QueryCollections): PagingSource<Int, CollectionModel> {
        return repository.queryAll(query)
    }
}