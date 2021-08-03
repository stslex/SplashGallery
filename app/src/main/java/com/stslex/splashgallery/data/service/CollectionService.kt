package com.stslex.splashgallery.data.service

import com.stslex.splashgallery.data.model.remote.RemoteCollectionModel
import com.stslex.splashgallery.data.model.remote.RemoteImageModel
import com.stslex.splashgallery.utils.GET_COLLECTIONS
import com.stslex.splashgallery.utils.GET_PHOTOS
import com.stslex.splashgallery.utils.QUERY_API_KEY
import com.stslex.splashgallery.utils.QUERY_PAGE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CollectionService {
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
}