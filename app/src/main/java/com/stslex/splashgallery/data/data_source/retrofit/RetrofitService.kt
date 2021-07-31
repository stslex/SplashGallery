package com.stslex.splashgallery.data.data_source.retrofit

import com.stslex.splashgallery.data.model.remote.RemoteCollectionModel
import com.stslex.splashgallery.data.model.remote.RemoteImageModel
import com.stslex.splashgallery.data.model.remote.RemoteUserModel
import com.stslex.splashgallery.utils.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {

    @GET("/$GET_PHOTOS")
    suspend fun getAllPhotos(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteImageModel>>

    @GET("/$GET_COLLECTIONS")
    suspend fun getAllCollections(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteCollectionModel>>

    @GET("/$GET_COLLECTIONS/{id}/$GET_PHOTOS")
    suspend fun getCollectionPhotos(
        @Path("id") id: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteImageModel>>

    @GET("/$GET_PHOTOS/{id}")
    suspend fun getCurrentPhoto(
        @Path("id") id: String,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<RemoteImageModel>

    @GET("/$GET_USERS/{username}")
    suspend fun getUserInfo(
        @Path("username") username: String,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<RemoteUserModel>
}