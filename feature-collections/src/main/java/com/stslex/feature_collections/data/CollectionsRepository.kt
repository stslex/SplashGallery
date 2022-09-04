package com.stslex.feature_collections.data

import androidx.paging.PagingSource
import com.stslex.core_model.data.collection.CollectionModel
import javax.inject.Inject

class CollectionsRepository @Inject constructor(
    private val collectionsPagingSourceFactory: CollectionsPagingSource.Factory
) {

    fun queryAll(query: QueryCollections): PagingSource<Int, CollectionModel> {
        return collectionsPagingSourceFactory.create(query)
    }
}