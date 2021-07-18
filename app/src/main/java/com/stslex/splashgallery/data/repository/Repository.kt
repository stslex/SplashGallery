package com.stslex.splashgallery.data.repository

import com.stslex.splashgallery.data.data_source.RemoteSource
import com.stslex.splashgallery.data.model.RemotePagesModel
import com.stslex.splashgallery.utils.Result
import kotlinx.coroutines.CoroutineDispatcher

class Repository(
    private val remoteSource: RemoteSource,
    private val ioDispatcher: CoroutineDispatcher
) : RepositoryInterface {
    override suspend fun getPageFromRetrofit(): Result<RemotePagesModel> {
        TODO("Not yet implemented")
    }
}