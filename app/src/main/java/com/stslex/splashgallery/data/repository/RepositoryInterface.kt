package com.stslex.splashgallery.data.repository

import com.stslex.splashgallery.data.model.RemotePagesModel
import com.stslex.splashgallery.utils.Result

interface RepositoryInterface {

    suspend fun getPageFromRetrofit():Result<RemotePagesModel>
}