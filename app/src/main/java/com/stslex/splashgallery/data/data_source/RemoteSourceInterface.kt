package com.stslex.splashgallery.data.data_source

import com.stslex.splashgallery.data.model.RemotePagesModel
import com.stslex.splashgallery.utils.Result

interface RemoteSourceInterface {
    suspend fun getResults(): Result<RemotePagesModel>
}