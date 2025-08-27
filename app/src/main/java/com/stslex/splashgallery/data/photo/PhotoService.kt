package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.data.core.FirebaseConstants.API_KEY
import com.stslex.splashgallery.data.core.FirebaseConstants.GET_DOWNLOAD
import com.stslex.splashgallery.data.core.FirebaseConstants.GET_PHOTOS
import com.stslex.splashgallery.data.core.FirebaseConstants.QUERY_API_KEY
import com.stslex.splashgallery.core_model.response.download.RemoteDownloadModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import st.slex.csplashscreen.data.model.remote.image.RemoteImageModel

interface PhotoService {

    @GET("$GET_PHOTOS/{id}")
    suspend fun getCurrentPhoto(
        @Path("id") id: String,
        @Query(QUERY_API_KEY) api_key: String = API_KEY
    ): Response<RemoteImageModel>

    @GET("$GET_PHOTOS/{id}/$GET_DOWNLOAD")
    suspend fun downloadPhoto(
        @Path("id") id: String,
        @Query(QUERY_API_KEY) api_key: String = API_KEY
    ): Response<RemoteDownloadModel>
}