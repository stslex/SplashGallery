package com.stslex.splashgallery.data.data_source

import com.stslex.splashgallery.data.data_source.retrofit.RetrofitClient
import com.stslex.splashgallery.data.data_source.retrofit.RetrofitService
import com.stslex.splashgallery.data.model.*
import com.stslex.splashgallery.data.model.image.RemoteImageModel
import com.stslex.splashgallery.data.model.title.RemoteTopicsModel
import com.stslex.splashgallery.data.model.title.TopicsModel
import com.stslex.splashgallery.mapper.ImageMapper
import com.stslex.splashgallery.mapper.TopicsMapper
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import com.stslex.splashgallery.utils.BASE_URL
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RemoteSource(private val ioDispatcher: CoroutineDispatcher) : RemoteSourceInterface {

    private val client: RetrofitService = RetrofitClient().getClient(BASE_URL)
        .create(RetrofitService::class.java)

    override suspend fun getAllPhotos(pageNumber: Int): Result<PagesModel> =
        withContext(ioDispatcher) {
            return@withContext try {
                val result = client.getAllPhotos(pageNumber, API_KEY_SUCCESS)
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

    override suspend fun getTopics(): Result<List<TopicsModel>> =
        withContext(ioDispatcher) {
            return@withContext try {
                val result = client.getTopics(API_KEY_SUCCESS)
                if (result.isSuccessful && result.body() != null) {
                    val mapper = TopicsMapper()
                    val listOfRemoteTopics = result.body() as List<RemoteTopicsModel>
                    val listOfTopics = listOfRemoteTopics.map {
                        mapper.transformToDomain(it)
                    }
                    Result.Success(listOfTopics)
                } else {
                    Result.Failure("Null request")
                }
            } catch (exception: Exception) {
                Result.Failure(exception.toString())
            }
        }

    override suspend fun getSingleTopic(t_id: String, pageNumber: Int): Result<PagesModel> =
        withContext(ioDispatcher) {
            return@withContext try {
                val result = client.getSingleTopic(t_id, pageNumber, API_KEY_SUCCESS)
                if (result.isSuccessful && result.body() != null) {
                    val mapper = ImageMapper()
                    val listOfRemoteImages = result.body() as List<RemoteImageModel>
                    val listOfImages = listOfRemoteImages.map {
                        mapper.transformToDomain(it)
                    }
                    val page = PagesModel(listOfImages)
                    Result.Success(page)
                } else {
                    Result.Failure("Null request")
                }
            } catch (exception: Exception) {
                Result.Failure(exception.toString())
            }
        }
}