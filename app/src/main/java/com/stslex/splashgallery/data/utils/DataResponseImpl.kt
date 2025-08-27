package com.stslex.splashgallery.data.utils

import com.stslex.splashgallery.core.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject

class DataResponseImpl @Inject constructor() : DataResponse {

    override fun <T> create(response: Response<T>): Flow<Resource<T>> = flow {
        emit(createRow(response))
    }

    override fun <T> createRow(response: Response<T>): Resource<T> = try {
        if (response.isSuccess) Resource.Success(response.body()!!)
        else Resource.Failure<Nothing>(HttpException(response))
    } catch (exception: Exception) {
        Resource.Failure(exception)
    }

    private val <T> Response<T>.isSuccess: Boolean
        get() = isSuccessful && body() != null
}