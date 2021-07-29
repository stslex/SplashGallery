package com.stslex.splashgallery.data.data_source

import com.stslex.splashgallery.data.data_source.retrofit.RetrofitService
import com.stslex.splashgallery.data.model.*
import com.stslex.splashgallery.data.model.domain.PagesCollectionModel
import com.stslex.splashgallery.data.model.domain.PagesModel
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.model.remote.RemoteCollectionModel
import com.stslex.splashgallery.data.model.remote.RemoteImageModel
import com.stslex.splashgallery.mapper.CollectionMapper
import com.stslex.splashgallery.mapper.ImageMapper
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteSourceImpl @Inject constructor(private val retrofitService: RetrofitService) :
    RemoteSource {

    override suspend fun getAllPhotos(pageNumber: Int): Result<PagesModel> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val result = retrofitService.getAllPhotos(pageNumber, API_KEY_SUCCESS)
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

    override suspend fun getAllCollections(pageNumber: Int): Result<PagesCollectionModel> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val result = retrofitService.getAllCollections(pageNumber, API_KEY_SUCCESS)
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

    override suspend fun getCollectionPhotos(id: String, pageNumber: Int): Result<PagesModel> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val result = retrofitService.getCollectionPhotos(id, pageNumber, API_KEY_SUCCESS)
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

    override suspend fun getCurrentPhoto(id: String): Result<ImageModel> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val result = retrofitService.getCurrentPhoto(id, API_KEY_SUCCESS)
                if (result.isSuccessful && result.body() != null) {
                    val mapper = ImageMapper()
                    val remoteImage = result.body() as RemoteImageModel
                    val image = mapper.transformToDomain(remoteImage)
                    Result.Success(image)
                } else {
                    Result.Failure("Null result")
                }
            } catch (exception: Exception) {
                Result.Failure(exception.toString())
            }
        }

}