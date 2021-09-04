package com.stslex.splashgallery.data.service

import com.stslex.splashgallery.data.model.remote.RemoteUserModel
import com.stslex.splashgallery.utils.GET_USERS
import com.stslex.splashgallery.utils.QUERY_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("/$GET_USERS/{username}")
    suspend fun getUser(
        @Path("username") username: String,
        @Query(QUERY_API_KEY) api_key: String
    ): Response<RemoteUserModel>
}