package com.stslex.splashgallery.data.service

import com.stslex.splashgallery.data.model.remote.RemoteCollectionModel
import com.stslex.splashgallery.data.model.remote.RemoteImageModel
import com.stslex.splashgallery.data.model.remote.RemoteUserModel
import com.stslex.splashgallery.utils.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("/$GET_USERS/{username}")
    suspend fun getUserInfo(
        @Path("username") username: String,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<RemoteUserModel>

    @GET("$GET_USERS/{username}/$GET_PHOTOS")
    suspend fun getUserContentPhotos(
        @Path("username") username: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteImageModel>>

    @GET("$GET_USERS/{username}/$GET_LIKES")
    suspend fun getUserContentLikes(
        @Path("username") username: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteImageModel>>

    @GET("$GET_USERS/{username}/$GET_COLLECTIONS")
    suspend fun getUserContentCollections(
        @Path("username") username: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteCollectionModel>>
}