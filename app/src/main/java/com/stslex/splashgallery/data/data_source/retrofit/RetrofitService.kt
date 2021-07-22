package com.stslex.splashgallery.data.data_source.retrofit

import com.stslex.splashgallery.data.model.remote.RemoteCollectionModel
import com.stslex.splashgallery.data.model.remote.RemoteImageModel
import com.stslex.splashgallery.data.model.remote.RemoteTopicsModel
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

    @GET("/$GET_TOPICS")
    suspend fun getTopics(
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteTopicsModel>>

    @GET("/$GET_TOPICS/{t_id}/$GET_PHOTOS")
    suspend fun getSingleTopic(
        @Path("t_id") t_id: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteImageModel>>

    @GET("/$GET_COLLECTIONS")
    suspend fun getAllCollections(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteCollectionModel>>

}