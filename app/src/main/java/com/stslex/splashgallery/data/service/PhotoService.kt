package com.stslex.splashgallery.data.service

import com.stslex.splashgallery.data.model.remote.RemoteImageModel
import com.stslex.splashgallery.utils.GET_PHOTOS
import com.stslex.splashgallery.utils.QUERY_API_KEY
import com.stslex.splashgallery.utils.QUERY_PAGE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoService {
    @GET("/$GET_PHOTOS")
    suspend fun getAllPhotos(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteImageModel>>

    @GET("/$GET_PHOTOS/{id}")
    suspend fun getCurrentPhoto(
        @Path("id") id: String,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<RemoteImageModel>
}