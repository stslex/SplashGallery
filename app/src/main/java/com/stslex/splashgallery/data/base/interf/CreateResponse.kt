package com.stslex.splashgallery.data.base.interf

import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CreateResponse<E, D> {
    suspend fun create(response: Response<List<E>>): Flow<Result<List<D>>>
    suspend fun createSingle(response: Response<E>): Flow<Result<D>>
}