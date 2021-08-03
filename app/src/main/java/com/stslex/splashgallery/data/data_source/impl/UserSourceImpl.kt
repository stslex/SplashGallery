package com.stslex.splashgallery.data.data_source.impl

import com.stslex.splashgallery.data.data_source.interf.UserSource
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.model.domain.user.UserModel
import com.stslex.splashgallery.data.model.remote.RemoteCollectionModel
import com.stslex.splashgallery.data.model.remote.RemoteImageModel
import com.stslex.splashgallery.data.model.remote.RemoteUserModel
import com.stslex.splashgallery.data.service.UserService
import com.stslex.splashgallery.mapper.CollectionMapper
import com.stslex.splashgallery.mapper.ImageMapper
import com.stslex.splashgallery.mapper.UserMapper
import com.stslex.splashgallery.utils.API_KEY_SUCCESS
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserSourceImpl @Inject constructor(private val userService: UserService) : UserSource {
    override suspend fun getUserInfo(username: String): Result<UserModel> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val result = userService.getUserInfo(username, API_KEY_SUCCESS)
                if (result.isSuccessful && result.body() != null) {
                    val mapper = UserMapper()
                    val remoteUser = result.body() as RemoteUserModel
                    val user = mapper.transformToDomain(remoteUser)
                    Result.Success(user)
                } else {
                    Result.Failure("Null result")
                }
            } catch (exception: Exception) {
                Result.Failure(exception.toString())
            }
        }

    override suspend fun getUserContentPhotos(
        username: String,
        page: Int
    ): Result<List<ImageModel>> = withContext(Dispatchers.IO) {
        return@withContext try {
            val result = userService.getUserContentPhotos(username, page, API_KEY_SUCCESS)
            if (result.isSuccessful && result.body() != null) {
                val mapper = ImageMapper()
                val remotePhotos = result.body() as List<RemoteImageModel>
                val photos = remotePhotos.map {
                    mapper.transformToDomain(it)
                }
                Result.Success(photos)
            } else {
                Result.Failure("Null result")
            }
        } catch (exception: Exception) {
            Result.Failure(exception.toString())
        }
    }

    override suspend fun getUserContentLikes(
        username: String,
        page: Int
    ): Result<List<ImageModel>> = withContext(Dispatchers.IO) {
        return@withContext try {
            val result = userService.getUserContentLikes(username, page, API_KEY_SUCCESS)
            if (result.isSuccessful && result.body() != null) {
                val mapper = ImageMapper()
                val remotePhotos = result.body() as List<RemoteImageModel>
                val photos = remotePhotos.map {
                    mapper.transformToDomain(it)
                }
                Result.Success(photos)
            } else {
                Result.Failure("Null result")
            }
        } catch (exception: Exception) {
            Result.Failure(exception.toString())
        }
    }

    override suspend fun getUserContentCollections(
        username: String,
        page: Int
    ): Result<List<CollectionModel>> = withContext(Dispatchers.IO) {
        return@withContext try {
            val result = userService.getUserContentCollections(username, page, API_KEY_SUCCESS)
            if (result.isSuccessful && result.body() != null) {
                val mapper = CollectionMapper()
                val remoteCollections = result.body() as List<RemoteCollectionModel>
                val collections = remoteCollections.map {
                    mapper.transformToDomain(it)
                }
                Result.Success(collections)
            } else {
                Result.Failure("Null result")
            }
        } catch (exception: Exception) {
            Result.Failure(exception.toString())
        }
    }

}