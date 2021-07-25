package com.stslex.splashgallery.data.data_source

import com.stslex.splashgallery.data.data_source.retrofit.RetrofitService
import com.stslex.splashgallery.data.model.*
import com.stslex.splashgallery.data.model.domain.PagesCollectionModel
import com.stslex.splashgallery.data.model.domain.PagesModel
import com.stslex.splashgallery.data.model.domain.title.TopicsModel
import com.stslex.splashgallery.data.model.remote.RemoteCollectionModel
import com.stslex.splashgallery.data.model.remote.RemoteImageModel
import com.stslex.splashgallery.data.model.remote.RemoteTopicsModel
import com.stslex.splashgallery.mapper.CollectionMapper
import com.stslex.splashgallery.mapper.ImageMapper
import com.stslex.splashgallery.mapper.TopicsMapper
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteSourceImpl @Inject constructor(private val client: RetrofitService) : RemoteSource {

    override suspend fun getAllPhotos(pageNumber: Int): Result<PagesModel> =
        withContext(Dispatchers.IO) {
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
        withContext(Dispatchers.IO) {
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
        withContext(Dispatchers.IO) {
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

    override suspend fun getAllCollections(pageNumber: Int): Result<PagesCollectionModel> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val result = client.getAllCollections(pageNumber, API_KEY_SUCCESS)
                if (result.isSuccessful && result.body() != null) {
                    val mapper = CollectionMapper()
                    val listOfRemoteCollections = result.body() as List<RemoteCollectionModel>
                    val listOfCollections = listOfRemoteCollections.map {
                        mapper.transformToDomain(it)
                    }
                    val page = PagesCollectionModel(listOfCollections)
                    Result.Success(page)
                } else {
                    Result.Failure("Null request")
                }
            } catch (exception: Exception) {
                Result.Failure(exception.toString())
            }
        }
}