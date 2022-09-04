package com.stslex.splashgallery.data.photo

import com.stslex.core.Mapper
import com.stslex.core.Resource
import com.stslex.core_model.data.image.ImageModel
import com.stslex.splashgallery.data.core.map
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