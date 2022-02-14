package com.stslex.splashgallery.data.utils

import com.stslex.splashgallery.core.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface DataResponse {

    fun <T> create(response: Response<T>): Flow<Resource<T>>
}