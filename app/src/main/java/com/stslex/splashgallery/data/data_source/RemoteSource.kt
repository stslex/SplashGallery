package com.stslex.splashgallery.data.data_source

import com.stslex.splashgallery.data.model.RemotePagesModel
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.CoroutineDispatcher

class RemoteSource(private val ioDispatcher: CoroutineDispatcher) : RemoteSourceInterface {
    override suspend fun getResults(): Result<RemotePagesModel> {
        TODO("Not yet implemented")
    }
}