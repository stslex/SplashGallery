package com.stslex.splashgallery.utils.base

import com.stslex.splashgallery.mapper.BaseMapper
import retrofit2.Response

class ResponseSingleEvent<E, D>(
    val mapper: BaseMapper<E, D>,
    val success: (D) -> Unit,
    val failure: (String) -> Unit
) {
    fun Response<E>.event() = try {
        if (isSuccessful && body() != null) {
            body()?.let { success(mapper.transformToDomain(it)) }
        } else {
            failure(message())
        }
    } catch (exception: Exception) {
        failure(exception.message.toString())
    }
}