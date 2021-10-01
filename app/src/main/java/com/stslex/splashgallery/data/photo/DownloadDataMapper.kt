package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.data.model.download.RemoteDownloadModel
import com.stslex.splashgallery.data.toDownloadModel
import com.stslex.splashgallery.ui.core.UIResult
import com.stslex.splashgallery.ui.model.DownloadModel
import javax.inject.Inject

class DownloadDataMapper @Inject constructor() :
    Abstract.Mapper.DataToUI<RemoteDownloadModel, UIResult<DownloadModel>> {

    override fun map(data: RemoteDownloadModel): UIResult<DownloadModel> =
        UIResult.Success(data.toDownloadModel())

    override fun map(exception: Exception): UIResult<DownloadModel> =
        UIResult.Failure(exception)
}