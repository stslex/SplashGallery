package com.stslex.splashgallery.data.data_source

import com.stslex.splashgallery.data.data_source.retrofit.RetrofitClient
import com.stslex.splashgallery.data.data_source.retrofit.RetrofitService
import com.stslex.splashgallery.data.model.*
import com.stslex.splashgallery.mapper.ImageMapper
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import com.stslex.splashgallery.utils.BASE_URL
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RemoteSource(private val ioDispatcher: CoroutineDispatcher) : RemoteSourceInterface {
    override suspend fun getResult(pageNumber: Int): Result<PagesModel> =
        withContext(ioDispatcher) {
            return@withContext try {
                val client: RetrofitService = RetrofitClient().getClient(BASE_URL)
                    .create(RetrofitService::class.java)
                val result = client.getPage(pageNumber, API_KEY_SUCCESS)
                if (result.isSuccessful && result.body() != null) {
                    val mapper = ImageMapper()
                    val listOfRemoteImages = result.body() as List<RemoteImageModel>
                    val listOfImages = listOfRemoteImages.map {
                        mapper.transformToDomain(it)
                    }
                    val page = PagesModel(listOfImages)
                    Result.Success(page)
                } else {
                    Result.Failure("Null result")
                }
            } catch (exception: Exception) {
                Result.Failure(exception.toString())
            }
        }
}