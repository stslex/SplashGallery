package com.stslex.splashgallery.data.repository

import com.stslex.splashgallery.data.data_source.RemoteSource
import com.stslex.splashgallery.data.model.PagesModel
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class Repository(
    private val remoteSource: RemoteSource,
    private val ioDispatcher: CoroutineDispatcher
) : RepositoryInterface {
    override suspend fun getPageFromRetrofit(pageNumber: Int): Result<PagesModel> =
        withContext(ioDispatcher) {
            return@withContext try {
                val response = remoteSource.getResult(pageNumber)
                when (response) {
                    is Result.Success -> {
                        val page = response.data
                        Result.Success(page)
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