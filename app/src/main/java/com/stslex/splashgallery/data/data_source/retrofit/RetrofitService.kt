package com.stslex.splashgallery.data.data_source.retrofit

import com.stslex.splashgallery.data.model.RemotePagesModel
import com.stslex.splashgallery.utils.GET_PHOTOS
import com.stslex.splashgallery.utils.QUERY_API_KEY
import com.stslex.splashgallery.utils.QUERY_PAGE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("/$GET_PHOTOS")
    suspend fun getPage(
        @Query(QUERY_PAGE) page: Int,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<RemotePagesModel>
}