package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.core.Mapper
import com.stslex.splashgallery.core.Resource
import com.stslex.splashgallery.data.core.map
import com.stslex.splashgallery.data.model.remote.download.RemoteDownloadModel
import com.stslex.splashgallery.data.model.ui.DownloadModel
import javax.inject.Inject

interface DownloadDataMapper : Mapper.DataToUI<RemoteDownloadModel, Resource<DownloadModel>> {

    class Base @Inject constructor() : DownloadDataMapper {

        override fun map(data: RemoteDownloadModel): Resource<DownloadModel> =
            Resource.Success(data.map())

        override fun map(exception: Exception): Resource<DownloadModel> =
            Resource.Failure(exception)

        override fun map(): Resource<DownloadModel> = Resource.Loading
    }
}