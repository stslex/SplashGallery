package com.stslex.splashgallery.data.photos

import com.stslex.splashgallery.utils.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AllPhotosService {
    @GET("/$GET_PHOTOS/")
    suspend fun getAllPhotos(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<PhotoData.Base>>

    @GET("$GET_USERS/{username}/$GET_PHOTOS")
    suspend fun getUserPhotos(
        @Path("username") username: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<PhotoData.Base>>

    @GET("$GET_USERS/{username}/$GET_LIKES")
    suspend fun getUserLikes(
        @Path("username") username: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<PhotoData.Base>>

    @GET("/$GET_COLLECTIONS/{id}/$GET_PHOTOS")
    suspend fun getCollectionPhotos(
        @Path("id") id: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<PhotoData.Base>>

}