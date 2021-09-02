package com.stslex.splashgallery.data.data_source.impl

import com.stslex.splashgallery.data.data_source.interf.CollectionSource
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.data.model.remote.RemoteCollectionModel
import com.stslex.splashgallery.data.service.CollectionService
import com.stslex.splashgallery.mapper.CollectionMapper
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CollectionSourceImpl @Inject constructor(private val collectionService: CollectionService) :
    CollectionSource {
    override suspend fun getAllCollections(pageNumber: Int): Result<List<CollectionModel>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val result = collectionService.getAllCollections(pageNumber, API_KEY_SUCCESS)
                if (result.isSuccessful && result.body() != null) {
                    val mapper = CollectionMapper()
                    val listOfRemoteCollections = result.body() as List<RemoteCollectionModel>
                    val listOfCollections = listOfRemoteCollections.map {
                        mapper.transformToDomain(it)
                    }
                    Result.Success(listOfCollections)
                } else {
                    Result.Failure("Null request")
                }
            } catch (exception: Exception) {
                Result.Failure(exception.toString())
            }
        }
}