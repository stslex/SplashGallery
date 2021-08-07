package com.stslex.splashgallery.mapper

import com.stslex.splashgallery.data.model.domain.DownloadModel
import com.stslex.splashgallery.data.model.remote.RemoteDownloadModel

class DownloadMapper : BaseMapper<RemoteDownloadModel, DownloadModel> {
    override fun transformToDomain(type: RemoteDownloadModel): DownloadModel =
        DownloadModel(url = type.url)
}