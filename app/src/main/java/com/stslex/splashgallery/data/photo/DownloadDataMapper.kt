package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.core.Mapper
import com.stslex.splashgallery.core.Resource
import com.stslex.splashgallery.data.core.toDownloadModel
import com.stslex.splashgallery.data.model.download.RemoteDownloadModel
import com.stslex.splashgallery.ui.model.DownloadModel
import javax.inject.Inject

interface DownloadDataMapper : Mapper.DataToUI<RemoteDownloadModel, Resource<DownloadModel>> {

    class Base @Inject constructor() : DownloadDataMapper {

        override fun map(data: RemoteDownloadModel): Resource<DownloadModel> =
            Resource.Success(data.toDownloadModel())

        override fun map(exception: Exception): Resource<DownloadModel> =
            Resource.Failure(exception)

        override fun map(): Resource<DownloadModel> = Resource.Loading
    }
}