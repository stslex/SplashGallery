package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.data.core.map
import com.stslex.splashgallery.data.model.response.download.RemoteDownloadModel
import com.stslex.splashgallery.data.model.data.DownloadModel
import javax.inject.Inject

interface DownloadDataMapper :
    com.stslex.core.Mapper.DataToUI<RemoteDownloadModel, com.stslex.core.Resource<DownloadModel>> {

    class Base @Inject constructor() : DownloadDataMapper {

        override fun map(data: RemoteDownloadModel): com.stslex.core.Resource<DownloadModel> =
            com.stslex.core.Resource.Success(data.map())

        override fun map(exception: Exception): com.stslex.core.Resource<DownloadModel> =
            com.stslex.core.Resource.Failure(exception)

        override fun map(): com.stslex.core.Resource<DownloadModel> =
            com.stslex.core.Resource.Loading
    }
}