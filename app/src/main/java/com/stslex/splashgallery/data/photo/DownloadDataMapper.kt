package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.core.Abstract
import com.stslex.splashgallery.data.model.download.RemoteDownloadModel
import com.stslex.splashgallery.data.toDownloadModel
import com.stslex.splashgallery.ui.detail_photo.UIResult
import com.stslex.splashgallery.ui.model.DownloadModel
import javax.inject.Inject

interface DownloadDataMapper<T> : Abstract.Mapper.DataToDomain<RemoteDownloadModel, T> {

    class Base @Inject constructor() : DownloadDataMapper<UIResult<DownloadModel>>,
        Abstract.Mapper.DataToUI<RemoteDownloadModel, UIResult<DownloadModel>> {
        override fun map(data: RemoteDownloadModel): UIResult<DownloadModel> =
            UIResult.Success(data.toDownloadModel())

        override fun map(exception: Exception): UIResult<DownloadModel> =
            UIResult.Failure(exception)

    }
}