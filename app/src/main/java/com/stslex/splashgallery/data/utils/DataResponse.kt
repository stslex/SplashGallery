package com.stslex.splashgallery.data.utils

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface DataResponse {

    fun <T> create(response: Response<T>): Flow<com.stslex.core.Resource<T>>
}