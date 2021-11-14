package com.stslex.splashgallery.data.collections

import com.stslex.splashgallery.data.core.FirebaseConstants.API_KEY
import com.stslex.splashgallery.data.core.FirebaseConstants.GET_COLLECTIONS
import com.stslex.splashgallery.data.core.FirebaseConstants.GET_USERS
import com.stslex.splashgallery.data.core.FirebaseConstants.QUERY_API_KEY
import com.stslex.splashgallery.data.core.FirebaseConstants.QUERY_PAGE
import com.stslex.splashgallery.data.model.collection.RemoteCollectionModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CollectionService {

    @GET(GET_COLLECTIONS)
    suspend fun getCollections(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String = API_KEY
    ): Response<List<RemoteCollectionModel>>

    @GET("$GET_USERS/{username}/$GET_COLLECTIONS")
    suspend fun getCollections(
        @Path("username") username: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String = API_KEY
    ): Response<List<RemoteCollectionModel>>
}