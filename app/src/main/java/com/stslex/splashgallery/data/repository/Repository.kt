package com.stslex.splashgallery.data.repository

import com.stslex.splashgallery.data.data_source.RemoteSource
import com.stslex.splashgallery.data.model.PagesModel
import com.stslex.splashgallery.data.model.title.TopicsModel
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class Repository(
    private val remoteSource: RemoteSource,
    private val ioDispatcher: CoroutineDispatcher
) : RepositoryInterface {
    override suspend fun getAllPhotos(pageNumber: Int): Result<PagesModel> =
        withContext(ioDispatcher) {
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
        withContext(ioDispatcher) {
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
        withContext(ioDispatcher) {
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

}
