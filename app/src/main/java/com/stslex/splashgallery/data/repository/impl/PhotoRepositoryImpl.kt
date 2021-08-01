package com.stslex.splashgallery.data.repository.impl

import com.stslex.splashgallery.data.data_source.interf.PhotoSource
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.repository.interf.PhotoRepository
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val photoSource: PhotoSource) :
    PhotoRepository {
    override suspend fun getAllPhotos(pageNumber: Int): Result<List<ImageModel>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                when (val response = photoSource.getAllPhotos(pageNumber)) {
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

    override suspend fun getCurrentPhoto(id: String): Result<ImageModel> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                when (val response = photoSource.getCurrentPhoto(id)) {
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