package com.stslex.splashgallery.data.photos

import com.stslex.splashgallery.data.model.image.RemoteImageModel
import com.stslex.splashgallery.utils.QUERY_API_KEY
import com.stslex.splashgallery.utils.QUERY_PAGE
import com.stslex.splashgallery.utils.QUERY_PAGE_SIZE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotosService {

    @GET("{query1}/{query2}/{query3}")
    suspend fun getPhotos(
        @Path("query1") query1: String,
        @Path("query2") query2: String,
        @Path("query3") query3: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteImageModel>>

    @GET("{q}")
    suspend fun getPhotos(
        @Path("q") query: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<List<RemoteImageModel>>
}