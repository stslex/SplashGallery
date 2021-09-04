package com.stslex.splashgallery.utils.base

import com.stslex.splashgallery.mapper.BaseMapper
import retrofit2.Response

class ResponseEvent<E, D>(
    val mapper: BaseMapper<E, D>,
    val success: (List<D>) -> Unit,
    val failure: (String) -> Unit
) {
    fun Response<List<E>>.event() = try {
        if (isSuccessful && !body().isNullOrEmpty()) {
            val list = body()?.map {
                mapper.transformToDomain(it)
            } as List<D>
            success(list)
        } else {
            failure(message())
        }
    } catch (exception: Exception) {
        failure(exception.message.toString())
    }
}