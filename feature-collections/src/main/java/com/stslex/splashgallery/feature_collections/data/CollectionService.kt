package com.stslex.splashgallery.feature_collections.data

import com.stslex.splashgallery.core_model.response.collection.RemoteCollectionModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CollectionService {

    @GET(FirebaseConstants.GET_COLLECTIONS)
    suspend fun getCollections(
        @Query(FirebaseConstants.QUERY_PAGE) page: Int,
        @Query(FirebaseConstants.QUERY_API_KEY) api_key: String = FirebaseConstants.API_KEY
    ): Response<List<RemoteCollectionModel>>

    @GET("${FirebaseConstants.GET_USERS}/{username}/${FirebaseConstants.GET_COLLECTIONS}")
    suspend fun getCollections(
        @Path("username") username: String,
        @Query(FirebaseConstants.QUERY_PAGE) page: Int,
        @Query(FirebaseConstants.QUERY_API_KEY) api_key: String = FirebaseConstants.API_KEY
    ): Response<List<RemoteCollectionModel>>
}