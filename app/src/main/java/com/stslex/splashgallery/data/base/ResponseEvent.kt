package com.stslex.splashgallery.data.base

import com.stslex.splashgallery.mapper.BaseMapper
import retrofit2.Response

class ResponseEvent<E, D>(val mapper: BaseMapper<E, D>) {

    inline fun Response<List<E>>.event(
        crossinline success: (List<D>) -> Unit,
        crossinline failure: (String) -> Unit
    ) = try {
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

    inline fun Response<E>.eventSingle(
        crossinline success: (D) -> Unit,
        crossinline failure: (String) -> Unit
    ) = try {
        if (isSuccessful && body() != null) {
            body()?.let { success(mapper.transformToDomain(it)) }
        } else {
            failure(message())
        }
    } catch (exception: Exception) {
        failure(exception.message.toString())
    }
}