package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.data.core.map
import com.stslex.splashgallery.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.model.remote.image.RemoteImageModel
import javax.inject.Inject


interface PhotoDataMapper :
    com.stslex.core.Mapper.DataToUI<RemoteImageModel, com.stslex.core.Resource<ImageModel>> {

    class Base @Inject constructor() : PhotoDataMapper {

        override fun map(data: RemoteImageModel): com.stslex.core.Resource<ImageModel> =
            com.stslex.core.Resource.Success(data.map())

        override fun map(exception: Exception): com.stslex.core.Resource<ImageModel> =
            com.stslex.core.Resource.Failure(exception)

        override fun map(): com.stslex.core.Resource<ImageModel> = com.stslex.core.Resource.Loading
    }
}