package com.stslex.splashgallery.data.collections

import androidx.paging.PagingSource
import com.stslex.splashgallery.data.model.data.collection.CollectionModel
import javax.inject.Inject

class CollectionsRepository @Inject constructor(
    private val collectionsPagingSourceFactory: CollectionsPagingSource.Factory
) {

    fun queryAll(query: QueryCollections): PagingSource<Int, CollectionModel> {
        return collectionsPagingSourceFactory.create(query)
    }
}