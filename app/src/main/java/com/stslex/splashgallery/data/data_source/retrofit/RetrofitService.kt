package com.stslex.splashgallery.data.data_source.retrofit

import com.stslex.splashgallery.data.model.image.RemoteImageModel
import com.stslex.splashgallery.data.model.title.RemoteTopicsModel
import com.stslex.splashgallery.utils.GET_PHOTOS
import com.stslex.splashgallery.utils.GET_TOPICS
import com.stslex.splashgallery.utils.QUERY_API_KEY
import com.stslex.splashgallery.utils.QUERY_PAGE
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

    @GET("/$GET_TOPICS")
    suspend fun getTopics(
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteTopicsModel>>

    @GET("/$GET_TOPICS/:{t_id}/$GET_PHOTOS")
    suspend fun getSingleTopic(
        @Path("t_id") t_id: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteImageModel>>
}