package com.stslex.splashgallery.data.photos

import com.stslex.splashgallery.data.core.FirebaseConstants.API_KEY
import com.stslex.splashgallery.data.core.FirebaseConstants.GET_COLLECTIONS
import com.stslex.splashgallery.data.core.FirebaseConstants.GET_LIKES
import com.stslex.splashgallery.data.core.FirebaseConstants.GET_PHOTOS
import com.stslex.splashgallery.data.core.FirebaseConstants.GET_USERS
import com.stslex.splashgallery.data.core.FirebaseConstants.QUERY_API_KEY
import com.stslex.splashgallery.data.core.FirebaseConstants.QUERY_PAGE
import com.stslex.splashgallery.data.core.FirebaseConstants.QUERY_PAGE_SIZE
import com.stslex.splashgallery.data.model.image.RemoteImageModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PhotosService {

    @GET("$GET_COLLECTIONS/{query}/$GET_PHOTOS")
    suspend fun getPhotos(
        @Path("query") query: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String = API_KEY
    ): Response<List<RemoteImageModel>>

    @GET(GET_PHOTOS)
    suspend fun getPhotos(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String = API_KEY
    ): Response<List<RemoteImageModel>>

    @GET("$GET_USERS/{username}/$GET_PHOTOS")
    suspend fun getUserPhotos(
        @Path("username") username: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String = API_KEY
    ): Response<List<RemoteImageModel>>

    @GET("$GET_USERS/{username}/$GET_LIKES")
    suspend fun getUserLikes(
        @Path("username") username: String,
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_PAGE_SIZE) page_size: Int,
        @Query(QUERY_API_KEY) api_key: String = API_KEY
    ): Response<List<RemoteImageModel>>
}