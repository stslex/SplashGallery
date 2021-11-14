package com.stslex.splashgallery.data.user

import com.stslex.splashgallery.data.core.FirebaseConstants.API_KEY
import com.stslex.splashgallery.data.core.FirebaseConstants.GET_USERS
import com.stslex.splashgallery.data.core.FirebaseConstants.QUERY_API_KEY
import com.stslex.splashgallery.data.model.user.RemoteUserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("$GET_USERS/{username}")
    suspend fun getUser(
        @Path("username") username: String,
        @Query(QUERY_API_KEY) api_key: String = API_KEY
    ): Response<RemoteUserModel>
}