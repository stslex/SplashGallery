package com.stslex.splashgallery.data.repository.impl

import android.util.Log
import com.stslex.splashgallery.data.data_source.interf.CollectionSource
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.repository.interf.CollectionRepository
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CollectionRepositoryImpl @Inject constructor(private val collectionSource: CollectionSource) :
    CollectionRepository {
    override suspend fun getAllCollections(pageNumber: Int): Result<List<CollectionModel>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                when (val response = collectionSource.getAllCollections(pageNumber)) {
                    is Result.Success -> {
                        Log.i("Collection::repo", response.data.toString())
                        Result.Success(response.data)
                    }
                    is Result.Failure -> {
                        Log.i("Collection::repo:e", response.exception)
                        Result.Failure(response.exception)
                    }
                    else -> {
                        Result.Loading
                    }
                }
            } catch (exception: Exception) {
                Log.i("Collection::repo:ex", exception.toString())
                Result.Failure(exception.toString())
            }
        }

    override suspend fun getCollectionPhotos(
        id: String,
        pageNumber: Int
    ): Result<List<ImageModel>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                when (val response = collectionSource.getCollectionPhotos(id, pageNumber)) {
                    is Result.Success -> {
                        Result.Success(response.data)
                    }
                    is Result.Failure -> {
                        Result.Failure(response.exception)
                    }
                    else -> {
                        Result.Loading
                    }
                }
            } catch (exception: Exception) {
                Result.Failure(exception.toString())
            }
        }
}