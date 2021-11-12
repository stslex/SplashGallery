package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.data.model.download.RemoteDownloadModel
import com.stslex.splashgallery.data.model.image.RemoteImageModel
import com.stslex.splashgallery.utils.GET_DOWNLOAD
import com.stslex.splashgallery.utils.GET_PHOTOS
import com.stslex.splashgallery.utils.QUERY_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotoService {

    @GET("/$GET_PHOTOS/{id}")
    suspend fun getCurrentPhoto(
        @Path("id") id: String,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<RemoteImageModel>

    @GET("/$GET_PHOTOS/{id}/$GET_DOWNLOAD")
    suspend fun downloadPhoto(
        @Path("id") id: String,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<RemoteDownloadModel>
}