package com.stslex.splashgallery.data.repository

import com.stslex.splashgallery.data.data_source.RemoteSourceImpl
import com.stslex.splashgallery.data.model.domain.PagesCollectionModel
import com.stslex.splashgallery.data.model.domain.PagesModel
import com.stslex.splashgallery.data.model.domain.title.TopicsModel
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ImageRepositoryImpl(private val remoteSource: RemoteSourceImpl) :
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

    override suspend fun getTopics(): Result<List<TopicsModel>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                when (val response = remoteSource.getTopics()) {
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

    override suspend fun getSingleTopic(t_id: String, pageNumber: Int): Result<PagesModel> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                when (val response = remoteSource.getSingleTopic(t_id, pageNumber)) {
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
