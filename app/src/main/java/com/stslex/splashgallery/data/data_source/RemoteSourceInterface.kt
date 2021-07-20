package com.stslex.splashgallery.data.data_source

import com.stslex.splashgallery.data.model.PagesModel
import com.stslex.splashgallery.utils.Result

interface RemoteSourceInterface {
    suspend fun getResult(pageNumber: Int): Result<PagesModel>
}