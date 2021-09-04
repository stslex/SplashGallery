package com.stslex.splashgallery.data.repository.impl

import com.stslex.splashgallery.data.base.interf.CreateResponse
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.data.model.remote.RemoteCollectionModel
import com.stslex.splashgallery.data.repository.interf.CollectionRepository
import com.stslex.splashgallery.data.service.CollectionService
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CollectionRepositoryImpl @Inject constructor(
    private val service: CollectionService,
    private val response: CreateResponse<RemoteCollectionModel, CollectionModel>
) : CollectionRepository {

    override suspend fun getAllCollections(page: Int): Flow<Result<List<CollectionModel>>> =
        response.create(service.getAllCollections(page, API_KEY_SUCCESS))

    override suspend fun getUserCollections(
        username: String,
        page: Int
    ): Flow<Result<List<CollectionModel>>> =
        response.create(service.getUserCollections(username, page, API_KEY_SUCCESS))

}