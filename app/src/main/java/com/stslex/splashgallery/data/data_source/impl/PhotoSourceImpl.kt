package com.stslex.splashgallery.data.data_source.impl

import com.stslex.splashgallery.data.data_source.interf.PhotoSource
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.model.remote.RemoteImageModel
import com.stslex.splashgallery.data.service.PhotoService
import com.stslex.splashgallery.mapper.ImageMapper
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotoSourceImpl @Inject constructor(private val photoService: PhotoService) : PhotoSource {

    override suspend fun getAllPhotos(pageNumber: Int): Flow<Result<List<ImageModel>>> =
        callbackFlow {
            val result = photoService.getAllPhotos(pageNumber, API_KEY_SUCCESS)
            if (result.isSuccessful && result.body() != null) {
                val mapper = ImageMapper()
                val listOfRemoteImages = result.body() as List<RemoteImageModel>
                val listOfImages = listOfRemoteImages.map {
                    mapper.transformToDomain(it)
                }
                trySendBlocking(Result.Success(listOfImages))
            } else {
                trySendBlocking(Result.Failure("Null result"))
            }
            awaitClose { result }
        }

    override suspend fun getCurrentPhoto(id: String): Result<ImageModel> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val result = photoService.getCurrentPhoto(id, API_KEY_SUCCESS)
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