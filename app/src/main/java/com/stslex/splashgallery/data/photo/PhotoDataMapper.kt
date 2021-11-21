package com.stslex.splashgallery.data.photo

import com.stslex.splashgallery.core.Mapper
import com.stslex.splashgallery.core.Resource
import com.stslex.splashgallery.data.core.map
import com.stslex.splashgallery.data.model.ui.image.ImageModel
import st.slex.csplashscreen.data.model.remote.image.RemoteImageModel
import javax.inject.Inject


interface PhotoDataMapper : Mapper.DataToUI<RemoteImageModel, Resource<ImageModel>> {

    class Base @Inject constructor() : PhotoDataMapper {

        override fun map(data: RemoteImageModel): Resource<ImageModel> =
            Resource.Success(data.map())

        override fun map(exception: Exception): Resource<ImageModel> =
            Resource.Failure(exception)

        override fun map(): Resource<ImageModel> = Resource.Loading
    }
}