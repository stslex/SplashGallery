package com.stslex.splashgallery.data.repository.impl

import com.stslex.splashgallery.data.data_source.interf.UserSource
import com.stslex.splashgallery.data.model.domain.collection.CollectionModel
import com.stslex.splashgallery.data.model.domain.image.ImageModel
import com.stslex.splashgallery.data.model.domain.user.UserModel
import com.stslex.splashgallery.data.model.remote.RemoteCollectionModel
import com.stslex.splashgallery.data.model.remote.RemoteImageModel
import com.stslex.splashgallery.data.repository.interf.UserRepository
import com.stslex.splashgallery.mapper.CollectionMapper
import com.stslex.splashgallery.mapper.ImageMapper
import com.stslex.splashgallery.utils.GET_COLLECTIONS
import com.stslex.splashgallery.utils.GET_LIKES
import com.stslex.splashgallery.utils.GET_PHOTOS
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val source: UserSource) : UserRepository {
    override suspend fun getUserInfo(username: String): Result<UserModel> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                when (val response = source.getUserInfo(username)) {
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

    override suspend fun getUserContentLikes(
        username: String,
        page: Int
    ): Result<List<ImageModel>> = withContext(Dispatchers.IO) {
        return@withContext try {
            when (val response = source.getUserContent<List<RemoteImageModel>>(
                username = username,
                page = page,
                content = GET_LIKES
            )) {
                is Result.Success -> {
                    val listOfImages: List<ImageModel> = response.data?.map {
                        ImageMapper().transformToDomain(it)
                    } as List<ImageModel>
                    Result.Success(listOfImages)
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

    override suspend fun getUserContentPhotos(
        username: String,
        page: Int
    ): Result<List<ImageModel>> = withContext(Dispatchers.IO) {
        return@withContext try {
            when (val response = source.getUserContent<List<RemoteImageModel>>(
                username = username,
                page = page,
                content = GET_PHOTOS
            )) {
                is Result.Success -> {
                    val listOfImages: List<ImageModel> = response.data?.map {
                        ImageMapper().transformToDomain(it)
                    } as List<ImageModel>
                    Result.Success(listOfImages)
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

    override suspend fun getUserContentCollections(
        username: String,
        page: Int
    ): Result<List<CollectionModel>> = withContext(Dispatchers.IO) {
        return@withContext try {
            when (val response = source.getUserContent<List<RemoteCollectionModel>>(
                username = username,
                page = page,
                content = GET_COLLECTIONS
            )) {
                is Result.Success -> {
                    val listOfImages: List<CollectionModel> = response.data?.map {
                        CollectionMapper().transformToDomain(it)
                    } as List<CollectionModel>
                    Result.Success(listOfImages)
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