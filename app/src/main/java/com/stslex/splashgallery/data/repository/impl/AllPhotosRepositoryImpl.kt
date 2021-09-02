package com.stslex.splashgallery.data.repository.impl

import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.model.remote.RemoteImageModel
import com.stslex.splashgallery.data.repository.interf.AllPhotosRepository
import com.stslex.splashgallery.data.service.AllPhotosService
import com.stslex.splashgallery.mapper.ImageMapper
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AllPhotosRepositoryImpl @Inject constructor(private val service: AllPhotosService) :
    AllPhotosRepository {

    override suspend fun getAllPhotos(page: Int): Flow<Result<List<ImageModel>>> =
        callbackFlow {
            val response = service.getAllPhotos(page, API_KEY_SUCCESS)
            response.event({
                trySendBlocking(Result.Success(it))
            }, {
                trySendBlocking(Result.Failure(it))
            })
            awaitClose { }
        }


    override suspend fun getUserPhotos(
        username: String,
        page: Int
    ): Flow<Result<List<ImageModel>>> = callbackFlow {
        val response = service.getUserPhotos(username, page, API_KEY_SUCCESS)
        response.event({
            trySendBlocking(Result.Success(it))
        }, {
            trySendBlocking(Result.Failure(it))
        })
        awaitClose { }
    }

    override suspend fun getUserLikes(username: String, page: Int): Flow<Result<List<ImageModel>>> =
        callbackFlow {
            val response = service.getUserLikes(username, page, API_KEY_SUCCESS)
            response.event({
                trySendBlocking(Result.Success(it))
            }, {
                trySendBlocking(Result.Failure(it))
            })
            awaitClose { }
        }

    override suspend fun getCollectionPhotos(
        id: String,
        page: Int
    ): Flow<Result<List<ImageModel>>> =
        callbackFlow {
            val response = service.getCollectionPhotos(id, page, API_KEY_SUCCESS)
            response.event({
                trySendBlocking(Result.Success(it))
            }, {
                trySendBlocking(Result.Failure(it))
            })
            awaitClose { }
        }

    private inline fun Response<List<RemoteImageModel>>.event(
        success: (List<ImageModel>) -> Unit,
        failure: (String) -> Unit
    ) = if (isSuccessful && !body().isNullOrEmpty()) {
        val list = body()?.map {
            ImageMapper().transformToDomain(it)
        } as List<ImageModel>
        success(list)
    } else {
        failure(toString())
    }
}