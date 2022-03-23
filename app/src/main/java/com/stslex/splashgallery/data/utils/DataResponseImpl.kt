package com.stslex.splashgallery.data.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class DataResponseImpl @Inject constructor() : DataResponse {

    override fun <T> create(response: Response<T>): Flow<com.stslex.core.Resource<T>> = flow {
        val result = try {
            if (response.isSuccess) com.stslex.core.Resource.Success(response.body()!!)
            else com.stslex.core.Resource.Failure<Nothing>(HttpException(response))
        } catch (exception: Exception) {
            com.stslex.core.Resource.Failure(exception)
        }
        emit(result)
    }

    private val <T> Response<T>.isSuccess: Boolean
        get() = isSuccessful && body() != null
}