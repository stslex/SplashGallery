package com.stslex.splashgallery.data.repository

import android.util.Log
import com.stslex.splashgallery.data.data_source.RemoteSource
import com.stslex.splashgallery.data.model.domain.PagesCollectionModel
import com.stslex.splashgallery.data.model.domain.PagesModel
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(private val remoteSource: RemoteSource) :
    ImageRepository {

    override suspend fun getAllPhotos(pageNumber: Int): Result<PagesModel> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                when (val response = remoteSource.getAllPhotos(pageNumber)) {
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

    override suspend fun getAllCollections(pageNumber: Int): Result<PagesCollectionModel> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                when (val response = remoteSource.getAllCollections(pageNumber)) {
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

    override suspend fun getCollectionPhotos(id: String, pageNumber: Int): Result<PagesModel> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                when (val response = remoteSource.getCollectionPhotos(id, pageNumber)) {
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
