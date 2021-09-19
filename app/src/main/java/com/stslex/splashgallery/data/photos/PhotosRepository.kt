package com.stslex.splashgallery.data.photos

import com.stslex.splashgallery.data.core.DataResult
import com.stslex.splashgallery.data.service.AllPhotosService
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import retrofit2.Response
import javax.inject.Inject

@ExperimentalCoroutinesApi
interface PhotosRepository {
    suspend fun getAllPhotos(page: Int): Flow<DataResult<List<PhotosData>>>
    suspend fun getUserPhotos(username: String, page: Int): Flow<DataResult<List<PhotosData>>>
    suspend fun getUserLikes(username: String, page: Int): Flow<DataResult<List<PhotosData>>>
    suspend fun getCollectionPhotos(id: String, page: Int): Flow<DataResult<List<PhotosData>>>

    class Base @Inject constructor(
        private val service: AllPhotosService
    ) : PhotosRepository {

        override suspend fun getAllPhotos(page: Int): Flow<DataResult<List<PhotosData>>> =
            create(service.getAllPhotos(page, API_KEY_SUCCESS))

        override suspend fun getUserPhotos(
            username: String,
            page: Int
        ): Flow<DataResult<List<PhotosData>>> =
            create(service.getUserPhotos(username, page, API_KEY_SUCCESS))

        override suspend fun getUserLikes(
            username: String,
            page: Int
        ): Flow<DataResult<List<PhotosData>>> =
            create(service.getUserLikes(username, page, API_KEY_SUCCESS))

        override suspend fun getCollectionPhotos(
            id: String,
            page: Int
        ): Flow<DataResult<List<PhotosData>>> =
            create(service.getCollectionPhotos(id, page, API_KEY_SUCCESS))

        private fun <T> create(response: Response<T>): Flow<DataResult<T>> = callbackFlow {
            response.responseEvent({
                trySendBlocking(DataResult.Success(it))
            }, {
                trySendBlocking(DataResult.Failure(it))
            })
            awaitClose { }
        }

        private inline fun <T> Response<T>.responseEvent(
            crossinline success: (T) -> Unit,
            crossinline failure: (String) -> Unit
        ) = try {
            if (isSuccessful && body() != null) {
                body()?.let {
                    success(it)
                }
            } else {
                failure(message().toString())
            }
        } catch (exception: Exception) {
            failure(exception.toString())
        }

    }


}